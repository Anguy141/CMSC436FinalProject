package course.examples.ui.listlayout
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

class AddHobbyActivity : Activity(){
    /////////// EditText Views ////////////
    private var mTitleText: EditText? = null
    private var mDescriptionText: EditText? = null
    private var mGoalText: EditText? = null
    private var mMessageText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_hobby)

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
            if (mTitleText!!.text.isNotEmpty() && mDescriptionText!!.text.isNotEmpty()
                    && mGoalText!!.text.isNotEmpty() && mMessageText!!.text.isNotEmpty()){
                val intent = Intent()
                intent.putExtra(HobbyItem.HOBBY, mTitleText!!.text.toString())
                intent.putExtra(HobbyItem.DESCRIPTION, mDescriptionText!!.text.toString())
                intent.putExtra(HobbyItem.GOAL, mGoalText!!.text.toString())
                intent.putExtra(HobbyItem.MESSAGE, mMessageText!!.text.toString())
                intent.putExtra(HobbyItem.COUNT, "0")
                setResult(RESULT_OK, intent)
                finish()
            } else {
                openDialog()
            }
        }
    }

    // opens the dialog using AlertDialog
    private fun openDialog(){
        AlertDialog.Builder(this)
                .setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_string)
                .setCancelable(true)
                .create()
                .show()
    }

    companion object {
        private val TAG = "FinalProject"
    }
}