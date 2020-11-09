package course.examples.ui.listlayout

import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity

class ListViewActivity : FragmentActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout)

        val listView = findViewById<ListView>(R.id.list)
        // Create a new Adapter containing a list of colors
        // Set the adapter on this ListActivity's built-in ListView
        val hobbyArray = arrayOf("Running", "Walking", "Cooking", "Baking", "Knitting",
                "Bird Watching", "Soccer", "Painting", "Basketball", "Wood Carving", "Coding",
                "Sewing", "Singing", "Dancing")
        hobbyArray.sort()

        listView.adapter = ArrayAdapter(
            this, R.layout.list_item,
                hobbyArray
        )

        // Enable filtering when the user types in the virtual keyboard
        listView.isTextFilterEnabled = true

        // Set a setOnItemClickListener on the ListView
        listView.onItemClickListener = OnItemClickListener { _, view, _, _ ->
            // Display a Toast message indicting the selected item
            Toast.makeText(
                applicationContext,
                (view as TextView).text, Toast.LENGTH_SHORT
            ).show()
        }
    }
}