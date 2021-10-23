package com.example.cores

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var rgbnum : TextView
    private lateinit var layout : ConstraintLayout
    private var colorred : Int = 0
    private var colorblue : Int = 0
    private var colorgreen : Int = 0
    private lateinit var corgerada : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.rgbnum = findViewById(R.id.tvCores)
        this.layout = findViewById(R.id.cslayout)

        this.layout.setOnClickListener{
            this.gerarCores()
            this.layout.setBackgroundColor(Color.rgb(this.colorred,this.colorgreen,this.colorblue))
            this.rgbnum.text = this.corgerada
        }
    }
    fun gerarCores(){
        this.colorred=(Random.nextInt(0,256))
        this.colorblue=(Random.nextInt(0,256))
        this.colorgreen=(Random.nextInt(0,256))

        this.corgerada = "R: " + this.colorred + " G: " + this.colorgreen + " B: " + this.colorblue
    }

}


