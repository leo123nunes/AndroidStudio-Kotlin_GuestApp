package com.leo123nunes.guestapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.leo123nunes.guestapp.R
import com.leo123nunes.guestapp.constants.GuestConstants
import com.leo123nunes.guestapp.viewModel.GuestFormViewModel
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: GuestFormViewModel

    private var mGuestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observers()
        loadData()

        button_presence.isChecked = true
    }

    fun observers(){
        viewModel.savedGuest.observe(this, Observer{
            if(it == true){
                Toast.makeText(applicationContext,"Convidado adicionado.",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext,"Convidado não confirmado.",Toast.LENGTH_LONG).show()
            }
            finish()
        })

        viewModel.mGuest.observe(this, Observer {
            guestName.setText(it.name)
            if(it.presence){
                button_presence.isChecked = true
            }else{
                button_absence.isChecked = true
            }
        })
    }

    fun setListeners(){
        buttonSave.setOnClickListener(this)
    }
    override fun onClick(v: View) {
        var id = v.id
        if (id == R.id.buttonSave){
            viewModel.saveGuest(mGuestId, guestName.text.toString(),button_presence.isChecked())
        }
    }

    private fun loadData(){
        val bundle = intent.extras

        if(bundle!=null){
            mGuestId = bundle.getInt(GuestConstants.GUESTID)

            viewModel.load(mGuestId)
        }
    }
}