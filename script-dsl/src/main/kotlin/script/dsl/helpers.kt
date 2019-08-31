package script.dsl

import kotlinx.coroutines.delay
import kotlinx.coroutines.future.await
import java.util.*
import java.util.concurrent.CompletableFuture


// simulating network call
suspend fun getEvent(): String {
    delay(1000)
    return "Event Id: ${UUID.randomUUID()}"
}

// java - kotlin interop ====================
fun getFutureInt(): CompletableFuture<Int> = CompletableFuture.completedFuture(100)

suspend fun getSuspendableInt(): Int = getFutureInt().await()
//  ====================

