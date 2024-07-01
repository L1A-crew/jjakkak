package com.zzekak.domain.user.usecase

import com.zzekak.domain.user.*
import com.zzekak.domain.user.repository.UserCommandRepository
import com.zzekak.domain.user.repository.UserQueryRepository
import com.zzekak.exception.ExceptionEnum
import com.zzekak.exception.ZzekakException
import org.springframework.stereotype.Service
import java.util.UUID

interface WithdrawalUseCase {
    fun withdrawal(userId: UUID): WithdrawalResult
}

@Service
internal class WithdrawalUseCaseImpl(
    val userRepo: UserCommandRepository,
) : WithdrawalUseCase {
    override fun withdrawal(userId: UUID): WithdrawalResult {
        val user = userRepo.findById(UserId(userId)) ?: throw ZzekakException(ExceptionEnum.NO_EXIST_USER)
        return userRepo.save(user.deleteUser()).let {
            WithdrawalResult(
                result = 'Y',
                message = "성공적으로 탈퇴처리 되었습니다.",
            )
        }
    }
}
