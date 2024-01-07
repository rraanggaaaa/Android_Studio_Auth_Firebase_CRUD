package com.Ajol

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity

class About : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)


        // Memanggil dan Mendeklarasikan ID pada Layout
        val btnCall: ImageView = findViewById(R.id.call)
        val btnSms: ImageView = findViewById(R.id.sms)
        val btnMail: ImageView = findViewById(R.id.mail)
        val btnMaps: ImageView = findViewById(R.id.maps)
        val btnWebsite: ImageView = findViewById(R.id.website)


        // Deklarasi Klik Ke Telepon
        btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+62-274-623306")
            startActivity(intent)
        }

        // Deklarasi Klik Ke Pesan (SMS)
        btnSms.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("smsto:+62-274-623306")
            intent.putExtra("sms_body", "Saran, Kritik dan Masukan!")
            startActivity(intent)
        }

        // Deklarasi Klik Ke Gmail
        btnMail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:rektor@uty.ac.id")
            intent.putExtra(Intent.EXTRA_SUBJECT, "Hello")
            intent.putExtra(Intent.EXTRA_TEXT, "Saran, Kritik dan Masukan!")
            startActivity(intent)
        }

        // Deklarasi Klik Ke Google Maps
        btnMaps.setOnClickListener {
            val uri = Uri.parse("geo:0,0?q=\"-7.7475128,110.3554162(UTY)\"")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }

        // Deklarasi Klik Ke Website
        btnWebsite.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://uty.ac.id/")
            startActivity(intent)
        }
    }
}