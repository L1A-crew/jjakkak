package com.zzekak.batch.push.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.util.ResourceUtils
import java.io.FileInputStream
import java.io.IOException
import kotlin.jvm.Throws

@Configuration
@EnableScheduling
@PropertySource("classpath:pushmsg.yml")
class SchedulerConfig(
    @Value("\${firebase.path}")
    val firebaseKeyPath: String,
) {
    @Bean
    @Throws(IOException::class)
    fun firebaseApp(): FirebaseApp {
        val firebaseFile = FileInputStream(ResourceUtils.getFile(firebaseKeyPath))
        val options =
            FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(firebaseFile))
                .build()
        return FirebaseApp.initializeApp(options)
    }

    @Bean
    fun firebaseMessaging(firebaseApp: FirebaseApp): FirebaseMessaging {
        return FirebaseMessaging.getInstance(firebaseApp)
    }
}
