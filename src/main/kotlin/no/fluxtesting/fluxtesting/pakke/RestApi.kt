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
        /*
    }
       return generateData()
         .map { sequence: Long ->
                ServerSentEvent.builder<HelloMessage>()
                    .id(sequence.toString())
                    .event("periodic-event")
                    .data(HelloMessage())
                    .build()*/

        return Flux.interval(Duration.ofMillis(50))
         .map { sequence: Long ->
                ServerSentEvent.builder<HelloMessage>()
                    .id(sequence.toString())
                    .event("periodic-event")
                    .data(HelloMessage())
                    .build()
            }


    }
}

/**
 * Generate some data
 */
fun generateData(): Flux<HelloMessage> {
    val antall = (10..2000).random()
    val data = mutableListOf<HelloMessage>()
    for (i in 1..antall) {
        data.add(HelloMessage())
    }
    return Mono.just(data.toList()).flatMapIterable { iter -> iter }
}

/**
 * Message in the stream
 */
data class HelloMessage(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "Hello_$id".toString(),
    val timestamp: LocalDateTime = LocalDateTime.now()
)

