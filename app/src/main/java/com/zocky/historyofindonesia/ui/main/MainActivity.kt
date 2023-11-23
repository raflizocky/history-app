package com.zocky.historyofindonesia.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zocky.historyofindonesia.R
import com.zocky.historyofindonesia.data.History
import com.zocky.historyofindonesia.ui.ListHistoryAdapter
import com.zocky.historyofindonesia.ui.about.AboutActivity
import com.zocky.historyofindonesia.ui.detail.DetailActivity

class MainActivity : AppCompatActivity() {
    private lateinit var rvHistory: RecyclerView
    private val list = ArrayList<History>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        rvHistory = findViewById(R.id.rv_history)
        rvHistory.setHasFixedSize(true)

        list.addAll(getListHistory)
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val getListHistory: ArrayList<History>
        get() {
            val dataName = resources.getStringArray(R.array.data_name)
            val dataDescription = resources.getStringArray(R.array.data_description)
            val dataPhoto = resources.getStringArray(R.array.data_photo)
            val dataTime = resources.getStringArray(R.array.data_time)
            val listHistory = ArrayList<History>()
            for (i in dataName.indices) {
                val history = History(dataName[i], dataDescription[i], dataPhoto[i], dataTime[i])
                listHistory.add(history)
            }
            return listHistory
        }

    private fun showRecyclerList() {
        rvHistory.layoutManager = LinearLayoutManager(this)
        val listHistoryAdapter = ListHistoryAdapter(list)
        rvHistory.adapter = listHistoryAdapter

        listHistoryAdapter.setOnItemClickCallback(object : ListHistoryAdapter.OnItemClickCallback {
            override fun onItemClicked(data: History) {
                showSelectedHistory(data)
            }
        })
    }

    private fun showSelectedHistory(history: History) {
        val intentToDetail = Intent(this, DetailActivity::class.java)
        intentToDetail.putExtra("DATA", history)
        intentToDetail.putExtra("DESCRIPTION", history.description)
        startActivity(intentToDetail)
    }
}