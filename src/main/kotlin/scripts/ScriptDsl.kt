package scripts

open class ScriptDsl {
    private var setupHandler = FunctionHandlers()

    fun handleSetup(f: suspend (Int) -> Int) = setupHandler.addHandler(f)

    suspend fun execute(value: Int): List<Int> = setupHandler.execute(value)
}