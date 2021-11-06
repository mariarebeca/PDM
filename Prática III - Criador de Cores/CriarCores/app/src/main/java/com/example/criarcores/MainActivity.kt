package com.example.criarcores

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private lateinit var btNovaCor : Button
    private lateinit var NovaCor : TextView
    private var red : Int = 0
    private var green : Int = 0
    private var blue : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.NovaCor = findViewById(R.id.TvShowColor)

        this.btNovaCor = findViewById(R.id.btNovaCor)
        this.btNovaCor.setOnClickListener(OnClick())

    }

    val tela2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK) {
            val nomecor : String
            nomecor = it.data?.getStringExtra("COR").toString()
            hextorgb(nomecor)
        }
    }

    inner class OnClick : View.OnClickListener{
        override fun onClick(v: View?) {
            tela2.launch(Intent(this@MainActivity, GerarCorActivity::class.java))

            if (intent.resolveActivity(packageManager) != null){
                startActivity(intent)
            }
        }
    }

    fun hextorgb(hex : String){
        if (hex == ""){
            this.NovaCor.setBackgroundColor(Color.rgb(0,0, 0))
            this.NovaCor.text = "#000000"
        } else {
            this.red = Integer.parseInt(hex.substring(0, 2), 16)
            this.green = Integer.parseInt(hex.substring(2, 4), 16)
            this.blue = Integer.parseInt(hex.substring(4, 6), 16)

            this.NovaCor.setBackgroundColor(Color.rgb(this.red, this.green, this.blue))
            this.NovaCor.text = "#$hex"
        }
    }

}