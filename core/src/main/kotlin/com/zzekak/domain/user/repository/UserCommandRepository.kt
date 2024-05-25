package com.zzekak.core.domain.user.repository

import com.zzekak.core.domain.user.UserCommand
import com.zzekak.core.domain.user.UserId

interface UserCommandRepository {
    fun save(user: UserCommand): UserCommand

    fun findById(userId: UserId): UserCommand?

    fun getById(userId: UserId): UserCommand = findById(userId) ?: throw NoSuchElementException("User not found")
}
