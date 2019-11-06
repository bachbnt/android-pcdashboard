package com.example.pcdashboard.Services
import com.example.pcdashboard.Manager.SharedPreferencesUtils
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class MyInstanceIDService : FirebaseInstanceIdService() {
    lateinit var webService: WebService
    override fun onTokenRefresh() {
        var refreshToken: String = FirebaseInstanceId.getInstance().getToken() as String
        webService = WebService.getInstance(this)
        if (SharedPreferencesUtils.loadStatusLogin(this))
            webService.sendFCMToken(refreshToken)
    }
}