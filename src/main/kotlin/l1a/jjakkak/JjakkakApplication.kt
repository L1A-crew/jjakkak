package l1a.jjakkak

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.time.ZoneId
import java.time.ZoneOffset.UTC
import java.util.TimeZone

@SpringBootApplication
@EnableJpaAuditing
class JjakkakApplication

fun main(args: Array<String>) {
	// 실행 환경에 따라 타임존이 변경되어 데이터가 의도와 다르게 출력되는 경우를 막기 위해 UTC 로 고정
	TimeZone.setDefault(TimeZone.getTimeZone(UTC))
	runApplication<JjakkakApplication>(*args)
}
