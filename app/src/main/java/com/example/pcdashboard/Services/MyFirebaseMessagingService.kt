package com.example.pcdashboard.Services
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.pcdashboard.Activity.DashboardActivity
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
        var intent=Intent(this,DashboardActivity::class.java)
        var bundle=Bundle();
        bundle.putString("title",title)
        intent.putExtras(bundle)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        var pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT)
        NotificationsUtils.getNotificationAvatar(this,title,name,content,avatar,pendingIntent)
    }
}