package blog.scopes

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job

fun main() {

    val job = GlobalScope.coroutineContext[Job]
    if (job == null) {
        println("No Job associated with GlobalScope!")
    }

}