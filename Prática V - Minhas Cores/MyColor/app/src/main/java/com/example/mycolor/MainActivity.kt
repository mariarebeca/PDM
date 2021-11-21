package com.example.mycolor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.mycolor.CoresDAO

class MainActivity : AppCompatActivity() {
    private lateinit var lvMinhasCores: ListView
    private lateinit var fabAdd: FloatingActionButton

    private lateinit var lista: ArrayList<Cores>
    private lateinit var dao: CoresDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.dao = CoresDAO(this)
        this.lista = arrayListOf()

        this.lvMinhasCores = findViewById(R.id.lvMinhasCores)
        this.fabAdd = findViewById(R.id.fabMainAdd)

        this.lvMinhasCores.adapter = CoresAdapter(this, this.lista)

        this.lvMinhasCores.onItemClickListener = OnClick()
        this.lvMinhasCores.onItemLongClickListener = OnLongClick()

        val corResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val C = it.data?.getSerializableExtra("COR") as Cores
                    this.dao.insert(C)
                    this.lista.add(C)
                    (this.lvMinhasCores.adapter as BaseAdapter).notifyDataSetChanged()
                }
            }

        this.fabAdd.setOnClickListener{
            val intent = Intent(this, FormCorActivity::class.java)
            corResult.launch(intent)
        }
    }
    fun atualiza(){
        this.lista = this.dao.get()
        this.lvMinhasCores.adapter = ArrayAdapter<Cores>(this, android.R.layout.simple_list_item_1, this.lista)
    }

    inner class OnClick: AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val cor = this@MainActivity.lista.get(position)
            Toast.makeText(this@MainActivity, cor.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    inner class OnLongClick: AdapterView.OnItemLongClickListener {
        override fun onItemLongClick(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long,
        ): Boolean {
            this@MainActivity.lista.removeAt(position)
            (this@MainActivity.lvMinhasCores.adapter as BaseAdapter).notifyDataSetChanged()
            return true
        }
    }
}