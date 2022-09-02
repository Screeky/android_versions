package ru.mephi.androidversions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import ru.mephi.androidversions.data.DataStorage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val android = DataStorage.getVersionList()
        val versions = DataStorage.getVersionList()
        val adapter = AndroidAdapter(this, versions) {position ->
            val androids = android[position]
            val intent = DetailsActivity.createIntent(this, androids)
            startActivity(intent)
        }

        val list = findViewById<RecyclerView>(R.id.androidList)
        list.adapter = adapter

        val decoration = DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
        decoration.setDrawable(ContextCompat.getDrawable(this, R.color.grey)!!)
        list.addItemDecoration(decoration)

    }
}