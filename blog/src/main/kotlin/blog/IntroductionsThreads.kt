package blog

import kotlin.concurrent.thread

fun main() {
    repeat(100_000) {
        thread {
            print(".")
        }
    }
}