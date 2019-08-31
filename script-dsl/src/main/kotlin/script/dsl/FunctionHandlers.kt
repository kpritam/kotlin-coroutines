package script.dsl

class FunctionHandlers<I, O> {
    private var _handlers = mutableListOf<suspend (I) -> O>()

    fun addHandler(f: suspend (I) -> O) = _handlers.add(f)

    suspend fun execute(value: I): List<O> = _handlers.map { it(value) }
}