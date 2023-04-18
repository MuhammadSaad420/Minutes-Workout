package com.example.minutesworkout

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.minutesworkout.databinding.ActivityExerciseBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    var binding: ActivityExerciseBinding? = null;
    var restTime: Long = 1;
    var exerciseTime: Long = 2;
    var exerciseList: ArrayList<ExerciseModel>? = null;
    var currentExercisePosition: Int = -1;
    var restTimer: CountDownTimer? = null;
    var restProgress: Int = 0;
    var exerciseTimer: CountDownTimer? = null;
    var exerciseProgress: Int = 0;
    var tts: TextToSpeech? = null
    var player: MediaPlayer? = null
    var recyclerAdapter : ExerciseStatusAdapter? = null;
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
        tts = TextToSpeech(this, this);
        exerciseList = Constants.Companion.defaultExerciseList();
        setUpRecyclerView()
        setUpTimer();
    }

    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);
        binding?.rvExerciseStatus?.layoutManager = layoutManager;
        recyclerAdapter = ExerciseStatusAdapter(exerciseList!!);
        binding?.rvExerciseStatus?.adapter = recyclerAdapter;
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
        try{
            player = MediaPlayer.create(applicationContext, R.raw.press_start)
            player?.start()
        } catch (e : Exception) {
            print(e.stackTrace)
        }
        binding?.tvUpcomingExercise?.text = exerciseList!![currentExercisePosition +1].getName()
        restTimer = object: CountDownTimer( restTime * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++;
                binding?.progressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                exerciseList!![currentExercisePosition + 1].setIsSelected(true);
                recyclerAdapter?.notifyDataSetChanged();
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
        speakOut(currentExercise.getName())

        exerciseTimer = object: CountDownTimer(exerciseTime * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++;
                binding?.exerciseProgressBar?.progress = 30 - exerciseProgress
                binding?.tvExerciseTimer?.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                if(currentExercisePosition + 1 < exerciseList!!.size) {
                    binding?.restLayout?.visibility = View.VISIBLE;
                    binding?.tvReady?.visibility = View.VISIBLE;
                    binding?.exerciseLayout?.visibility = View.INVISIBLE;
                    binding?.tvExerciseName?.visibility = View.INVISIBLE;
                    binding?.ivExercise?.visibility = View.INVISIBLE
                    binding?.tvUpcomingLabel?.visibility = View.VISIBLE;
                    binding?.tvUpcomingExercise?.visibility = View.VISIBLE;
                    restProgress = 0
                    exerciseList!![currentExercisePosition].setIsCompleted(true);
                    exerciseList!![currentExercisePosition].setIsSelected(false);
                    recyclerAdapter?.notifyDataSetChanged();
                    startRestTimer()
                } else {
                    val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                    startActivity(intent);
                    finish()
                }
            }

        }.start()
    }

    private fun speakOut(name: String) {
        tts?.speak(name, TextToSpeech.QUEUE_FLUSH,null,"")
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
        player?.stop()
        tts?.stop()
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS) {
            tts?.language = Locale.US
        }
    }
}