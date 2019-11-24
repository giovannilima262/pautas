package com.uds.pautas.ui.main.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.uds.pautas.R
import com.uds.pautas.ui.main.activity.logged.LoggedActivity
import com.uds.pautas.util.JwtUtils

class MainActivity: AppCompatActivity() {

    private val DELAY_SPLASH: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            val jwt = JwtUtils.get(this)
            val intent = jwt?.let {
                Intent(this, LoggedActivity::class.java)
            } ?: Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, DELAY_SPLASH)

    }


}