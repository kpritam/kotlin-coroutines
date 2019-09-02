package examples

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce


@ExperimentalCoroutinesApi
fun CoroutineScope.producer(): ReceiveChannel<Int> = produce {
    (1.until(10)).map {
        send(it)
        delay(100)
    }
}

suspend fun increment(counter: Channel<Int>) = counter.send(1)

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    var counter: Int = 0
    val counterChannel = Channel<Int>()

    launch {
        for (c in counterChannel) {
            counter += 1
        }
    }

    repeat(50) {
        launch {
            increment(counterChannel)
            if (counter == 50) counterChannel.close()
        }
    }

    delay(5000)
    println(counter)
}