package blog

import kotlinx.coroutines.*
import kotlinx.coroutines.test.setMain

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    Dispatchers.setMain(Dispatchers.Default)

    val scope = MainScope()

    scope.launch {

        launch(CoroutineName("A")) {
            println("[${Thread.currentThread().name}] from A")
        }

        launch(CoroutineName("B")) {
            delay(500)
            println("[${Thread.currentThread().name}] from B")
        }

    }
    delay(1000)
}