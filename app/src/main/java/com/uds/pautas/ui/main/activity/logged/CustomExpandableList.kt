package com.uds.pautas.ui.main.activity.logged

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.uds.pautas.R
import com.uds.pautas.model.Schedule
import com.uds.pautas.presenter.shedule.ISchedulePresenter
import com.uds.pautas.util.MesageToast.Companion.textErrorClosedSchedule
import com.uds.pautas.util.MesageToast.Companion.textSuccessClosedSchedule
import com.uds.pautas.util.MesageToast.Companion.textSuccessReopenSchedule
import com.uds.pautas.util.MesageToast.Companion.toastShow
import com.uds.pautas.util.StatusEnum
import kotlinx.android.synthetic.main.list_group.view.*
import kotlinx.android.synthetic.main.list_item.view.*
import java.lang.Exception

class CustomExpandableListAdapter(
    private val fragment: PlaceholderFragment,
    private val expandableList: ArrayList<Schedule>,
    private val presenter: ISchedulePresenter,
    private val statusEnum: StatusEnum
) : BaseExpandableListAdapter() {

    private var lastExpandedPosition: Int = -1
    private var convertView: View? = null
    private var convertViewGroup: ViewGroup? = null
    private var schedule: Schedule? = null

    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
        return expandableList[listPosition]
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(
        listPosition: Int, expandedListPosition: Int,
        isLastChild: Boolean, convertView: View?, parent: ViewGroup
    ): View? {
        this.convertView = convertView
        this.schedule =
            getChild(listPosition, expandedListPosition) as Schedule
        if (this.convertView == null) {
            val layoutInflater = fragment.activity!!
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            this.convertView = layoutInflater.inflate(R.layout.list_item, null)
        }
        changeNameButton()
        val detail = this.convertView!!.detail as TextView
        detail.text = this.schedule!!.detail
        val author = this.convertView!!.author as TextView
        author.text = this.schedule!!.author
        closedSchedule()
        convertViewGroup = parent
        return this.convertView
    }

    private fun changeNameButton() {
        this.convertView!!.button.text = if(statusEnum == StatusEnum.CLOSE){
            fragment.activity!!.getString(R.string.reopen)
        } else {
            fragment.activity!!.getString(R.string.close)
        }
    }

    private fun closedSchedule() {
        this.convertView!!.button.setOnClickListener {
            try {
                presenter.changeStatus(this.schedule!!.id!!, if(statusEnum == StatusEnum.CLOSE) StatusEnum.OPEN else StatusEnum.CLOSE)
                toastShow(fragment.activity!!,
                    if(statusEnum == StatusEnum.OPEN)
                        textSuccessClosedSchedule(fragment.activity!!.resources)
                    else
                        textSuccessReopenSchedule(fragment.activity!!.resources)
                )
                updateList()
            } catch (e: Exception) {
                textErrorClosedSchedule(fragment.activity!!.resources)
            }
        }
    }

    private fun updateList() {
        val intent = Intent(fragment.activity!!, LoggedActivity::class.java)
        intent.putExtra("status", statusEnum.name)
        fragment.activity!!.startActivity(intent)
        fragment.activity!!.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        fragment.activity!!.finish()
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return 1
    }

    override fun getGroup(listPosition: Int): Any {
        return expandableList[listPosition]
    }

    override fun getGroupCount(): Int {
        return expandableList.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(
        listPosition: Int, isExpanded: Boolean,
        convertView: View?, parent: ViewGroup
    ): View? {
        var convertViewv = convertView
        val listTitle = getGroup(listPosition) as Schedule
        if (convertView == null) {
            val layoutInflater =
                fragment.activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertViewv = layoutInflater.inflate(R.layout.list_group, null)
        }
        val listTitleTextView = convertViewv!!.listTitle as TextView
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = listTitle.title
        val listDescriptionTextView = convertViewv.listDescription as TextView
        listDescriptionTextView.text = listTitle.description
        return convertViewv
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(
        listPosition: Int,
        expandedListPosition: Int
    ): Boolean {
        return true
    }

    override fun onGroupExpanded(groupPosition: Int) {
        super.onGroupExpanded(groupPosition)
        if(convertViewGroup != null){
            if(lastExpandedPosition != -1
                && groupPosition != lastExpandedPosition) {
                (convertViewGroup as ExpandableListView).collapseGroup(lastExpandedPosition)
            }
        }
        lastExpandedPosition = groupPosition
    }

}