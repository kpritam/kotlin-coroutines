package scripts

class Script : ScriptDsl() {
    init {
        handleSetup { cmd ->
            val event = getEvent()
            println(getSuspendableInt())
            println(event)
            cmd + 1
        }

        handleSetup { cmd ->
            val event = getEvent()
            println(getSuspendableInt())
            println(event)
            cmd + 2
        }
    }
}