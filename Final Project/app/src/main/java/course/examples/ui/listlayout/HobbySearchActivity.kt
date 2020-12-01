package course.examples.ui.listlayout

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import android.widget.AdapterView.OnItemClickListener

class HobbySearchActivity : Activity() {

    // Used by WikiHowSearchTask.kt //
    private lateinit var mWikiSearch : WikiHowSearchTask
    private lateinit var mWikiToast : Toast
    private var mWikiDataMap = hashMapOf<String, String>()
    ///////////////////////////////////////////////

    private lateinit var hobbyListView : ListView
    private lateinit var hobbyArrayList : ArrayList<String>
    private lateinit var searchBar : EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout)

        hobbyListView = findViewById<ListView>(R.id.list)
        searchBar = findViewById<EditText>(R.id.search)
        val searchButton = findViewById<Button>(R.id.button)

        hobbyArrayList = ArrayList<String>()
        if (savedInstanceState != null) {
            val savedHobbyList : ArrayList<String> = savedInstanceState.getStringArrayList("HOBBY_LIST") as ArrayList<String>
            val savedDataMap : HashMap<String, String> = savedInstanceState.getSerializable("DATA_MAP") as HashMap<String, String>
            hobbyArrayList.addAll(savedHobbyList)
            mWikiDataMap = savedDataMap
        }

        // Set the adapter on this ListActivity's built-in ListView
        hobbyListView.adapter = ArrayAdapter(
                this,
                R.layout.list_item,
                hobbyArrayList
        )

        // Used by WikiHowSearchTask.kt //
        mWikiToast = Toast.makeText(applicationContext, "Searching...", Toast.LENGTH_SHORT)
        ///////////////////////////////////////////////

        // Set a setOnItemClickListener on the ListView
        hobbyListView.onItemClickListener = OnItemClickListener { _ , _, pos, _ ->
            val wikiWebIntent = Intent(applicationContext, WikiWebViewActivity::class.java)
            val selectedHobby : String = hobbyListView.getItemAtPosition(pos) as String
            val selectedHobbyHref : String? = mWikiDataMap[selectedHobby]
            wikiWebIntent.putExtra("HobbyHref", selectedHobbyHref)
            startActivity(wikiWebIntent)
        }

        // Set a setOnItemClickListener on the the button for parsing and web searching
        searchButton.setOnClickListener {
            mWikiToast!!.setText("Searching...")
            mWikiToast.show()
            mWikiSearch = WikiHowSearchTask(applicationContext)
            mWikiSearch.setListViewActivityVariables(mWikiDataMap, hobbyListView, hobbyArrayList, mWikiToast, searchBar.text.toString())
            mWikiSearch.start()
            searchBar.setText("")
            hideKeyboard()
        }
    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putStringArrayList("HOBBY_LIST", hobbyArrayList)
        savedInstanceState.putSerializable("DATA_MAP", mWikiDataMap)

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(savedInstanceState)
    }

    fun Activity.hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(searchBar.windowToken, 0);
    }
}