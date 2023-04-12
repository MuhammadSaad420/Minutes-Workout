package com.example.minutesworkout

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.example.minutesworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    var binding: ActivityExerciseBinding? = null;
    var restTimer: CountDownTimer? = null;
    var restProgress: Int = 0;
    var exerciseTimer: CountDownTimer? = null;
    var exerciseProgress: Int = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbar)
        if(supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
        setUpTimer();
    }

    private fun setUpTimer() {
        if(restTimer != null) {
            restTimer = null;
            restProgress = 0
        }
        if(exerciseTimer != null) {
            exerciseTimer = null;
            exerciseProgress = 0
        }
        startRestTimer()
    }

    private fun startRestTimer() {
        restTimer = object: CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++;
                binding?.progressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                startExerciseTimer()
            }

        }.start()
    }

    private fun startExerciseTimer() {
        binding?.restLayout?.visibility = View.GONE;
        binding?.tvReady?.text = "Exercise Name"
        binding?.exerciseLayout?.visibility = View.VISIBLE;
        exerciseTimer = object: CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++;
                binding?.exerciseProgressBar?.progress = 30 - exerciseProgress
                binding?.tvExerciseTimer?.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity,"One exercise is complete", Toast.LENGTH_SHORT).show()
            }

        }.start()
    }



    override fun onDestroy() {
        super.onDestroy()
        binding = null;
        if(restTimer != null) {
            restTimer = null;
            restProgress = 0
        }
        if(exerciseTimer != null) {
            exerciseTimer = null;
            exerciseProgress = 0
        }

    }
}