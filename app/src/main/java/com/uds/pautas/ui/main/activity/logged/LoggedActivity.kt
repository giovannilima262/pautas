package com.uds.pautas.ui.main.activity.logged

import android.content.Intent
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.uds.pautas.R
import com.uds.pautas.ui.main.activity.LoginActivity
import com.uds.pautas.util.JwtUtils
import com.uds.pautas.util.StatusEnum
import kotlinx.android.synthetic.main.activity_logged.*

class LoggedActivity : AppCompatActivity() {

    private var buttonNewSheduleClick = false
    private var statusEnum: StatusEnum = StatusEnum.OPEN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var statusString = intent.getStringExtra("status")
        statusEnum = if(statusString != null) {
            StatusEnum.valueOf(statusString)
        } else {
            StatusEnum.OPEN
        }
        setContentView(R.layout.activity_logged)
        buttonNewShedule()
        buttonLogout()
    }

    private fun buttonLogout() {
        logout.setOnClickListener {
            JwtUtils.remove(this)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun buttonNewShedule() {
        button_shedule.setOnClickListener {
            if(buttonNewSheduleClick) return@setOnClickListener
            buttonNewSheduleClick = true
            val intent = Intent(this, AddSheduleActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        buttonNewSheduleClick = false
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        viewPager.currentItem = statusEnum.index
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }
}