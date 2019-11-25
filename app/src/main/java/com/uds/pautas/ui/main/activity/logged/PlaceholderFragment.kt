package com.uds.pautas.ui.main.activity.logged

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.uds.pautas.R
import com.uds.pautas.di.component.DaggerScheduleComponent
import com.uds.pautas.di.module.ScheduleModule
import com.uds.pautas.model.Schedule
import com.uds.pautas.presenter.shedule.ISchedulePresenter
import com.uds.pautas.util.JwtUtils
import com.uds.pautas.util.StatusEnum
import kotlinx.android.synthetic.main.fragment_logged.view.*
import javax.inject.Inject

class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var expandableListAdapter: ExpandableListAdapter? = null
    private var expandableList: ArrayList<Schedule>? = null

    @Inject lateinit var presenter: ISchedulePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }

        injectDependency()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_logged, container, false)
        screenList(root)
        return root
    }

    private fun screenList(inflater: View?) {
        val expandableListView = inflater!!.expandableListView
        expandableList = findSchedules()
        expandableListAdapter = CustomExpandableListAdapter(this, expandableList!!, presenter, StatusEnum.values()[(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1) - 1])
        expandableListView.setAdapter(expandableListAdapter)
    }

    private fun findSchedules(): ArrayList<Schedule> {
        return presenter.findByUserStatus(JwtUtils.getJwt(this.activity!!)!!, StatusEnum.values()[(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1) - 1])
    }

    private fun injectDependency() {
        val activityComponent = DaggerScheduleComponent.builder()
            .scheduleModule(ScheduleModule(this.activity!!))
            .build()
        activityComponent.inject(this)
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}