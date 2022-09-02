package ru.mephi.androidversions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.mephi.androidversions.data.Android

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val android = intent?.getParcelableExtra<Android>(ARGS_VERSION)
            ?: throw IllegalArgumentException("Missing argument")

        findViewById<ImageView>(R.id.poster).setImageResource(android.posterAndroid)
        findViewById<ImageView>(R.id.avatar).setImageResource(android.imageAndroid)
        findViewById<TextView>(R.id.title).text = android.title
        findViewById<TextView>(R.id.released_date).text = android.releaseDate
        findViewById<TextView>(R.id.overview_text).text = android.overview

        val androidButton: Button = findViewById(R.id.click_btn)
        androidButton.setOnClickListener {
            openAndroidTrailer(android.trailerUrl)
        }
    }

    private fun openAndroidTrailer(url: String){
        val intentUrl = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intentUrl)
    }

    companion object{
        private const val ARGS_VERSION = "ARGS_VERSION"

        fun createIntent (context: Context, android: Android): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(ARGS_VERSION, android)
            return intent
        }
    }

}