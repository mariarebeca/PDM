package com.example.mycolor

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi

class CoresAdapter (var contexto : Context, var lista : ArrayList<Cores>) : BaseAdapter() {
    override fun getCount(): Int {
        return this.lista.count()
    }

    override fun getItem(position: Int): Any {
        return this.lista.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = if (convertView == null){
            LayoutInflater.from(this.contexto).inflate(R.layout.cores_layout, parent, false)
        }else{
            convertView
        }

        val cor = this.lista.get(position)

        val tvNome = view.findViewById<TextView>(R.id.tvNome)
        val ivColor = view.findViewById<ImageView>(R.id.ivColor)

        tvNome.text = cor.toString()

        view.minimumHeight = 100

        ivColor.drawable.setTint((Color.rgb(cor.r,cor.g,cor.b)))

        return view
    }
}