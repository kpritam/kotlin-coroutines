import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {

    GlobalScope.launch {
        print()
        println("Last Line ==============")
    }


    Thread.sleep(2000)
}


suspend fun print(): Int {
//    delay(2000)
    one()
    println("===========================")
    return 10
}

suspend fun one(): Int {
    delay(1000)
    println("One")
    return 20
}