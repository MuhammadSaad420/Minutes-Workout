package com.example.minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import com.example.minutesworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding?.root)

        val fLStartButton: FrameLayout = findViewById(R.id.frameLayout)
        binding?.frameLayout?.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
//            Toast.makeText(
//                this@MainActivity,
//                "Here we will start the exercise.",
//                Toast.LENGTH_SHORT
//            ).show()
        }
    }
}