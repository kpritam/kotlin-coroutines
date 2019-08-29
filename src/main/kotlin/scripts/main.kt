package scripts

import kotlinx.coroutines.*
import java.util.*
import java.util.concurrent.CompletableFuture

fun main() {

    val strandEc = StrandEc()
    val script = Script(strandEc)


    val test = GlobalScope.async(context = strandEc) {
        val result = script.execute(0)
        delay(1000)
        script.executeCmdsHandled()
        result
    }

    runBlocking { test.await().forEach { println(it) } }

    strandEc.close()
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

