package scripts

import kotlinx.coroutines.*

fun main() = runBlocking {
    val strandEc = StrandEc()
    val script = Script(strandEc)

    runScript(script, strandEc)

    strandEc.close()
}

suspend fun runScript(script: Script, strandEc: StrandEc) = coroutineScope {
    withContext(strandEc) {
        script.execute(0).forEach(::println)
        delay(1000)
        script.executeCmdsHandled()
    }
}
