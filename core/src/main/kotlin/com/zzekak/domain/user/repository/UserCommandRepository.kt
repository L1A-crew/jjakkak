package com.zzekak.domain.user.repository

import com.zzekak.domain.user.UserCommand
import com.zzekak.domain.user.UserId
import org.springframework.web.multipart.MultipartFile

interface UserCommandRepository {
    fun save(user: UserCommand): UserCommand

    fun findById(userId: UserId): UserCommand?

    fun getById(userId: UserId): UserCommand = findById(userId) ?: throw NoSuchElementException("User not found")

    fun saveProfileImage(
        userId: UserId,
        profileImage: MultipartFile
    ): String

    fun updateFcmKey(
        userId: UserId,
        fcmKey: String
    ): UserCommand
}
