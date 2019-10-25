package blog.scopes

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    // create 100_000 coroutines and print "."
    repeat(100_000) {
        launch {
            print(".")
        }
    }
}