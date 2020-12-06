package course.examples.ui.listlayout

import android.content.Intent

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
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

        hobby = intent.getStringExtra(HOBBY).toString()
        description = intent.getStringExtra(DESCRIPTION).toString()
        goal = intent.getStringExtra(GOAL).toString()
        message = intent.getStringExtra(MESSAGE).toString()
        count = intent.getStringExtra(COUNT).toString()
        color = intent.getStringExtra(COLOR).toString()

    }

    override fun toString(): String {
        return (hobby + ITEM_SEP + description + ITEM_SEP + goal + ITEM_SEP
                + message + ITEM_SEP + count + ITEM_SEP + color)
    }

    fun toLog(): String {
        return ("hobby:" + hobby + ITEM_SEP + "description:" + description
                + ITEM_SEP + "message:" + message + ITEM_SEP + "goal:"
                + goal + ITEM_SEP + "count:" + count + ITEM_SEP + "color:" + color +"\n")
    }

    companion object {

        val ITEM_SEP: String = System.getProperty("line.separator")

        const val HOBBY = "hobby"
        const val DESCRIPTION = "description"
        const val MESSAGE = "message"
        const val GOAL = "goal"
        const val COUNT = "count"
        const val COLOR = "color"

    }
}