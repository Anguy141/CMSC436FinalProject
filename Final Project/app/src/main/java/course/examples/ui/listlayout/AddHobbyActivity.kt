package course.examples.ui.listlayout
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.add_hobby.*

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
            spinner!!.setSelection(0)
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
                intent.putExtra(HobbyItem.COLOR,spinner.selectedItem.toString())
                setResult(RESULT_OK, intent)
                finish()
            } else {
                openDialog()
            }
        }

        // Indicates whether spinner was touched by user
        wasTouched = false

        // Get a reference to the Spinner
        val spinner = findViewById<Spinner>(R.id.spinner)

        // Create an Adapter that holds a list of colors
        val adapter = ArrayAdapter.createFromResource(
                this, R.array.colors, R.layout.color_picker
        )

        // Set the Adapter for the spinner
        spinner.adapter = adapter

        // Set an onTouchListener on the spinner because
        // onItemSelected() can be called multiple times by framework
        spinner.setOnTouchListener { v: View, _ ->
            wasTouched = true
            v.performClick()
            false
        }

        // Set an onItemSelectedListener on the spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>, view: View,
                    pos: Int, id: Long
            ) {

                if (wasTouched) {
                    wasTouched = false
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
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
        private var wasTouched: Boolean = false
    }
}