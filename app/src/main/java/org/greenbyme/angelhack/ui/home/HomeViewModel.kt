//package org.greenbyme.angelhack.ui.home
//
//import android.app.Application
//import android.util.Log
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.MutableLiveData
//import io.reactivex.schedulers.Schedulers
//import org.greenbyme.angelhack.network.ApiService
//import org.greenbyme.angelhack.ui.home.model.User
//
//class HomeViewModel(application: Application) : AndroidViewModel(application){
//    val user = MutableLiveData<User>()
//
//    fun loadUser(id : Int = 1){
//        ApiService.service.getUserInfo(id).map {
//        }.subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe({
//                Log.e("TAG_SHOW", user.toString())
//            }, {
////                Log.e("TAG_SHOW_ERR", it.toString())
//            })
//    }
//}