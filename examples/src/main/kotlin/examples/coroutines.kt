package examples

import kotlinx.coroutines.*
import java.util.concurrent.Executors

fun main() {
    runBlocking {
        Test1.start().awaitAll()
    }
    println(Test1.counter)
    Test1.end()

}

object Test1 {
    var counter = 0

    private val singleThreaded = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    private fun incrementAsync(): Deferred<Int> {
        return GlobalScope.async(context = singleThreaded) {
            counter += 1
            counter
        }
    }

    fun start(): List<Deferred<Int>> {
        return (1..10000).map {
            incrementAsync()
        }
    }

    fun end() = singleThreaded.close()

}

