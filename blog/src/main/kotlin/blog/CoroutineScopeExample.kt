package blog

import kotlinx.coroutines.*

object MyException : Throwable("Dummy exception", null, false, false)

fun main() = runBlocking {

    val scope = CoroutineScope(CoroutineName("Parent"))
    // New job gets created if not provided explicitely
    if (scope.coroutineContext[Job] != null) {
        println("New job is created!")
    }

    scope.launch {
        launch(CoroutineName("Child-A")) {
            delay(500)
            println("${Thread.currentThread().name} throwing exception")
            throw MyException
        }

        launch(CoroutineName("Child-B")) {
            println("${Thread.currentThread().name} before exception...")
            delay(1000)
            println("${Thread.currentThread().name} after exception...")
        }
    }

    delay(2000)
}