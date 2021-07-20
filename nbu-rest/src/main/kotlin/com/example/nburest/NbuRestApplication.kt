package com.example.nburest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(scanBasePackages = arrayOf("com.example"))
@EnableScheduling
@EnableJpaRepositories("com.example.nbupersistence")
@EntityScan("com.example.nbupersistence")
class NbuRestApplication

fun main(args: Array<String>) {
    runApplication<NbuRestApplication>(*args)
}
