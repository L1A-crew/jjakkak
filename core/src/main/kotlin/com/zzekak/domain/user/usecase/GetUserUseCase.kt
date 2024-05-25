package com.zzekak.core.domain.user.usecase

import com.zzekak.core.domain.user.UserQuery

interface GetUserUseCase {
    fun getUser(): UserQuery
}
