package com.example.misturadordecores

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import com.google.android.material.slider.RangeSlider
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var seekred: SeekBar
    private lateinit var seekverde: SeekBar
    private lateinit var seekazul: SeekBar
    private lateinit var tview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.seekred = findViewById(R.id.SeekBarRed)
        this.seekverde = findViewById(R.id.SeekBarGreen)
        this.seekazul = findViewById(R.id.SeekBarBlue)

        this.tview = findViewById(R.id.TvShowColor)

        this.seekred.setOnSeekBarChangeListener(OnChange)
        this.seekverde.setOnSeekBarChangeListener(OnChange)
        this.seekazul.setOnSeekBarChangeListener(OnChange)

        onAppStart()
    }

    var OnChange : SeekBar.OnSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {

        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            MisturadorDeCores()
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {}

        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    }

    fun MisturadorDeCores() {
        var sred : Int = 0
        var sblue : Int = 0
        var sgreen : Int = 0

        var corhexa: String

        sred =  this@MainActivity.seekred.progress
        sblue =  this@MainActivity.seekazul.progress
        sgreen=  this@MainActivity.seekverde.progress

        this@MainActivity.tview.setBackgroundColor(Color.rgb(sred,sgreen,sblue))

        corhexa = String.format("%02X", sred) + String.format("%02X", sgreen) + String.format("%02X", sblue)
        this@MainActivity.tview.text = corhexa

    }

    fun onAppStart(){
        this@MainActivity.seekred.progress = (Random.nextInt(0,256))
        this@MainActivity.seekverde.progress = (Random.nextInt(0,256))
        this@MainActivity.seekazul.progress = (Random.nextInt(0,256))

        MisturadorDeCores()
    }
}
