package com.zzekak.infra.domain.common.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
abstract class AuditableBase {
    @CreatedDate
    @Column(name = COLUMN_CREATED_AT, updatable = false)
    lateinit var createdAt: Instant

    @LastModifiedDate
    @Column(name = COLUMN_UPDATED_AT)
    lateinit var updatedAt: Instant

    companion object {
        const val COLUMN_CREATED_AT = "created_at"
        const val COLUMN_UPDATED_AT = "updated_at"
    }
}
