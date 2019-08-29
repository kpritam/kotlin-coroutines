package scripts

import java.util.concurrent.CompletableFuture

class Script(val strandEc: StrandEc) : ScriptDsl() {
    private var cmdsHandled = 0

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

        (1..10000).forEach {
            handleSetup { cmd ->
                cmdsHandled += 1
                longComp().thenAccept {
                    cmdsHandled += 1
                }
                cmd + it
            }
        }

        cmdsHandled {
            println("Total cmds handled = $cmdsHandled")
        }
    }

    private fun longComp(): CompletableFuture<Void> {
        val runnable = Runnable { Unit }

        return CompletableFuture.runAsync(runnable, strandEc.executor)
    }

}