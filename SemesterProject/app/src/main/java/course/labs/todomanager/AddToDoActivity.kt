package course.labs.todomanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

class AddToDoActivity : Activity() {

    private var mTitleText: EditText? = null
    private var mDescriptionText: EditText? = null
    private var mGoalText: EditText? = null
    private var mMessageText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_todo)

        mTitleText = findViewById<View>(R.id.title) as EditText
        mDescriptionText = findViewById<View>(R.id.Description) as EditText
        mGoalText = findViewById<View>(R.id.goalEdit) as EditText
        mMessageText = findViewById<View>(R.id.Message) as EditText

        val cancelButton = findViewById<View>(R.id.cancelButton) as Button
        cancelButton.setOnClickListener {
            Log.i(TAG, "Entered cancelButton.OnClickListener.onClick()")

            finish()
        }

        val resetButton = findViewById<View>(R.id.resetButton) as Button
        resetButton.setOnClickListener {
            Log.i(TAG, "Entered resetButton.OnClickListener.onClick()")

            mTitleText!!.setText("")
            mDescriptionText!!.setText("")
            mGoalText!!.setText("")
            mMessageText!!.setText("")
        }

        val submitButton = findViewById<View>(R.id.submitButton) as Button
        submitButton.setOnClickListener {
            Log.i(TAG, "Entered submitButton.OnClickListener.onClick()")

            val itemTitle = mTitleText!!.text.toString()
            val itemDescription = mDescriptionText!!.text.toString()
            val itemGoal = mGoalText!!.text.toString()
            val itemMessage = mMessageText!!.text.toString()

            val intent = Intent()
            intent.putExtra(ToDoItem.HOBBY, itemTitle)
            intent.putExtra(ToDoItem.DESCRIPTION, itemDescription)
            intent.putExtra(ToDoItem.GOAL, itemGoal)
            intent.putExtra(ToDoItem.MESSAGE, itemMessage)
            setResult(RESULT_OK,intent)

            finish()

        }
    }

    companion object {

        private val TAG = "FinalProject"
    }
}
