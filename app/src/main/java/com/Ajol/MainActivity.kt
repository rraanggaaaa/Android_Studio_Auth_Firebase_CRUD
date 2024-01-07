package com.Ajol

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
    lateinit var tv_car : ImageView
    lateinit var btn_find_us : Button

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
        tv_chatus.setOnClickListener {
            val intentChat = Intent(this, Chat::class.java)
            startActivity(intentChat)
        }

        tv_gallery = findViewById(R.id.tv_gallery)
        tv_gallery.setOnClickListener {
            val intentGallery = Intent(this, Gallery::class.java)
            startActivity(intentGallery)
        }

        tv_motor = findViewById(R.id.tv_motor)
        tv_motor.setOnClickListener {
            val intentMotor = Intent(this, Motor::class.java)
            startActivity(intentMotor)
        }

        btn_find_us = findViewById(R.id.btn_find_us)
        btn_find_us.setOnClickListener {
            val intentMotor = Intent(this, About::class.java)
            startActivity(intentMotor)
        }
    }
}