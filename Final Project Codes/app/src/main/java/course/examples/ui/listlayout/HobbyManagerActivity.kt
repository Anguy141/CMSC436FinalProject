@file:Suppress("DEPRECATION")

package course.examples.ui.listlayout

import android.annotation.SuppressLint
import android.app.ListActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import java.io.*
import java.text.ParseException

class HobbyManagerActivity : ListActivity() {
    private lateinit var mAdapter: HobbyListAdapter

    @SuppressLint("InflateParams")
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
            startActivityForResult(addToDoActivityIntent, ADD_HOBBY_ITEM_REQUEST)
        }

        listView.adapter = mAdapter

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        Log.i(TAG, "Entered onActivityResult()")

        if (resultCode == RESULT_OK && requestCode == ADD_HOBBY_ITEM_REQUEST){
            val newItem = data?.let { HobbyItem(it) }
            if (newItem != null) {
                Log.i(TAG, newItem.toLog())
                mAdapter.add(newItem)
            }
        }
    }

    public override fun onResume() {
        super.onResume()

        // Load saved HobbyItems, if necessary

        if (mAdapter.count == 0)
            loadItems()
    }

    override fun onPause() {
        super.onPause()

        // Save HobbyItems

        saveItems()

    }

    /////////// option menu to switch to finder and delete ////////////
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menu.add(Menu.NONE, MENU_DELETE, Menu.NONE, "Delete all")
        menu.add(Menu.NONE, MENU_SWITCH, Menu.NONE, "Switch to hobby finder")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            MENU_DELETE -> {
                mAdapter.clear()
                true
            }
            MENU_SWITCH -> {
                val searcherIntent = Intent(
                        this,
                        HobbySearchActivity::class.java
                )
                startActivity(searcherIntent)
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    // Load stored HobbyItems, taken from the UILab 4
    private fun loadItems() {
        var reader: BufferedReader? = null
        try {
            val fis = openFileInput(FILE_NAME)
            reader = BufferedReader(InputStreamReader(fis))

            var hobby: String?
            var descriptor: String?
            var goal: String?
            var message: String?
            var count: String?
            var color: String?

            do {
                hobby = reader.readLine()
                if (hobby == null)
                    break
                descriptor = reader.readLine()
                goal = reader.readLine()
                message = reader.readLine()
                count = reader.readLine()
                color = reader.readLine()
                mAdapter.add(HobbyItem(hobby, descriptor,
                        goal, message, count, color))

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

    // Save HobbyItems to file, taken from the UILab 4
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

        private const val ADD_HOBBY_ITEM_REQUEST = 0
        private const val FILE_NAME = "HobbyManagerActivityData.txt"
        private const val TAG = "Final Project"

        // IDs for menu items
        private const val MENU_DELETE = Menu.FIRST
        private const val MENU_SWITCH = Menu.FIRST+1
    }
}