package script.dsl

import kotlinx.coroutines.runBlocking

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun main() = runBlocking {
    log("Main runBlocking")

    val script = ScriptWithLifecycle()
    script.start().join()
    script.close()
}
