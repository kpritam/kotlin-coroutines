import kotlinx.coroutines.*
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

fun main() {
    runBlocking {
        Test1.start().awaitAll()
    }
    println(Test1.counter)
    Test1.end()

}

object Test1 {
    var counter = 0

    class SingleEc : ExecutorCoroutineDispatcher() {

        override fun dispatch(context: CoroutineContext, block: Runnable) {
            ec.execute(block)
        }

        val ec: ExecutorService = Executors.newSingleThreadExecutor()
        override fun close() {
            ec.shutdown()
        }

        override val executor: Executor
            get() = ec
    }

    private val singleThreaded = SingleEc()

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

