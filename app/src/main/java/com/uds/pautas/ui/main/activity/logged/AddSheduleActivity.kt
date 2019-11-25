package com.uds.pautas.ui.main.activity.logged

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.uds.pautas.R
import com.uds.pautas.di.component.DaggerScheduleComponent
import com.uds.pautas.di.module.ScheduleModule
import com.uds.pautas.model.Schedule
import com.uds.pautas.model.User
import com.uds.pautas.presenter.shedule.ISchedulePresenter
import com.uds.pautas.util.JwtUtils
import com.uds.pautas.util.MesageToast
import com.uds.pautas.util.MesageToast.Companion.toastShow
import kotlinx.android.synthetic.main.activity_add_shedule.*
import javax.inject.Inject

class AddSheduleActivity : AppCompatActivity() {

    @Inject lateinit var presenter: ISchedulePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_shedule)
        injectDependency()
        button_register.isEnabled = false
        setAuthor()
        buttonSchedule()
        enableButtonFinish()
        buttonBack()
    }

    private fun setAuthor() {
        input_auth.setText(JwtUtils.getUserName(this))
    }

    private fun buttonSchedule() {
        button_register.setOnClickListener {
            try {
                val schedule = validateInputs()
                schedule.user = User(JwtUtils.getJwt(this))
                presenter.save(schedule)
                toastShow(this, MesageToast.textSuccessAddSchedule(resources))
                onBackPressed()
            } catch (e: Exception) {
                toastShow(this, MesageToast.textErrorAddSchedule(resources))
            }
        }
    }

    private fun validateInputs(): Schedule {
        val author = input_auth.text.toString()
        val description = input_description.text.toString()
        val detail = input_detail.text.toString()
        val title = input_title.text.toString()
        return Schedule(title, description, detail, author)
    }

    private fun enableButtonFinish() {
        enableInputButtonFinish(input_auth)
        enableInputButtonFinish(input_description)
        enableInputButtonFinish(input_detail)
        enableInputButtonFinish(input_title)
    }

    private fun enableInputButtonFinish(input: TextInputEditText) {
        input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                validInputButtonFinish()
            }
        })
    }

    private fun validInputButtonFinish() {
        val schedule = validateInputs()
        button_register.isEnabled = schedule.validInsert()
    }

    private fun buttonBack() {
        back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, LoggedActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun injectDependency() {
        val activityComponent = DaggerScheduleComponent.builder()
            .scheduleModule(ScheduleModule(this))
            .build()
        activityComponent.inject(this)
    }
}
