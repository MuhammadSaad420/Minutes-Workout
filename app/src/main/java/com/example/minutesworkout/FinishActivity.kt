package com.example.minutesworkout

import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.minutesworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch

class FinishActivity : AppCompatActivity() {
    var binding: ActivityFinishBinding? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater);
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.toolbar?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding?.btFinish?.setOnClickListener {
            finish()
        }
        val dao: HistoryDAO? = (application as HistoryApp).db?.historyDao()
        dao?.let {
            addDateToDatabase(it)

        }

    }
    fun addDateToDatabase(historyDao:HistoryDAO) {
        val calendar = Calendar.getInstance();
        val currentDate = calendar.time.toString();
        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(currentDate))
        }
    }
}