package scripts

class Script : ScriptDsl() {
    var cmdsHandled = 0

    init {

        handleSetup { cmd ->
            val event = getEvent()
            val number = getSuspendableInt()

            println(number)
            println(event)
            cmdsHandled += 1

            cmd + 1
        }

        handleSetup { cmd ->
            val event = getEvent()
            val number = getSuspendableInt()

            println(number)
            println(event)
            cmdsHandled += 1

            cmd + 2
        }

        cmdsHandled {
            println(" cmdsHandled Called ")
            println("Total cmds handled = $cmdsHandled")
        }


    }

}