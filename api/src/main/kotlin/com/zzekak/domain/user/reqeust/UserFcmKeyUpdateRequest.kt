package com.zzekak.domain.user.reqeust

import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize
internal class UserFcmKeyUpdateRequest(
    val fcmKey: String
)
