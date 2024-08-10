package com.zzekak.domain.user.repository

import com.zzekak.domain.common.dao.AwsS3Dao
import com.zzekak.domain.user.UserCommand
import com.zzekak.domain.user.UserId
import com.zzekak.domain.user.dao.UserEntityDao
import com.zzekak.domain.user.entity.UserEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Repository
internal class UserCommandRepositoryImpl(
    val userEntityDao: UserEntityDao,
    val awsS3Dao: AwsS3Dao
) : UserCommandRepository {
    @Transactional
    override fun save(user: UserCommand): UserCommand = userEntityDao.save(UserEntity.from(user)).toDomain()

    @Transactional
    override fun findById(userId: UserId): UserCommand? =
        userEntityDao.findUserByUserIdAndIsRemoved(userId.value, false)?.toDomain()

    @Transactional
    override fun saveProfileImage(
        userId: UserId,
        profileImage: MultipartFile
    ): String {
        val uploadedUrl =
            awsS3Dao.uploadFile(
                path = PATH_USER_PROFILE,
                fileName = userId.value.toString(),
                file = profileImage,
            )

        val updated = userEntityDao.getUserById(userId).apply { profileImageUrl = uploadedUrl }
        userEntityDao.save(updated)

        return uploadedUrl
    }

    override fun updateFcmKey(
        userId: UserId,
        fcmKeyValue: String
    ): UserCommand {
        val updated = userEntityDao.getUserById(userId).apply { fcmKey = fcmKeyValue }
        return userEntityDao.save(updated).toDomain()
    }

    companion object {
        const val PATH_USER_PROFILE = "user/profile"
    }
}
