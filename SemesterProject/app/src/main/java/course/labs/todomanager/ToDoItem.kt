package course.labs.todomanager

import android.content.Intent

class ToDoItem {

    var hobby = String()
    var description = String()
    var message = String()
    var goal = String()

    internal constructor(hobby: String, description: String, goal: String, message: String) {
        this.hobby = hobby
        this.description = description
        this.goal = goal
        this.message = message
    }

    internal constructor(intent: Intent) {

        hobby = intent.getStringExtra(ToDoItem.HOBBY)
        description = intent.getStringExtra(ToDoItem.DESCRIPTION)
        goal = intent.getStringExtra(ToDoItem.GOAL)
        message = intent.getStringExtra(ToDoItem.MESSAGE)

    }

    override fun toString(): String {
        return (hobby + ITEM_SEP + description + ITEM_SEP + goal + ITEM_SEP
                + message)
    }

    fun toLog(): String {
        return ("hobby:" + hobby + ITEM_SEP + "description:" + description
                + ITEM_SEP + "message:" + message + ITEM_SEP + "goal:"
                + goal + "\n")
    }

    companion object {

        val ITEM_SEP = System.getProperty("line.separator")

        val HOBBY = "hobby"
        val DESCRIPTION = "description"
        val MESSAGE = "message"
        val GOAL = "goal"

        fun packageIntent(intent: Intent, hobby: String,
                          description: String, message: String, goal: Int) {

            intent.putExtra(ToDoItem.HOBBY, hobby)
            intent.putExtra(ToDoItem.DESCRIPTION, description)
            intent.putExtra(ToDoItem.GOAL, goal)
            intent.putExtra(ToDoItem.MESSAGE, message)

        }
    }
}
