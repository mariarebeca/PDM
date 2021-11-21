package com.example.mycolor

import java.io.Serializable

class Cores (var r : Int, var g : Int, var b : Int) : Serializable{
    var id: Int = 0

    constructor(id: Int, r: Int, g: Int, b: Int) : this(r, g, b) {
        this.id = id
        this.r = r
        this.g = g
        this.b = b
    }

    override fun toString(): String {
        return "$r$g$b"
    }

}