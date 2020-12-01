package course.examples.ui.listlayout

import android.content.Intent
import android.os.Bundle
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.FragmentActivity

class ListViewActivity : FragmentActivity() {

    // Used by WikiHowSearchTask.kt //
    private lateinit var mWikiSearch : WikiHowSearchTask
    private lateinit var mWikiToast : Toast
    private var mWikiDataMap = mutableMapOf<String, String>()
    ///////////////////////////////////////////////

    private lateinit var listView : ListView
    private lateinit var hobbyArrayList : ArrayList<String>
    private lateinit var searchBar : EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout)

        listView = findViewById<ListView>(R.id.list)
        searchBar = findViewById<EditText>(R.id.search)
        val searchButton = findViewById<Button>(R.id.button)

        // Create a new Adapter containing a list of hobbies
        // Set the adapter on this ListActivity's built-in ListView
        hobbyArrayList = resources.getStringArray(R.array.hobby).toCollection(ArrayList<String>())
        hobbyArrayList.sort()

        listView.adapter = ArrayAdapter(
            this,
            R.layout.list_item,
            hobbyArrayList
        )

        // Used by WikiHowSearchTask.kt //
        mWikiToast = Toast.makeText(applicationContext, "Searching...", Toast.LENGTH_SHORT)
        ///////////////////////////////////////////////

        // Set a setOnItemClickListener on the ListView
        listView.onItemClickListener = OnItemClickListener { _ , _, pos, _ ->
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("hobby", listView.getItemAtPosition(pos).toString())
            startActivity(intent)
        }

        // Set a setOnItemClickListener on the the button for parsing and web searching
        searchButton.setOnClickListener {
            mWikiToast!!.setText("Searching...")
            mWikiToast.show()
            mWikiSearch = WikiHowSearchTask(applicationContext)
            mWikiSearch.setListViewActivityVariables(mWikiDataMap, listView, hobbyArrayList, mWikiToast, searchBar.text.toString())
            mWikiSearch.start()
        }
    }
}