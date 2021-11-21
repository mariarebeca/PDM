package com.example.mycolor

import android.content.ContentValues
import android.content.Context

class CoresDAO {
    private var banco: BancoHelper

    constructor(context: Context){
        this.banco = BancoHelper(context)
    }

    fun insert(cor: Cores){
        val cv = ContentValues()
        cv.put("RGB", cor.toString())
        this.banco.writableDatabase.insert("cores", null, cv)
    }

    fun count(): Int{
        val sql = "select count(id) from cores"
        val cursor = this.banco.readableDatabase.rawQuery(sql,null)
        cursor.moveToFirst()
        return cursor.getInt(0)
    }

    fun get(): ArrayList<Cores>{
        val lista = arrayListOf<Cores>()
        val colunas = arrayOf("id", "RGB")

        val cursor = this.banco.readableDatabase.query("cores", colunas, null, null, null, null, null)
        cursor.moveToFirst()

        for (i in 1..cursor.count){
            val id = cursor.getInt(0)
            val RGB = cursor.getString(1)
            val R = RGB.toString().toInt()
            val G = RGB.toString().toInt()
            val B = RGB.toString().toInt()

            lista.add(Cores(id, R, G, B))
            cursor.moveToNext()
        }

        return lista
    }

    fun get(id: Int): Cores?{
        val colunas = arrayOf("id", "RGB")
        val where = "id = ?"
        val pWhere = arrayOf(id.toString())

        val cursor = this.banco.readableDatabase.query("cores", colunas, where, pWhere, null, null, null)

        cursor.moveToFirst()

        if (cursor.count == 1){
            val id = cursor.getInt(0)
            val RGB = cursor.getString(1)
            val R = RGB.toString().toInt()
            val G = RGB.toString().toInt()
            val B = RGB.toString().toInt()
            return Cores(id, R, G, B)
        }
        return null
    }

    fun delete(id: Int){
        val where = "id = ?"
        val pWhere = arrayOf(id.toString())
        this.banco.writableDatabase.delete("cores", where, pWhere)
    }

    fun update(cor: Cores){
        val where = "id = ?"
        val pWhere = arrayOf(cor.id.toString())
        val cv = ContentValues()
        cv.put("RGB", cor.toString())

        this.banco.writableDatabase.update("cores", cv, where, pWhere)
    }
}