package com.zzekak.domain.appointment.repository

import com.zzekak.domain.address.entity.AppointmentAddressEntity
import com.zzekak.domain.address.model.AppointmentAddress
import com.zzekak.domain.address.model.AppointmentAddressId
import com.zzekak.domain.address.model.SearchedPathResponse
import com.zzekak.domain.address.repository.PathFindingRepository
import com.zzekak.domain.appointment.dao.AppointmentEntityDao
import com.zzekak.domain.appointment.entity.AppointmentEntity
import com.zzekak.domain.appointment.entity.AppointmentUserEntity
import com.zzekak.domain.appointment.entity.AppointmentUserId
import com.zzekak.domain.appointment.model.Appointment
import com.zzekak.domain.appointment.model.AppointmentCommand
import com.zzekak.domain.appointment.model.AppointmentId
import com.zzekak.domain.appointment.model.AppointmentQuery
import com.zzekak.domain.appointmentmission.dao.AppointmentMissionEntityDao
import com.zzekak.domain.appointmentmission.entity.AppointmentMissionEntity
import com.zzekak.domain.appointmentmission.entity.AppointmentMissionId
import com.zzekak.domain.mission.MissionCode
import com.zzekak.domain.mission.MissionContentsCode
import com.zzekak.domain.mission.MissionContentsType
import com.zzekak.domain.mission.dao.MissionEntityDao
import com.zzekak.domain.mission.entity.MissionEntity
import com.zzekak.domain.mission.model.AppointmentMissionCommand
import com.zzekak.domain.mission.model.MissionCommand
import com.zzekak.domain.push.PushTypeCode
import com.zzekak.domain.push.dao.AppointmentPushDataEntityDao
import com.zzekak.domain.push.dao.AppointmentPushEntityDao
import com.zzekak.domain.push.entity.AppointmentPushDataEntity
import com.zzekak.domain.push.model.AppointmentPushCommand
import com.zzekak.domain.push.entity.AppointmentPushDataId
import com.zzekak.domain.push.entity.AppointmentPushEntity
import com.zzekak.domain.user.UserId
import com.zzekak.domain.user.dao.UserEntityDao
import com.zzekak.domain.user.entity.UserEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.reflect.KClass

