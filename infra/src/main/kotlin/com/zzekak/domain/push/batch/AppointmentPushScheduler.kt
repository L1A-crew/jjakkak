package com.zzekak.domain.push.batch

import com.zzekak.domain.push.dao.AppointmentPushEntityDao
import org.springframework.stereotype.Service

/**
 * 최소 출발시간, 반경 2km/1km 진입 시 push알림 배치.
 *
 * 작업순서
 * 1. appointment_time_push 테이블에서 출발시간/도착시간/유저ID/반경2km/1km 여부 조회
 * 2-1. 출발시간이 현재시간 기준으로 5분 이내에 있으면 push 발송
 * 2-2.
 */
@Service
class AppointmentPushScheduler(
    val appointmentTimePushDao: AppointmentPushEntityDao,
) {
    /*db엔 UTC로 저장되므로 TimeZone을 Asia/Seoul로 변환하는 작업 필요
    * utcTime.withZoneSameInstant(ZoneId.of("Asia/Seoul"));*/

}
