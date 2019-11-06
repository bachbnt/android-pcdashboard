package com.example.pcdashboard.Services
import android.util.Log
import com.example.pcdashboard.Manager.SharedPreferencesUtils
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class MyInstanceIDService : FirebaseInstanceIdService() {
    lateinit var webService: WebService
    override fun onTokenRefresh() {
        var refreshToken: String = FirebaseInstanceId.getInstance().getToken() as String
        if (SharedPreferencesUtils.loadStatusLogin(this)){
            webService = WebService.getInstance(this)
            webService.sendFCMToken(refreshToken)
            Log.i("tag","sendFCMToken $refreshToken")
        }
        SharedPreferencesUtils.saveFCMToken(this,refreshToken)
    }
}