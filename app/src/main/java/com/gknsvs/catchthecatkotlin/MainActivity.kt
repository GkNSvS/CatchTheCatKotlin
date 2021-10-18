package com.gknsvs.catchthecatkotlin

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.gknsvs.catchthecatkotlin.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var score = 0
    private var imageArray = ArrayList<ImageView>()
    var runnable = Runnable {}
    var handler = Handler(Looper.getMainLooper())


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initialize()
        startGame()
        object : CountDownTimer(15000,1000){
            override fun onTick(p0: Long) {
                binding.txtTime.text="Time : "+ p0/1000;
            }

            override fun onFinish() {
                handler.removeCallbacks(runnable)
                binding.txtTime.text="Time : 0"

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart the Game")
                alert.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                    Toast.makeText(this@MainActivity,"Game Over", Toast.LENGTH_LONG).show()
                    hideImage()

                })
                alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                    val intent=intent;
                    finish()
                    startActivity(intent)
                })
                alert.show()
            }

        }.start()
    }

    fun initialize() {
        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
        score = 0


    }

    fun startGame() {
        hideImage()


        runnable = object : Runnable {
            override fun run() {
                hideImage()
                randomImageShow()
                handler.postDelayed(runnable, 500)

            }

        }
        handler.post(runnable)
    }

    fun hideImage() {
        for (image in imageArray) {
            image.visibility = View.INVISIBLE
        }
    }


    fun randomImageShow() {
        val random = Random
        val randomIndex = random.nextInt(9)
        imageArray[randomIndex].visibility = View.VISIBLE
    }



    fun buttonClicked(view: View) {
        score++;
        binding.txtScore.text = "Score : $score"
        hideImage()
        randomImageShow()

    }
}