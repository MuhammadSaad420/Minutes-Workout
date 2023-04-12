package com.example.minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.example.minutesworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    var binding: ActivityExerciseBinding? = null;
    var exerciseList: ArrayList<ExerciseModel>? = null;
    var currentExercisePosition: Int = -1;
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
        exerciseList = Constants.Companion.defaultExerciseList();
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
        binding?.tvUpcomingExercise?.text = exerciseList!![currentExercisePosition +1].getName()
        restTimer = object: CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++;
                binding?.progressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                exerciseProgress = 0;
                startExerciseTimer()
            }

        }.start()
    }

    private fun startExerciseTimer() {
        currentExercisePosition++
        binding?.restLayout?.visibility = View.INVISIBLE;
        binding?.tvReady?.visibility = View.INVISIBLE;
        binding?.tvUpcomingLabel?.visibility = View.INVISIBLE;
        binding?.tvUpcomingExercise?.visibility = View.INVISIBLE;
        binding?.exerciseLayout?.visibility = View.VISIBLE;
        binding?.tvExerciseName?.visibility = View.VISIBLE;
        binding?.ivExercise?.visibility = View.VISIBLE
        var currentExercise = exerciseList!![currentExercisePosition];
        binding?.ivExercise?.setImageResource(currentExercise.getImage())
        binding?.tvExerciseName?.text = currentExercise.getName();

        exerciseTimer = object: CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++;
                binding?.exerciseProgressBar?.progress = 30 - exerciseProgress
                binding?.tvExerciseTimer?.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                if(currentExercisePosition <= exerciseList!!.size) {
                    binding?.restLayout?.visibility = View.VISIBLE;
                    binding?.tvReady?.visibility = View.VISIBLE;
                    binding?.exerciseLayout?.visibility = View.INVISIBLE;
                    binding?.tvExerciseName?.visibility = View.INVISIBLE;
                    binding?.ivExercise?.visibility = View.INVISIBLE
                    binding?.tvUpcomingLabel?.visibility = View.INVISIBLE;
                    binding?.tvUpcomingExercise?.visibility = View.INVISIBLE;
                    restProgress = 0
                    startRestTimer()
                } else {

                }
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