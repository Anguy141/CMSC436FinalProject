package course.examples.ui.listlayout

//import android.R
import android.app.ListActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import java.io.*
import java.text.ParseException
import java.util.*
import kotlin.collections.ArrayList

class HobbyManagerActivity : ListActivity() {
    internal lateinit var mAdapter: HobbyListAdapter

    public override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "Entered onCreate()")
        super.onCreate(savedInstanceState)

        mAdapter = HobbyListAdapter(applicationContext)

        listView.setFooterDividersEnabled(true)

        val myInflater = layoutInflater
        val view = myInflater.inflate(R.layout.footer_view, null, false)

        listView.addFooterView(view)

        view.setOnClickListener{
            val addToDoActivityIntent = Intent(
                    this,
                    AddHobbyActivity::class.java
            )
            startActivityForResult(addToDoActivityIntent, ADD_TODO_ITEM_REQUEST)
        }

        listView.adapter = mAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        Log.i(TAG, "Entered onActivityResult()")

        if (resultCode == RESULT_OK && requestCode == ADD_TODO_ITEM_REQUEST){
            val newItem = data?.let { HobbyItem(it) }
            if (newItem != null) {
                Log.i(TAG, newItem.toLog())
                mAdapter.add(newItem)
            }
        }
    }

    public override fun onResume() {
        super.onResume()

        // Load saved ToDoItems, if necessary

        if (mAdapter.count == 0)
            loadItems()
    }

    override fun onPause() {
        super.onPause()

        // Save ToDoItems

        saveItems()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)

        menu.add(Menu.NONE, MENU_DELETE, Menu.NONE, "Delete all")
        menu.add(Menu.NONE, MENU_DUMP, Menu.NONE, "Dump to log")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            MENU_DELETE -> {
                mAdapter.clear()
                return true
            }
            MENU_DUMP -> {
                dump()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun dump() {
        for (i in 0 until mAdapter.count) {
            val data = (mAdapter.getItem(i) as HobbyItem).toLog()
            Log.i(TAG,
                    "Item " + i + ": " + data.replace(HobbyItem.ITEM_SEP, ","))
        }
    }

    // Load stored ToDoItems
    private fun loadItems() {
        var reader: BufferedReader? = null
        try {
            val fis = openFileInput(FILE_NAME)
            reader = BufferedReader(InputStreamReader(fis))

            var hobby: String? = null
            var descriptor: String? = null
            var goal: String? = null
            var message: String? = null
            var count: String? = null

            do {
                hobby = reader.readLine();
                if (hobby == null)
                    break
                descriptor = reader.readLine()
                goal = reader.readLine()
                message = reader.readLine()
                count = reader.readLine()
                mAdapter.add(HobbyItem(hobby, descriptor,
                        goal, message, count))

            }
            while (true)

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ParseException) {
            e.printStackTrace()
        } finally {
            if (null != reader) {
                try {
                    reader.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
    }

    // Save ToDoItems to file
    private fun saveItems() {
        var writer: PrintWriter? = null
        try {
            val fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
            writer = PrintWriter(BufferedWriter(OutputStreamWriter(
                    fos)))

            for (idx in 0 until mAdapter.count) {

                writer.println(mAdapter.getItem(idx))

            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            writer?.close()
        }
    }


    companion object {

        private val ADD_TODO_ITEM_REQUEST = 0
        private val FILE_NAME = "TodoManagerActivityData.txt"
        private val TAG = "Final Project"

        // IDs for menu items
        private val MENU_DELETE = Menu.FIRST
        private val MENU_DUMP = Menu.FIRST + 1
    }
}