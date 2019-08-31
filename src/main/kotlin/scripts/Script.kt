package scripts

class Script : ScriptDsl() {
    private var handledCount = 0

    init {
        handleSetup { cmd ->
            log("handleSetup")

            val event = getEvent()
            val number = getSuspendableInt()

            println(number)
            println(event)
            handledCount += 1

            cmd + 1
        }

        handleSetup { cmd ->
            val event = getEvent()
            val number = getSuspendableInt()

            println(number)
            println(event)
            handledCount += 1

            cmd + 2
        }

        (1..10000).forEach {
            handleSetup { cmd ->
                handledCount += 1

                par {
                    handledCount += 1
                }
                cmd + it
            }
        }

        cmdsHandled {
            log("Total cmds handled = $handledCount")
        }
    }
}