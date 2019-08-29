package scripts

import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.Runnable
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

class StrandEc : ExecutorCoroutineDispatcher() {
    private val ec: ExecutorService = Executors.newSingleThreadExecutor()

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        ec.execute(block)
    }

    override fun close() {
        ec.shutdown()
    }

    override val executor: Executor
        get() = ec
}