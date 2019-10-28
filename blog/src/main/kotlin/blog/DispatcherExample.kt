package blog

import io.kotlintest.shouldBe
import kotlinx.coroutines.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

suspend fun main() {

    // 1. create single threaded dispacher
    val ec: ExecutorService = Executors.newSingleThreadExecutor()
    val dispatcher: ExecutorCoroutineDispatcher = ec.asCoroutineDispatcher()

    // 2. bind coroutine scope with single threaded dispatcher
    val scope = CoroutineScope(dispatcher)
    var counter = 0
    val jobs = mutableListOf<Job>()

    fun incrementAsync() = scope.launch { counter += 1 }

    // 3. launch 1000 coroutines to increment counter
    repeat(1000) {
        jobs += incrementAsync()
    }

    1 shouldBe 1

    // 4. wait for all the coroutines to finish
    jobs.joinAll()
    println("Counter = $counter")

}