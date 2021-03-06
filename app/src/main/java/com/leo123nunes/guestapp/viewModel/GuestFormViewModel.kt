package com.leo123nunes.guestapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leo123nunes.guestapp.services.model.GuestModel
import com.leo123nunes.guestapp.services.repositories.GuestRepository

class GuestFormViewModel(application: Application): AndroidViewModel(application) {
    private var context = application.applicationContext

    private var guestRepository: GuestRepository = GuestRepository.getInstance(context)

    var mGuest = MutableLiveData<GuestModel>()

    var savedGuest = MutableLiveData<Boolean>()

    fun saveGuest(id: Int, guestName: String, guestPresence: Boolean){
        var newGuest = GuestModel(id,name = guestName, presence = guestPresence)

        if(id==0){
            savedGuest.value = newGuest.presence==true
            guestRepository.save(newGuest)
        }else{
            savedGuest.value = guestRepository.update(newGuest)
        }
    }

    fun load(id: Int){
        mGuest.value = guestRepository.get(id)
    }

}