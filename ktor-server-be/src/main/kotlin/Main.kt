package org.example

import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


data class User(val id: Int, val name: String)
data class Response(val status: Int, val message: String, val data: Any?)

const val USER_ID = "User-Id"
const val SCOPE = "Scope"

fun main() {
    val users = mutableListOf(
        User(1, "Alice"),
        User(2, "Bob")
    )
    val gson = Gson()

    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            gson()
        }

        routing {
            route("/users") {
                get {
                    if (call.request.headers[USER_ID]?.equals("ifabula") == false || call.request.headers[SCOPE]?.equals(
                            "user"
                        ) == false
                    ) {
                        call.respondText {
                            gson.toJson(
                                Response(
                                    status = HttpStatusCode.Unauthorized.value,
                                    message = "Unauthorized",
                                    data = null
                                )
                            )
                        }
                        return@get
                    }


                    call.respondText {
                        gson.toJson(
                            Response(
                                status = HttpStatusCode.OK.value,
                                message = "",
                                data = users
                            )
                        )
                    }
                }
                post {
                    if (call.request.headers[USER_ID]?.equals("ifabula") == false || call.request.headers[SCOPE]?.equals(
                            "user"
                        ) == false
                    ) {
                        call.respondText {
                            gson.toJson(
                                Response(
                                    status = HttpStatusCode.Unauthorized.value,
                                    message = "Unauthorized",
                                    data = null
                                )
                            )
                        }
                        return@post
                    }
                    val user = call.receive<User>()
                    users.add(user)
                    call.respondText {
                        gson.toJson(
                            Response(
                                status = HttpStatusCode.Created.value,
                                message = "Successfully created",
                                data = users
                            )
                        )
                    }
                }

            }
        }
    }.start(wait = true)
}
