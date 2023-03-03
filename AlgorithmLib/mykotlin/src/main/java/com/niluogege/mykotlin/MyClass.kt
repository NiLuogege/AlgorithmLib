package com.niluogege.mykotlin

fun main(args: Array<String>) {
    var d: Data? = Data()


    d?.let {
        it.a = 2
        it.b = "3"
    }

    d?.apply {
        a = 1
        b = "3"
    }



}


class Data {
    var a = 1
    var b = "2"
    var c = ArrayList<String>()
}
