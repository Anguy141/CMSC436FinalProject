package course.examples.ui.listlayout

import android.content.Intent
import android.os.Bundle
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.FragmentActivity

class ListViewActivity : FragmentActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout)

        val listView = findViewById<ListView>(R.id.list)
        val searchBar = findViewById<EditText>(R.id.search)
        val searchButton = findViewById<Button>(R.id.button)

        // Create a new Adapter containing a list of hobbies
        // Set the adapter on this ListActivity's built-in ListView
        val hobbyArray = resources.getStringArray(R.array.hobby)
        hobbyArray.sort()

        listView.adapter = ArrayAdapter(
            this,
            R.layout.list_item,
            hobbyArray
        )

        // Set a setOnItemClickListener on the ListView
        listView.onItemClickListener = OnItemClickListener { _ , _, pos, _ ->
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("hobby", listView.getItemIdAtPosition(pos))
            startActivity(intent)
        }

        // Set a setOnItemClickListener on the the button for parsing and web searching
        searchButton.setOnClickListener {

        }
    }
}