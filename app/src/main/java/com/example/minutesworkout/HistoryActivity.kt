package com.example.minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.minutesworkout.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    var binding: ActivityHistoryBinding? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root);

        setSupportActionBar(binding?.toolbar)
        if(supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true);
            supportActionBar?.title = "History"
        }
        binding?.toolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}