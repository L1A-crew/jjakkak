package com.zzekak.domain.push.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = ZzekakPushEntity.ZZEKAK_PUSH)
@EntityListeners(AuditingEntityListener::class)
class ZzekakPushEntity(
    @Id
    @Column(name = COLUMN_PUSH_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val pushId: Long?,
    @Column(name = COLUMN_PUSH_TYPE)
    val pushType: String,
    @Column(name = COLUMN_CONTENTS)
    val contents: String,
    @Column(name = COLUMN_RECEIVER_ID)
    val receiverId: String?,
    @Column(name = COLUMN_SEND_TIME)
    val sendTime: Instant,
    @Column(name = COLUMN_SENDER_ID)
    val senderId: UUID,
    @Column(name = COLUMN_CREATE_AT)
    val createAt: Instant
) {
    companion object {
        const val ZZEKAK_PUSH = "zzekak_push"
        const val COLUMN_PUSH_ID = "push_id"
        const val COLUMN_PUSH_TYPE = "push_type"
        const val COLUMN_CONTENTS = "contents"
        const val COLUMN_RECEIVER_ID = "receiver_id"
        const val COLUMN_CREATE_AT = "create_at"
        const val COLUMN_SEND_TIME = "send_time"
        const val COLUMN_SENDER_ID = "sender_id"
    }
}
