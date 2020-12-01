package course.labs.todomanager

import android.content.Intent

class ToDoItem {

    var hobby = String()
    var description = String()
    var message = String()
    var goal = String()
    var count = String()

    internal constructor(hobby: String, description: String, goal: String, message: String, count:
        String) {
        this.hobby = hobby
        this.description = description
        this.goal = goal
        this.message = message
        this.count = count
    }

    internal constructor(intent: Intent) {

        hobby = intent.getStringExtra(ToDoItem.HOBBY)
        description = intent.getStringExtra(ToDoItem.DESCRIPTION)
        goal = intent.getStringExtra(ToDoItem.GOAL)
        message = intent.getStringExtra(ToDoItem.MESSAGE)
        count = intent.getStringExtra(ToDoItem.COUNT)

    }

    override fun toString(): String {
        return (hobby + ITEM_SEP + description + ITEM_SEP + goal + ITEM_SEP
                + message + ITEM_SEP + count)
    }

    fun toLog(): String {
        return ("hobby:" + hobby + ITEM_SEP + "description:" + description
                + ITEM_SEP + "message:" + message + ITEM_SEP + "goal:"
                + goal + ITEM_SEP + "count:" + count + "\n")
    }

    companion object {

        val ITEM_SEP = System.getProperty("line.separator")

        val HOBBY = "hobby"
        val DESCRIPTION = "description"
        val MESSAGE = "message"
        val GOAL = "goal"
        val COUNT = "count"


    }
}
