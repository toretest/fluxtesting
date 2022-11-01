package no.fluxtesting.fluxtesting.pakke

import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random


@RestController
//@RequestMapping("rest")
class RestApi {

    /**
     * https://www.baeldung.com/spring-server-sent-events
     * https://www.baeldung.com/spring-server-sent-events:
     *
     * 1. we can handle the events metadata, which we'd need in a real case scenario
     * 2. we can ignore “text/event-stream” media type declaration
     */
    @GetMapping("/stream")
    fun streamEvents(): Flux<ServerSentEvent<HelloMessage>> {
        return Flux.interval(Duration.ofMillis(40))
         .map { sequence: Long ->
                ServerSentEvent
                    .builder<HelloMessage>()
                    .id(sequence.toString())
                    .event("Price-event")
                    .data(HelloMessage())
                    .build()
            }
    }
}

/**
 * Message in the stream
 */
data class HelloMessage(
    val id: String = UUID.randomUUID().toString(),
    val value : Int = (1 .. 10).random(),
    val name: String = "Pris",
    val timestamp: LocalDateTime = LocalDateTime.now()
)

