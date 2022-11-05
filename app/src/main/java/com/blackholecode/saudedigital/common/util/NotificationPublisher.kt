package com.blackholecode.saudedigital.common.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.main.view.MainActivity

class NotificationPublisher : BroadcastReceiver() {

    companion object {
        const val NOTIFICATION_ID = "notification_id"
    }

    private val titleNotifications =
        arrayOf("Ei, não se esquece de mim...", "Tá na hora devoltar em", "Vem, vem, vem")
    private val messageNotifications = arrayOf(
        "Ei, tem videos tão legais pra você aqui",
        "Não se esqueça da sua saúde, é o que mais importa",
        "Você vai se sentir melhor se fizer exercícios com frequência"
    )

    override fun onReceive(context: Context?, intent: Intent?) {
        val i = Intent(context?.applicationContext, MainActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pI = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_IMMUTABLE)
        } else {
            PendingIntent.getActivity(context, 0, i,0)
        }

        val id = intent?.getIntExtra(NOTIFICATION_ID, 1000)

        val notificationManager: NotificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        titleNotifications.shuffle()
        messageNotifications.shuffle()
        val notification = createNotification(
            context,
            titleNotifications[1],
            messageNotifications[1],
            notificationManager,
            pI
        )

        id?.let { notificationManager.notify(it, notification) }
    }

    private fun createNotification(
        context: Context,
        title: String,
        message: String,
        manager: NotificationManager,
        pIntent: PendingIntent
    ): Notification {
        val channelId = "YOUR_CHANNEL"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, "Channel", NotificationManager.IMPORTANCE_DEFAULT)

            manager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(message)
            .setContentIntent(pIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        return builder.build()
    }
}