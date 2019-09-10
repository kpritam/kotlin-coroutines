package examples


fun fib() =
    generateSequence(Pair(0, 1), {
        Pair(it.second, it.first + it.second)
    }).map { it.first }

fun main() {
    fib().take(10).forEach(::println)
}