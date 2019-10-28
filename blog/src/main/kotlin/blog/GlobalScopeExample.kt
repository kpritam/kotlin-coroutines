package blog

import kotlinx.coroutines.*

fun main() {

    val job = GlobalScope.coroutineContext[Job]
    if (job == null) {
        println("No Job associated with GlobalScope!")
    }

    // exception thrown in this coroutine does not affect other coroutines
    GlobalScope.launch {
        delay(500)
        println("throwing exception")
        throw MyException
    }

    GlobalScope.launch {
        println("before exception...")
        delay(1000)
        println("after exception...")
    }

    Thread.sleep(1500)
}