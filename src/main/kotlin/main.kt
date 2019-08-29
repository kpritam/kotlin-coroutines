import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

fun main() {
    println("Hello world")

    "Hi".print()
    val info: LogLevel = LogLevel.Info("Sample", "asd")

    when (info) {
        is LogLevel.Info -> print(info.x)
        is LogLevel.Debug -> print(info.msg)
    }

}

fun String.print() {
    println("Hello $this")
}

sealed class LogLevel {
    data class Info(val msg: String, val x: String) : LogLevel()
    data class Debug(val msg: String) : LogLevel()
}


suspend fun demo(): Int {
    val result = add2(1, 2).value()
    println(result)
    return result
}

suspend fun <T> Deferred<T>.value(): T = this.await()

val add2 = { a: Int, b: Int ->
    GlobalScope.async {
        delay(1000)
        a + b
    }
}
