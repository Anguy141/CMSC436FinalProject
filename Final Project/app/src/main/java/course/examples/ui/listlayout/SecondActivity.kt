package course.examples.ui.listlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    //TextView for hobby text
    private lateinit var textView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        textView = findViewById<TextView>(R.id.textView)

        //retriving the intent extra string
        val b = intent.extras
        val hobby = b!!.getString("hobby") as String

        Log.i("habit/hobby builder", hobby)

        textView.text = hobby

    }
}