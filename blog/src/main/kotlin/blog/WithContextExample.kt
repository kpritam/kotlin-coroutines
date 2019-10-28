package blog

import kotlinx.coroutines.*

suspend fun main() {

    // 1. create new coroutine scope with default dispatcher
    val scope = CoroutineScope(Dispatchers.Default)

    scope.launch {
        // 2. do some cpu bound operations, runs on Default thread pool
        println("${Thread.currentThread().name} doing CPU work...")

        // 3. shifts to IO thread pool
        withContext(Dispatchers.IO) {
            // 4. do some io operations like file read, network calls  etc
            println("${Thread.currentThread().name} doing IO work...")
        }

        // 5. shifts back to Default thread pool
        println("${Thread.currentThread().name} back to doing CPU work...")
    }

    delay(500)
}