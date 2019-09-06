package examples

import kotlin.properties.Delegates

fun main() {
    println("================= observable ===============")
    var name: String by Delegates.observable("Empty") { _, old, new ->
        println("[$old -> $new]")
    }

    name = "Pritam"
    name = "Kadam"

    println("================= vetoable ===============")
    var listMoreThan3Items: List<String> by Delegates.vetoable(emptyList()) { _, _, new ->
        if (new.size <= 3) return@vetoable false
        println("Updating")
        true
    }

    println("Start")
    listMoreThan3Items = listOf("A", "B", "C")
    println(listMoreThan3Items)
    listMoreThan3Items = listOf("A", "B", "C", "D")
    println(listMoreThan3Items)
    listMoreThan3Items = listOf("A", "B")
    println(listMoreThan3Items)

}