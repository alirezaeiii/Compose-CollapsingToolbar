package com.android.sample.app.feature.list.ui

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.android.sample.app.feature.list.R
import com.android.sample.core.response.Poster
import java.util.*

/**
 * Utility class for posting notifications.
 * This class creates the notification channel (as necessary) and posts to it when requested.
 */
object Notifier {

    private const val channelId = "Default"

    fun init(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                activity.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            val existingChannel = notificationManager.getNotificationChannel(channelId)
            if (existingChannel == null) {
                // Create the NotificationChannel
                val name = activity.getString(R.string.defaultChannel)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val mChannel = NotificationChannel(channelId, name, importance)
                mChannel.description = activity.getString(R.string.notificationDescription)
                notificationManager.createNotificationChannel(mChannel)
            }
        }
    }

    fun postNotification(poster: Poster, context: Context, intent: PendingIntent) {
        val summary = NotificationCompat.Builder(context, channelId)
            .setContentTitle(poster.name)
            .setContentText(poster.release)
            .setAutoCancel(true)
            .setContentIntent(intent)
            .setSmallIcon(R.drawable.donut_with_sprinkles)
            .setGroup(poster.id.toString())
            .setGroupSummary(true)
            .build()

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(poster.name)
            .setContentText(poster.release)
            .setAutoCancel(true)
            .setContentIntent(intent)
            .setSmallIcon(R.drawable.donut_with_sprinkles)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setGroup(poster.id.toString())
            .build()

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.apply {
            notify(
                "summary ${poster.id}",
                0,
                summary
            )
            notify(
                "notification ${UUID.randomUUID()}",
                0,
                notification
            )
        }
    }
}