@Repository
internal class AppointmentRepositoryImpl(
    val dao: AppointmentEntityDao,
    val userDao: UserEntityDao,
    val appointmentMissionDao: AppointmentMissionEntityDao,
    val missionDao: MissionEntityDao,
    val pathFindingRepo: PathFindingRepository,
    val appointmentPushDao: AppointmentPushEntityDao,
    val appointmentPushDataDao: AppointmentPushDataEntityDao
) : AppointmentRepository {
    @Transactional
    override fun <T : Appointment> save(
        appointmentCommand: AppointmentCommand,
        returnType: KClass<out T>
    ): T {
        val existed = dao.findById(appointmentCommand.id)
        val existedUsers = userDao.findAllByIds(appointmentCommand.participants.map { it.userId })
        val saved =
            dao.save(
                appointmentCommand.toEntity(
                    existed = existed,
                    participantUserEntities = existedUsers,
                ),
            )

        /*약속 생성 시 자동으로 insert(mission/push) */
//        addMission(existedUsers, saved, appointmentCommand)

        existedUsers.forEach { ptcp ->
            MissionCode.entries.forEach {
                val missionEntity = MissionCommand(
                    missionContents = MissionContentsCode.MISSION_TAP,
                    contentType = MissionContentsType.TAP
                ).toMissionEntity()
                val missionSaved = missionDao.save(missionEntity)
                val apntMisn = AppointmentMissionCommand(
                    appointmentId = AppointmentId(saved.appointmentId),
                    userId = UserId(ptcp.userId),
                    phaseCd = it,
                    missionId = missionSaved.missionId!!,
                    completeAt = null
                ).toApntMisnEntity(
                    appointment = saved,
                    participants = ptcp,
                    mission = missionSaved
                )
                appointmentMissionDao.save(apntMisn)
            }
            /* App push 발송을 위한 DB insert */
            var strtX = ""
            var strtY = ""
            appointmentCommand.participants.map { participant ->
                strtX = participant.departureAddress.address.x
                strtY = participant.departureAddress.address.y
            }

            val path: SearchedPathResponse = pathFindingRepo.findPath(
                strtLocX = strtX,
                strtLocY = strtY,
                endLocX = appointmentCommand.destinationAddress.address.x,
                endLocY = appointmentCommand.destinationAddress.address.y,
                appointmentTime = appointmentCommand.appointmentTime.atZone(ZoneId.of("Asia/Seoul"))
            )
            val departure = Instant.ofEpochSecond(path.departureTimeValue).atZone(ZoneId.of("Asia/Seoul"))
            val arrivalTime = Instant.ofEpochSecond(path.arrivalTimeValue).atZone(ZoneId.of("Asia/Seoul"))
            val pushCmd = AppointmentPushCommand(
                appointmentTime = arrivalTime.toInstant(),
                departureTime = departure.minus(Duration.ofMinutes(5)).toInstant(), //최소출발시간 5분전..!
                createAt = Instant.now()
            )
            val pushEntity = pushCmd.toEntity(
                appointment = saved,
                user = ptcp)
            val apntPush = appointmentPushDao.save(pushEntity)

            PushTypeCode.entries.forEach{
                if(it.name.startsWith("PUSH_TYPE", true)){
                    val value =  when(it.code){
                        PushTypeCode.PUSH_TYPE_DEPARTURE_TIME.code -> pushCmd.departureTime.toString()
                        PushTypeCode.PUSH_TYPE_ARRIVAL_TIME.code -> pushCmd.appointmentTime.toString()
                        PushTypeCode.PUSH_TYPE_RADIUS_2KM.code -> "N"
                        else -> ""
                    }
                   val pushDataEntity = apntPush.toPushDataEntity(
                        type = it,
                        value = value,
                    )
                    appointmentPushDataDao.save(pushDataEntity)
                }
            }

        }


        return saved.toDomain(returnType)
    }

    @Transactional
    fun addMission(existedUsers: List<UserEntity>,
                   saved: AppointmentEntity,
                   appointmentCommand: AppointmentCommand
                   ){
        existedUsers.forEach { ptcp ->
            MissionCode.entries.forEach {
                val missionEntity = MissionCommand(
                    missionContents = MissionContentsCode.MISSION_TAP,
                    contentType = MissionContentsType.TAP
                ).toMissionEntity()
                val missionSaved = missionDao.save(missionEntity)
                val apntMisn = AppointmentMissionCommand(
                    appointmentId = AppointmentId(saved.appointmentId),
                    userId = UserId(ptcp.userId),
                    phaseCd = it,
                    missionId = missionSaved.missionId!!,
                    completeAt = null
                ).toApntMisnEntity(
                    appointment = saved,
                    participants = ptcp,
                    mission = missionSaved
                )
                appointmentMissionDao.save(apntMisn)
            }
            /* App push 발송을 위한 DB insert */
            var strtX = ""
            var strtY = ""
            appointmentCommand.participants.map { participant ->
                strtX = participant.departureAddress.address.x
                strtY = participant.departureAddress.address.y
            }

            val path: SearchedPathResponse = pathFindingRepo.findPath(
                strtLocX = strtX,
                strtLocY = strtY,
                endLocX = appointmentCommand.destinationAddress.address.x,
                endLocY = appointmentCommand.destinationAddress.address.y,
                appointmentTime = appointmentCommand.appointmentTime.atZone(ZoneId.of("Asia/Seoul"))
            )
            val departure = Instant.ofEpochSecond(path.departureTimeValue).atZone(ZoneId.of("Asia/Seoul"))
            val arrivalTime = Instant.ofEpochSecond(path.arrivalTimeValue).atZone(ZoneId.of("Asia/Seoul"))
            val pushCmd = AppointmentPushCommand(
                appointmentTime = arrivalTime.toInstant(),
                departureTime = departure.minus(Duration.ofMinutes(5)).toInstant(), //최소출발시간 5분전..!
                createAt = Instant.now()
            )
            val pushEntity = pushCmd.toEntity(
                appointment = saved,
                user = ptcp)
            val apntPush = appointmentPushDao.save(pushEntity)
            PushTypeCode.entries.forEach{
                if(it.name.startsWith("PUSH_TYPE", true)){
                    val value =  when(it.code){
                        PushTypeCode.PUSH_TYPE_DEPARTURE_TIME.code -> pushCmd.departureTime.toString()
                        PushTypeCode.PUSH_TYPE_ARRIVAL_TIME.code -> pushCmd.appointmentTime.toString()
                        PushTypeCode.PUSH_TYPE_RADIUS_2KM.code -> "N"
                        else -> ""
                    }
                    val pushData = apntPush.toPushDataEntity(
                        type = it,
                        value = value,
                    )
                    appointmentPushDataDao.save(pushData)

                }
            }

        }
    }

    @Transactional
    override fun <T : Appointment> findAllByUserId(
        userId: UserId,
        returnType: KClass<out T>
    ): List<T> = dao.findByUserId(userId).map { it.toDomain(returnType) }

    @Transactional
    override fun <T : Appointment> findBy(
        id: AppointmentId,
        returnType: KClass<out T>
    ): T? = dao.findById(id)?.toDomain(returnType)

    private fun AppointmentCommand.toEntity(
        existed: AppointmentEntity?,
        participantUserEntities: Collection<UserEntity>
    ) = existed?.apply {
        this.ownerId = this@toEntity.ownerId.value
        this.name = this@toEntity.name
        this.appointmentAddress = destinationAddress.toEntity(existed.appointmentAddress)
        this.appointmentTime = this@toEntity.appointmentTime
        this.participants = this@toEntity.toAppointmentUserEntity(this, participantUserEntities)
        this.deleted = this@toEntity.deleted
    } ?: AppointmentEntity(
        appointmentId = id.value,
        ownerId = ownerId.value,
        name = name,
        appointmentAddress = destinationAddress.toEntity(null),
        appointmentTime = appointmentTime,
        participants = mutableSetOf(),
        deleted = deleted,
    ).apply { this.participants = this@toEntity.toAppointmentUserEntity(this, participantUserEntities) }

    private fun AppointmentCommand.toAppointmentUserEntity(
        existed: AppointmentEntity,
        participants: Collection<UserEntity>
    ): Set<AppointmentUserEntity> =
        with(this.participants.associate { it.userId.value to it.departureAddress }) {
            participants.mapNotNull {
                AppointmentUserEntity(
                    id = AppointmentUserId(appointmentId = existed.appointmentId, userId = it.userId),
                    appointment = existed,
                    user = it,
                    departureAddress = this[it.userId]?.toEntity(null) ?: return@mapNotNull null,
                )
            }.toSet()
        }

    private fun AppointmentMissionCommand.toApntMisnEntity(
        appointment: AppointmentEntity,
        participants: UserEntity,
        mission: MissionEntity,
    ) : AppointmentMissionEntity = AppointmentMissionEntity(
        id = AppointmentMissionId(
            appointmentId = appointment.appointmentId,
            userId =participants.userId,
            missionId = mission.missionId,
        ),
        appointment = appointment,
        user = participants,
        mission = mission,
        phaseCd = this.phaseCd.code,
        completeAt = null
    )

    private fun AppointmentAddress.toEntity(existed: AppointmentAddressEntity?) =
        AppointmentAddressEntity(
            id = existed?.id ?: id.value,
            cityOrProvince = address.cityOrProvince,
            districtOrCity = address.districtOrCity,
            postalCode = address.postalCode,
            jibunAddress = address.jibunAddress,
            roadAddress = address.roadAddress,
            x = address.x,
            y = address.y,
        )

    @Suppress("UNCHECKED_CAST")
    private fun <T : Appointment> AppointmentEntity.toDomain(clazz: KClass<out T>): T =
        when (clazz) {
            AppointmentCommand::class ->
                AppointmentCommand(
                    id = AppointmentId(appointmentId),
                    ownerId = UserId(ownerId),
                    name = name,
                    destinationAddress = appointmentAddress.toDomain(),
                    appointmentTime = appointmentTime,
                    participants =
                        participants.map {
                            AppointmentCommand.Participant(
                                userId = UserId(it.user.userId),
                                departureAddress =
                                    AppointmentAddress(
                                        id = AppointmentAddressId(it.departureAddress.id),
                                        address = it.departureAddress.toDomain().address,
                                    ),
                            )
                        }.toSet(),
                    deleted = deleted,
                ) as T

            AppointmentQuery::class ->
                AppointmentQuery(
                    id = AppointmentId(appointmentId),
                    ownerId = UserId(ownerId),
                    name = name,
                    destinationAddress = appointmentAddress.toDomain(),
                    appointmentTime = appointmentTime,
                    participants = participants.map { UserId(it.user.userId) },
                    createdAt = createdAt,
                    updatedAt = updatedAt,
                    deleted = deleted,
                ) as T

            else -> throw IllegalArgumentException("Unsupported type: ${clazz.qualifiedName}")
        }

    private fun AppointmentAddressEntity.toDomain() =
        AppointmentAddress.create(
            id = AppointmentAddressId(id),
            cityOrProvince = cityOrProvince,
            districtOrCity = districtOrCity,
            postalCode = postalCode,
            jibunAddress = jibunAddress,
            roadAddress = roadAddress,
            x = x,
            y = y,
            undergroundYn = "N",
            buildingName = "",
        )

    /* 미션 생성 관련 처리*/
    private fun MissionCommand.toMissionEntity() =
        MissionEntity(
            missionId = null,
            missionContents = this.missionContents.code,
            contentType = this.contentType.value,
            createAt = ZonedDateTime.now().toInstant()
        )

    private fun AppointmentPushCommand.toEntity(
        appointment: AppointmentEntity,
        user: UserEntity
    ) = AppointmentPushEntity(
            appointmentPushId = null,
            appointmentId = appointment,
            userId = user,
            createAt = this.createAt
        )
    private fun AppointmentPushEntity.toPushDataEntity(
        type: PushTypeCode,
        value: String,
    ) = AppointmentPushDataEntity (
        appointmentPushId = this,
//        pushType = type.code,
        pushValue = null,
        pushDataValue = value,
        sendAt = false,
        id = AppointmentPushDataId(
            appointmentPushId = this.appointmentPushId!!,
            pushType = type.code
        )
    )
}
