package com.zzekak.domain.push.batch.model

import com.zzekak.domain.push.entity.AppointmentPushDataEntity
import com.zzekak.domain.user.entity.UserEntity

class BatchPushDataQuery(
    val user: UserEntity,
    val push: AppointmentPushDataEntity
)
