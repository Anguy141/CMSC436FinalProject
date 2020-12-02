package course.examples.ui.listlayout

import android.annotation.SuppressLint
import android.app.AlertDialog
import java.util.ArrayList

import android.content.Context
import android.graphics.Color
import android.provider.Settings.Global.getString
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.hobby_item.view.*

class HobbyListAdapter(private val mContext: Context) : BaseAdapter() {
    private val mItems = ArrayList<HobbyItem>()

    // Add a ToDoItem to the adapter
    // Notify observers that the data set has changed

    fun add(item: HobbyItem) {

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

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

        val currItem: HobbyItem = getItem(position) as HobbyItem
        val viewHolder = ViewHolder()
        var count = currItem.count.toInt()

        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.hobby_item, parent, false)

        /////////// Initialize the Views and Buttons ////////////
        viewHolder.mTitleView=view.titleView
        viewHolder.mDescriptionView=view.DescriptionView
        viewHolder.mProgressView=view.ProgressView
        viewHolder.mCongratulationView=view.congratulationMessage
        viewHolder.mItemLayout=view.RelativeLayout1

        viewHolder.mResetButton = view.findViewById(R.id.Reset)
        viewHolder.mIncrementButton = view.findViewById(R.id.Increment)
        viewHolder.mDeleteButton = view.findViewById(R.id.Delete)

        /////////// sets color of view background ////////////
        when (currItem.color){
            "white" -> view.setBackgroundColor(Color.parseColor("#ffffff"))
            "red" -> view.setBackgroundColor(Color.parseColor("#FF0000"))
            "orange" -> view.setBackgroundColor(Color.parseColor("#FF7200"))
            "yellow" -> view.setBackgroundColor(Color.parseColor("#FBFF00"))
            "green" -> view.setBackgroundColor(Color.parseColor("#04FF00"))
            "blue" -> view.setBackgroundColor(Color.parseColor("#002BFF"))
            else -> view.setBackgroundColor(Color.parseColor("#ffffff"))
        }

        /////////// onClick for reset button ////////////
        viewHolder.mResetButton.setOnClickListener {
            Log.i(TAG, "Entered mResetButton.OnClickListener.onClick()")
            count = 0
            currItem.count = count.toString()
            /////////// displays all messages ////////////
            viewHolder.mCongratulationView!!.text = ""
            viewHolder.mTitleView!!.text = currItem.hobby
            viewHolder.mDescriptionView!!.text = currItem.description
            viewHolder.mProgressView!!.text = mContext.getString(R.string.Progress_string,
                    count,currItem.goal)
            Log.i(TAG, "count = $count")
        }

        /////////// onClick for increment button ////////////
        viewHolder.mIncrementButton.setOnClickListener {
            Log.i(TAG, "Entered mIncrementButton.OnClickListener.onClick()")
            count += 1
            currItem.count = count.toString()
            /////////// displays congratulation message ////////////
            if (count >= (currItem.goal).toInt()) {
                viewHolder.mTitleView!!.text = ""
                viewHolder.mDescriptionView!!.text = ""
                viewHolder.mProgressView!!.text = ""
                viewHolder.mCongratulationView!!.text = mContext.getString(R.string.finished_string,
                        currItem.message)
                Log.i(TAG, "Congratulation Message Displayed")
            } else {
                viewHolder.mProgressView!!.text = mContext.getString(R.string.Progress_string,
                        count,currItem.goal)
            }
            Log.i(TAG, "count = $count")
        }

        /////////// onClick for delete button ////////////
        viewHolder.mDeleteButton.setOnClickListener {
            Log.i(TAG, "Entered mDeleteButton.OnClickListener.onClick()")
            mItems.removeAt(position)
            notifyDataSetChanged()
        }

        /////////// sets the text views ////////////
        if (count >= (currItem.goal).toInt()) {
            /////////// displays congratulation message ////////////
            viewHolder.mTitleView!!.text = ""
            viewHolder.mDescriptionView!!.text = ""
            viewHolder.mProgressView!!.text = ""
            viewHolder.mCongratulationView!!.text = mContext.getString(R.string.finished_string,
                    currItem.message)
        } else {
            viewHolder.mTitleView!!.text = currItem.hobby
            viewHolder.mDescriptionView!!.text = currItem.description
            viewHolder.mProgressView!!.text = mContext.getString(R.string.Progress_string,
                    count,currItem.goal)
        }

        return viewHolder.mItemLayout

    }


    internal class ViewHolder {
        var mItemLayout: RelativeLayout? = null
        var mTitleView: TextView? = null
        var mDescriptionView: TextView? = null
        var mProgressView: TextView? = null
        var mCongratulationView: TextView? = null
        lateinit var mResetButton:Button
        lateinit var mIncrementButton:Button
        lateinit var mDeleteButton:Button
    }

    companion object {
        private const val TAG = "FinalProject"
    }
}