package course.labs.todomanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

class AddToDoActivity : Activity() {

    /////////// EditText Views ////////////
    private var mTitleText: EditText? = null
    private var mDescriptionText: EditText? = null
    private var mGoalText: EditText? = null
    private var mMessageText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_todo)

        /////////// findViewById's ////////////
        mTitleText = findViewById<View>(R.id.title) as EditText
        mDescriptionText = findViewById<View>(R.id.Description) as EditText
        mGoalText = findViewById<View>(R.id.goalEdit) as EditText
        mMessageText = findViewById<View>(R.id.Message) as EditText

        /////////// onClick for cancel buttons, just finish activity ////////////
        val cancelButton = findViewById<View>(R.id.cancelButton) as Button
        cancelButton.setOnClickListener {
            Log.i(TAG, "Entered cancelButton.OnClickListener.onClick()")

            finish()
        }

        /////////// onClick for cancel buttons, sets all text to blank ////////////
        val resetButton = findViewById<View>(R.id.resetButton) as Button
        resetButton.setOnClickListener {
            Log.i(TAG, "Entered resetButton.OnClickListener.onClick()")

            mTitleText!!.setText("")
            mDescriptionText!!.setText("")
            mGoalText!!.setText("")
            mMessageText!!.setText("")
        }

        /////////// onClick for submit buttons, makes intent and send intent ////////////
        val submitButton = findViewById<View>(R.id.submitButton) as Button
        submitButton.setOnClickListener {
            Log.i(TAG, "Entered submitButton.OnClickListener.onClick()")

            val intent = Intent()
            intent.putExtra(ToDoItem.HOBBY, mTitleText!!.text.toString())
            intent.putExtra(ToDoItem.DESCRIPTION, mDescriptionText!!.text.toString())
            intent.putExtra(ToDoItem.GOAL, mGoalText!!.text.toString())
            intent.putExtra(ToDoItem.MESSAGE, mMessageText!!.text.toString())
            intent.putExtra(ToDoItem.COUNT, "0")
            setResult(RESULT_OK,intent)
            finish()
        }
    }

    companion object {
        private val TAG = "FinalProject"
    }
}
