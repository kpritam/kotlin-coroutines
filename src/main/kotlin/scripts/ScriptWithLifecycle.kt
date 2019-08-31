package scripts

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

class ScriptWithLifecycle : CoroutineScope {
    private val job = Job()
    private val ec = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    fun close() {
        job.cancel()
        ec.close()
    }

    override val coroutineContext: CoroutineContext
        get() = job + ec

    fun start() = launch {
        log("Launching ScriptWithLifecycle")

        val script = Script()
        script.execute(0).forEach { log(it.toString()) }
        delay(1000)
        script.executeCmdsHandled()
    }
}