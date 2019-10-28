package blog

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    var counter = 0
    suspend fun incrment() {
        delay(10)
        counter += 1
    }

    // @Test
    fun `should able to increment counter`() = runBlocking {
        repeat(50) { incrment() }
        assert(counter == 50) { "Assertion failed, expected=100 but actual=$counter" }
    }

    `should able to increment counter`()
}