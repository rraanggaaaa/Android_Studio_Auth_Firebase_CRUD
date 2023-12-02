package com.tmr9

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    lateinit var tv_history : ImageView
    lateinit var tv_chatus : ImageView
    lateinit var tv_gallery : ImageView
    lateinit var tv_motor : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_history = findViewById(R.id.tv_history)
        tv_history.setOnLongClickListener {
            val intentHistory = Intent(this, History::class.java)
            startActivity(intentHistory)

            true
        }

        tv_chatus = findViewById(R.id.tv_chatus)
        tv_chatus.setOnLongClickListener {
            val intentChat = Intent(this, Chat::class.java)
            startActivity(intentChat)

            true
        }

        tv_gallery = findViewById(R.id.tv_gallery)
        tv_gallery.setOnLongClickListener {
            val intentGallery = Intent(this, Gallery::class.java)
            startActivity(intentGallery)

            true
        }

        tv_motor = findViewById(R.id.tv_motor)
        tv_motor.setOnLongClickListener {
            val intentMotor = Intent(this, Motor::class.java)
            startActivity(intentMotor)

            true
        }
    }
}