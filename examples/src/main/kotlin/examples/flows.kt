package examples

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking


fun start() = flow {
    for (i in 1..50) {
        emit(i)
        delay(100)
    }
}

@InternalCoroutinesApi
fun main() = runBlocking {
    start().collect { x ->
        println(x)
    }

}