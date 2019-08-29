package scripts

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.cancelFutureOnCancellation
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.CompletableFuture
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

// ==================== Internal ===================
suspend fun <T> CompletableFuture<T>.await(): T {
    if (isDone) {
        // then only way to get unwrapped exception from the CompletableFuture...
        var result: T? = null
        var exception: Throwable? = null
        whenComplete { r, e ->
            result = r
            exception = e
        }
        if (exception != null) throw exception!!
        return result as T
    }
    return suspendCancellableCoroutine { cont: CancellableContinuation<T> ->
        val completionFuture = whenComplete { result, exception ->
            if (exception == null) // the future has been completed normally
                cont.resume(result)
            else // the future has completed with an exception
                cont.resumeWithException(exception)
        }
        cont.cancelFutureOnCancellation(completionFuture)
        Unit
    }
}