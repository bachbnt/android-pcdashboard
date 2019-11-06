package com.example.pcdashboard.Services
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(p0: RemoteMessage) {
        var data=p0.data
        Log.i("tag","fcm request: ${data.get("title")}")
    }
}