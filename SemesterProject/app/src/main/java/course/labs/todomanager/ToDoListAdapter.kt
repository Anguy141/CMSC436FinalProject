package course.labs.todomanager

import android.app.AlertDialog
import java.util.ArrayList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.todo_item.view.*

class ToDoListAdapter(private val mContext: Context) : BaseAdapter() {

    private val mItems = ArrayList<ToDoItem>()

    // Add a ToDoItem to the adapter
    // Notify observers that the data set has changed

    fun add(item: ToDoItem) {

        mItems.add(item)
        notifyDataSetChanged()

    }

    // Clears the list adapter of all items.

    fun clear() {

        mItems.clear()
        notifyDataSetChanged()

    }

    // Returns the number of ToDoItems

    override fun getCount(): Int {

        return mItems.size

    }

    // Retrieve ToDoItems at index pos

    override fun getItem(pos: Int): Any {

        return mItems[pos]

    }

    // Get the ID for the ToDoItem
    // In this case it's just the position

    override fun getItemId(pos: Int): Long {

        return pos.toLong()

    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

        val currItem = getItem(position) as ToDoItem
        val viewHolder: ViewHolder = ViewHolder()
        var count: Int = 0

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.todo_item, parent, false)

        viewHolder.mTitleView=view.titleView
        viewHolder.mDescriptionView=view.DescriptionView
        viewHolder.mProgressView=view.ProgressView
        viewHolder.mItemLayout=view.RelativeLayout1

        view.findViewById<Button>(R.id.resetButton).setOnClickListener (View.OnClickListener {
            fun onClick(v: View){
                count = 0
            }
        })

        view.findViewById<Button>(R.id.Increment).setOnClickListener (View.OnClickListener {
            fun onClick(v: View){
                count += 1
            }

            if (count == (currItem.goal).toInt()){
                AlertDialog.Builder(mContext)
                        .setTitle(R.string.congratulation_title)
                        .setMessage(currItem.message)
                        .setCancelable(true)
                        .create()
                        .show()
            }

            count = 0
        })

        viewHolder.mTitleView!!.text = currItem.hobby
        viewHolder.mDescriptionView!!.text = currItem.description
        viewHolder.mProgressView!!.text = "${count}/${currItem.goal}"

        return viewHolder.mItemLayout

    }


    internal class ViewHolder {
        var position: Int = 0
        var mItemLayout: RelativeLayout? = null
        var mTitleView: TextView? = null
        var mDescriptionView: TextView? = null
        var mProgressView: TextView? = null
    }

    companion object {
        private val TAG = "Lab-UserInterface"
    }
}
