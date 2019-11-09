package com.example.pcdashboard.Services
import android.util.Log
import com.example.pcdashboard.Manager.NotificationsUtils
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(p0: RemoteMessage) {
        var data=p0.data
        var title:String=data.get("title") as String
        var name:String?=data.get("name")
        var content:String=data.get("content") as String
        var avatar:String?=data.get("avatar")
        Log.i("tag","onMessageReceived")
        NotificationsUtils.getNotificationAvatar(this,title,name,content,avatar)
    }
}