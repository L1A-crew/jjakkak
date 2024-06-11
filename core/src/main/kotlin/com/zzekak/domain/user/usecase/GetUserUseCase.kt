package com.zzekak.domain.user.usecase

import com.zzekak.domain.user.UserQuery

interface GetUserUseCase {
    fun getUser(): UserQuery
}
