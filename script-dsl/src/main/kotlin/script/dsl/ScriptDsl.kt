package script.dsl

import kotlinx.coroutines.*

open class ScriptDsl {
    private var setupHandler = FunctionHandlers<Int, Int>()
    private var _cmdsHandled = FunctionHandlers<Unit, Unit>()

    fun handleSetup(f: suspend (Int) -> Int) = setupHandler.addHandler(f)

    fun cmdsHandled(f: suspend (Unit) -> Unit) = _cmdsHandled.addHandler(f)

    suspend fun execute(value: Int): List<Int> = setupHandler.execute(value)

    suspend fun executeCmdsHandled(): List<Unit> = _cmdsHandled.execute(Unit)

    suspend fun <T> par(block: () -> T): Deferred<T> = coroutineScope {
        async {
            log("Par operation")
            block()
        }
    }
}