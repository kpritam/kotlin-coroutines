package scripts

import kotlinx.coroutines.*
import java.util.concurrent.Executors

fun main() = runBlocking<Unit> {

    val ec: ExecutorCoroutineDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    val script = Script(ec)

    runScript(script, ec)

    ec.close()

}

suspend fun runScript(script: Script, ec: CoroutineDispatcher) = coroutineScope {
    withContext(ec) {
        script.execute(0).forEach(::println)
        delay(1000)
        script.executeCmdsHandled()
    }
}
