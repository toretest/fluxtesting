package no.fluxtesting.fluxtesting

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FluxtestingApplication

fun main(args: Array<String>) {
    runApplication<FluxtestingApplication>(*args)
}
