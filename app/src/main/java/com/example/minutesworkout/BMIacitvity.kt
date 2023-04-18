package com.example.minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.minutesworkout.databinding.ActivityBmiacitvityBinding

class BMIacitvity : AppCompatActivity() {
    var binding:ActivityBmiacitvityBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiacitvityBinding.inflate(layoutInflater);
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbar)
        if(supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true);
            supportActionBar?.title = "BMI Calculator"
        }
        binding?.toolbar?.setNavigationOnClickListener {
            onBackPressed()
        }


    }
}