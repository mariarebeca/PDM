package com.example.criarcores

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView

class GerarCorActivity : AppCompatActivity() {
    private lateinit var seekred: SeekBar
    private lateinit var seekverde: SeekBar
    private lateinit var seekazul: SeekBar
    private lateinit var ShowColor: TextView
    private lateinit var ProgressRed: TextView
    private lateinit var ProgressGreen: TextView
    private lateinit var ProgressBlue: TextView
    private lateinit var btSalvar: Button
    private lateinit var btCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gerar_cor)

        this.seekred = findViewById(R.id.seekBarRed)
        this.seekverde = findViewById(R.id.seekBarGreen)
        this.seekazul = findViewById(R.id.seekBarBlue)

        this.ShowColor = findViewById(R.id.TvShowColor2)
        this.ProgressRed = findViewById(R.id.tvProgressR)
        this.ProgressGreen = findViewById(R.id.tvProgressG)
        this.ProgressBlue = findViewById(R.id.tvProgressB)

        this.btSalvar = findViewById(R.id.btSalvar)
        this.btCancelar = findViewById(R.id.btCancelar)

        this.seekred.setOnSeekBarChangeListener(OnChange)
        this.seekverde.setOnSeekBarChangeListener(OnChange)
        this.seekazul.setOnSeekBarChangeListener(OnChange)

        this.btSalvar.setOnClickListener(OnClick())
        this.btCancelar.setOnClickListener{
            finish()
        }
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

        sred =  this.seekred.progress
        this.ProgressRed.text = this.seekred.progress.toString()

        sgreen=  this.seekverde.progress
        this.ProgressGreen.text = this.seekverde.progress.toString()

        sblue =  this.seekazul.progress
        this.ProgressBlue.text = this.seekazul.progress.toString()

        this.ShowColor.setBackgroundColor(Color.rgb(sred,sgreen,sblue))
        this.ShowColor.setTextColor(Color.rgb(sred,sgreen,sblue))

        corhexa = String.format("%02X", sred) + String.format("%02X", sgreen) + String.format("%02X", sblue)
        this.ShowColor.text = corhexa

    }

    inner class OnClick : View.OnClickListener{
        override fun onClick(v: View?) {
            val intent = Intent().apply {
                putExtra("COR", this@GerarCorActivity.ShowColor.text)
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}