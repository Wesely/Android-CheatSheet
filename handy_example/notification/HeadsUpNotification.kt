package com.btse.btse.util.ui

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.widget.Toast
import com.btse.btse.R


class BtseNotification {
    companion object {
        fun showNotification(context: Context, text: String) {

            val notificationBuilder = NotificationCompat.Builder(context, "channel01")
                .setSmallIcon(R.drawable.ic_blue_checked)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_bitcoin))
                .setContentText(text)
                .setContentTitle(context.getString(R.string.channel_immediate))
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)   // heads-up
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                    Log.d("BtseNotification", "showNotification OREO")
                    val channel = NotificationChannel(
                        context.getString(R.string.channel_immediate), context.getString(R.string.channel_immediate),
                        NotificationManager.IMPORTANCE_HIGH
                    )
                    notificationBuilder.setChannelId(context.getString(R.string.channel_immediate))
                    channel.description = context.getString(R.string.channel_immediate)
                    notificationManager.createNotificationChannel(channel)
                    notificationManager.notify(System.currentTimeMillis().toInt(), notificationBuilder.build())
                }
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                    Log.d("BtseNotification", "showNotification LOLLIPOP")
                    notificationBuilder.setChannelId(context.getString(R.string.channel_immediate))
                }
                else -> {
                    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
                    notificationManager.notify(System.currentTimeMillis().toInt(), notificationBuilder.build())
                    Log.d("BtseNotification", "showNotification Toast")
                }
            }
        }

    }
}