package com.example.mycolor

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.Validators.and
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class FormCorActivity : AppCompatActivity() {
    private lateinit var ValorR : EditText
    private lateinit var ValorG : EditText
    private lateinit var ValorB : EditText
    private lateinit var btSalvar : Button
    private lateinit var btCancelar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_cor)

        ValorR = findViewById(R.id.etNumR)
        ValorG = findViewById(R.id.etNumG)
        ValorB = findViewById(R.id.etNumB)
        btSalvar = findViewById(R.id.btSalvar)
        btCancelar = findViewById(R.id.btCancelar)

        this.btSalvar.setOnClickListener{salvar()}
        this.btCancelar.setOnClickListener {
            finish()
        }
    }

        private fun salvar(){

            val R = this.ValorR.text.toString().toInt()
            val G = this.ValorG.text.toString().toInt()
            val B = this.ValorB.text.toString().toInt()


            if (R <= 255 && G <= 255 && B <= 255) {
                val cor = Cores(R, G, B)
                val intent = Intent().apply {
                    putExtra("COR", cor)
                }
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Insira um numero entre 0 e 255", Toast.LENGTH_LONG).show()
            }
        }


}
