package com.hfad.countdowntimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.hfad.countdowntimer.databinding.ActivityMainBinding
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var interval : Long = 1000
    private lateinit var countDownT : CountDownTimer
    var running = false
    var mills : Long = 15000
    val RUNNING_KEY = "running"
    val BASE_KEY = "base"
    var timerTime : Long = 0
    val TIMER_TIME_KEY = "time"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            running = savedInstanceState.getBoolean(RUNNING_KEY)
            if (running) {
                mills = savedInstanceState.getLong(BASE_KEY)
                timerTime = savedInstanceState.getLong(TIMER_TIME_KEY)
                binding.output.text = "You put the timer to ${timerTime/1000} seconds"
                startCounting(mills, interval)
                countDownT.start()
            } else mills = 15000
        }

        binding.btnPlus.setOnClickListener {
            if (running){
                Toast.makeText(this, "While countDown Timer is running you cannot add time", Toast.LENGTH_SHORT).show()
            } else {
                mills += 15000
                Toast.makeText(this, "CountDown timer time is $mills seconds", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnMinus.setOnClickListener {
            if (running) {
                Toast.makeText(this, "While CountDown Timer is running you cannot minus time", Toast.LENGTH_SHORT).show()
            } else {
                if (mills > 15000) {
                    mills -= 15000
                }else {
                    Toast.makeText(this, "CountDownTimer cannot be less than 15 seconds", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.button.setOnClickListener {
            startCounting(mills, interval)
            binding.output.text = "You put the timer to ${mills/1000} seconds"
            timerTime = mills
            countDownT.start()
        }
    }

    private fun startCounting(milSec: Long, inter: Long) {
        countDownT = object : CountDownTimer(milSec, inter) {
            override fun onTick(p0: Long) {
                running = true
                mills = p0
                binding.textView2.text = "${p0 / inter}"
            }
            override fun onFinish() {
                running = false
                binding.output.text = "Done!"
                mills = 0
            }
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putBoolean(RUNNING_KEY, running)
        savedInstanceState.putLong(TIMER_TIME_KEY, timerTime)
        savedInstanceState.putLong(BASE_KEY, mills)
        super.onSaveInstanceState(savedInstanceState)
    }

    override fun onStop() {
        super.onStop()
        countDownT.cancel()
    }
}