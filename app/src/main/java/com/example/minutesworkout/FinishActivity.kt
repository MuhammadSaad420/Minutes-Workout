package com.example.minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.minutesworkout.databinding.ActivityFinishBinding

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

    }
}