import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(CIO, port = 8080) {
        routing {
            get("/echo/{msg}") {
                val msg = call.parameters["msg"]!!
                call.respondText(msg)
            }
        }
        log.info("use path /echo/${'$'}msgToEcho")
    }.start(wait = true)
}

