package ru.oganesyan.artem.archibaldserver

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class ArchibaldServerApplication{
    fun main(args: Array<String>) {
        val app = SpringApplication(ArchibaldServerApplication().javaClass)
        app.run(args.firstOrNull())
    }
}


fun main(args: Array<String>) {
    runApplication<ArchibaldServerApplication>(*args)
}