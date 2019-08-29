package scripts

import kotlinx.coroutines.*
import java.util.*
import java.util.concurrent.CompletableFuture

fun main() {

    val script = Script()

    val test = GlobalScope.async {
        script.execute(10)
    }

    runBlocking { test.await().forEach { println(it) } }
}

// simulating network call
suspend fun getEvent(): String {
    delay(1000)
    return "Event Id: ${UUID.randomUUID()}"
}

// java - kotlin interop ====================
fun getFutureInt(): CompletableFuture<Int> = CompletableFuture.completedFuture(100)

suspend fun getSuspendableInt(): Int = getFutureInt().await()
//  ====================

