package l1a.jjakkak.infra.domain.user.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import l1a.jjakkak.infra.domain.auth.entity.AuthenticationEntity
import l1a.jjakkak.infra.domain.user.entity.UserEntity.Companion.TABLE_USER
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = TABLE_USER)
@EntityListeners(AuditingEntityListener::class)
class UserEntity(
    @Id @Column(name = COLUMN_USER_ID)
    val userId: UUID,

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "userEntity", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = AuthenticationEntity.COLUMN_USER_ID)
    var authenticationEntity: AuthenticationEntity,
) {
    @Column(name = CREATED_AT)
    @CreatedDate
    lateinit var createdAt: Instant

    @Column(name = COLUMN_UPDATED_AT)
    @LastModifiedDate
    lateinit var updatedAt: Instant

    companion object {
        const val TABLE_USER = "user"
        const val COLUMN_USER_ID = "user_id"
        const val CREATED_AT = "created_at"
        const val COLUMN_UPDATED_AT = "updated_at"

    }
}