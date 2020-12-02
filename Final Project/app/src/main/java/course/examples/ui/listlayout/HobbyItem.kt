package course.examples.ui.listlayout

import android.content.Intent

class HobbyItem {
    var hobby = String()
    var description = String()
    var message = String()
    var goal = String()
    var count = String()
    var color = String()

    internal constructor(hobby: String, description: String, goal: String, message: String, count:
    String, color: String) {
        this.hobby = hobby
        this.description = description
        this.goal = goal
        this.message = message
        this.count = count
        this.color = color
    }

    internal constructor(intent: Intent) {

        hobby = intent.getStringExtra(HobbyItem.HOBBY).toString()
        description = intent.getStringExtra(HobbyItem.DESCRIPTION).toString()
        goal = intent.getStringExtra(HobbyItem.GOAL).toString()
        message = intent.getStringExtra(HobbyItem.MESSAGE).toString()
        count = intent.getStringExtra(HobbyItem.COUNT).toString()
        color = intent.getStringExtra(HobbyItem.COLOR).toString()

    }

    override fun toString(): String {
        return (hobby + ITEM_SEP + description + ITEM_SEP + goal + ITEM_SEP
                + message + ITEM_SEP + count + ITEM_SEP + color)
    }

    fun toLog(): String {
        return ("hobby:" + hobby + ITEM_SEP + "description:" + description
                + ITEM_SEP + "message:" + message + ITEM_SEP + "goal:"
                + goal + ITEM_SEP + "count:" + count + "color:" + color +"\n")
    }

    companion object {

        val ITEM_SEP = System.getProperty("line.separator")

        val HOBBY = "hobby"
        val DESCRIPTION = "description"
        val MESSAGE = "message"
        val GOAL = "goal"
        val COUNT = "count"
        val COLOR = "color"

    }
}