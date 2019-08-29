package scripts

class FunctionHandlers {
    private var _handlers = mutableListOf<suspend (Int) -> Int>()

    fun addHandler(f: suspend (Int) -> Int) = _handlers.add(f)

    suspend fun execute(value: Int): List<Int> = _handlers.map { it(value) }
}