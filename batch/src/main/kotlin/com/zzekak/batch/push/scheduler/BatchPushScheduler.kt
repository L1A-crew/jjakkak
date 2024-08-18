package com.zzekak.batch.push.scheduler

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.MulticastMessage
import com.google.firebase.messaging.Notification
import com.zzekak.domain.appointmentmission.dao.AppointmentMissionEntityDao
import com.zzekak.domain.push.AppointmentPushMessage
import com.zzekak.domain.push.PushTypeCode
import com.zzekak.domain.push.batch.model.BatchPushDataQuery
import com.zzekak.domain.push.dao.AppointmentPushDataEntityDao
import com.zzekak.domain.push.entity.AppointmentPushDataEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * 3분마다 동작하면서 push발송
 */
@Component
internal class BatchPushScheduler(
    val pushRepo: AppointmentPushDataEntityDao,
    val amRepo: AppointmentMissionEntityDao,
    val firebase: FirebaseMessaging,
) {
    private val logger: Logger = LoggerFactory.getLogger(BatchPushScheduler::class.java)

//    @Scheduled(fixedRate = 180000) // ms 3 * 60 * 1000
    fun startPushScheduling() {
        val seoulZoneId = ZoneId.of("Asia/Seoul")
        val threeMinute = ZonedDateTime.ofInstant(Instant.now().plus(Duration.ofMinutes(3)), seoulZoneId)
        val allPushList = pushRepo.getAllPushList(threeMinute.toString()) // 현재 시간부터 3분이내 발송해야하는 건수 조회

//        val pushList = allPushList.filter { it.user.fcmKey.isNotEmpty() }       //단건발송때 사용할 수 있음.
//            .map { makeMessage(it) }

        val departureList =
            allPushList.filter {
                it.user.fcmKey?.isNotEmpty() == true &&
                    it.push.id.pushType.equals(PushTypeCode.PUSH_TYPE_DEPARTURE_TIME.code)
            }.map { it.user.fcmKey }
        val departureMsg = makeMultiMessage(departureList, PushTypeCode.PUSH_TYPE_DEPARTURE_TIME.code)

        val midList =
            allPushList.filter {
                it.user.fcmKey?.isNotEmpty() == true &&
                    it.push.id.pushType.equals(PushTypeCode.PUSH_TYPE_MIDL_TIME.code)
            }.map { it.user.fcmKey }
        val midMsg = makeMultiMessage(midList, PushTypeCode.PUSH_TYPE_MIDL_TIME.code)

        val arrivalList =
            allPushList.filter {
                it.user.fcmKey?.isNotEmpty() == true &&
                    it.push.id.pushType.equals(PushTypeCode.PUSH_TYPE_ARRIVAL_TIME.code)
            }.map { it.user.fcmKey }
        val arrivalMsg = makeMultiMessage(arrivalList, PushTypeCode.PUSH_TYPE_ARRIVAL_TIME.code)

        // 최소출발 시간 지났는데 출발인증 안한 사람들 재촉push 발송
        val notDepartureUserFcmList = amRepo.getNotDepartureUserList()
        val lateMsg = makeLateMsg(notDepartureUserFcmList.filter { it != null && it.isNotEmpty() })

        val dRslt = firebase.sendMulticast(departureMsg)
        logger.info("departure push List => S: ${dRslt.successCount}, F: ${dRslt.failureCount}")
        val mRslt = firebase.sendMulticast(midMsg)
        logger.info("middle push List => S: ${mRslt.successCount}, F: ${mRslt.failureCount}")
        val aRslt = firebase.sendMulticast(arrivalMsg)
        logger.info("arrival push List => S: ${aRslt.successCount}, F: ${aRslt.failureCount}")
        val lRslt = firebase.sendMulticast(lateMsg)
        logger.info("lateDeparture push List => S: ${lRslt.successCount}, F: ${lRslt.failureCount}")

        // push발송처리 완료.

        val pushList = allPushList.filterIsInstance<AppointmentPushDataEntity>().map { it.id }
        pushRepo.updatePushSendAt(pushList)
    }

    private fun makeLateMsg(fcmKeyList: List<String>): MulticastMessage {
        val message = AppointmentPushMessage.NOT_DEPARTED.message
        val title = "째깍째깍"
        val noti =
            Notification.builder()
                .setTitle(title)
                .setBody(message)
                .build()

        return MulticastMessage.builder()
            .addAllTokens(fcmKeyList)
            .setNotification(noti)
            .build()
    }

    private fun makeMultiMessage(
        fcmKeyList: List<String>,
        pushType: String
    ): MulticastMessage {
        val message = AppointmentPushMessage.values().find { it.code == pushType }!!.message
        val title = "째깍째깍"

        val noti =
            Notification.builder()
                .setTitle(title)
                .setBody(message)
                .build()

        return MulticastMessage.builder()
            .addAllTokens(fcmKeyList)
            .setNotification(noti)
            .build()
    }

    private fun makeMessage(obj: BatchPushDataQuery): Message {
        val fcmKey = obj.user.fcmKey
        val pushType = obj.push.id.pushType
        val message = AppointmentPushMessage.values().find { it.code == pushType }!!.message
        val title = "째깍째깍"

        val noti =
            Notification.builder()
                .setTitle(title)
                .setBody(message)
                .build()

        return Message.builder()
            .setToken(fcmKey)
            .setNotification(noti)
            .build()
    }

    private fun sendMultiPush(pushList: BatchPushDataQuery) {
    }
}
