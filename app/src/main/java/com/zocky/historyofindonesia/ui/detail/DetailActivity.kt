package com.zocky.historyofindonesia.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.zocky.historyofindonesia.R
import com.zocky.historyofindonesia.data.History
import com.zocky.historyofindonesia.ui.about.AboutActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val receivedData = intent.getParcelableExtra<History>("key_history")
        val description = intent.getStringExtra("DESCRIPTION")

        val photoImageView = findViewById<ImageView>(R.id.profile_image)
        val titleTextView = findViewById<TextView>(R.id.detail_title)
        val descriptionTextView = findViewById<TextView>(R.id.detail_description)
        val dataTime = findViewById<TextView>(R.id.detail_time)
        val shareButton = findViewById<Button>(R.id.action_share)

        receivedData?.let {
            Glide.with(this)
                .load(it.photo)
                .into(photoImageView)

            titleTextView.text = it.name
            descriptionTextView.text = it.description
            dataTime.text = it.time
        }

        shareButton.setOnClickListener {
            shareButtonClicked(
                description
                    ?: "Hello! I\'ve just read one of a thousand history from the History of Indonesia App, Check it out!\nLink: https://www.dicoding.com/home"
            )
        }
    }

    private fun shareButtonClicked(description: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, description)

        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

            R.id.about_page -> {
                val aboutIntent = Intent(this, AboutActivity::class.java)
                startActivity(aboutIntent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
