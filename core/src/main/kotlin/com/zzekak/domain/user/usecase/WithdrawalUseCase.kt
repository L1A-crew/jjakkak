package com.zzekak.domain.user.usecase

import com.zzekak.core.domain.user.WithdrawalResult
import com.zzekak.core.domain.user.message.WithdrawalMessage
import com.zzekak.core.domain.user.repository.UserCommandRepository
import com.zzekak.exception.ExceptionEnum
import com.zzekak.exception.ZzekakException
import org.springframework.stereotype.Service

interface WithdrawalUseCase {
    fun withdrawal(message: WithdrawalMessage): WithdrawalResult
}

@Service
internal class WithdrawalUseCaseImpl(
    val userRepo: UserCommandRepository
) : WithdrawalUseCase {
    override fun withdrawal(message: WithdrawalMessage): WithdrawalResult {
        val (userId) = message
        val user =
            userRepo.findById(userId)
                ?: throw ZzekakException(ExceptionEnum.NO_EXIST_USER)

        return userRepo.save(user.deleteUser()).let {
            WithdrawalResult(
                result = 'Y',
                message = "성공적으로 탈퇴처리 되었습니다.",
            )
        }
    }
}
