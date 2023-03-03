package com.niluogege.mykotlin


fun main(args: Array<String>) {
    var d: Data? = Data()


    //let 是扩展函数 ，内部通过it引用外部对象 ，最后一行可以返回其他的返回值
    val letResult = d?.let {
        it.b = "let"
        "let return"
    }
    println("let 以后$d letResult=$letResult")


    //with 是一个方法，内部可以直接使用this访问外部对象 ，最后一行可以返回其他的返回值
    val withResult = with(d) {
        this?.b = "with"
        "with return"
    }
    println("with 以后$d  runResult=$withResult")


    //run 是扩展函数，内部通过this访问外部对象，最后一行可以返回其他的返回值
    val runResult = d?.run {
        b = "run"
        "run return"
    }
    println("run 以后$d  runResult=$runResult")

    //also 是扩展函数 内部通过it访问外部对象，返回值只能是外部对象
    d?.also {
        it.b = "also"
    }
    println("also 以后$d")

    //apply是扩展函数，内部通过this访问外部对象，返回值只能是外部对象
    d?.apply {
        b = "apply"
    }
    println("apply 以后$d")


}


class Data {
    var a = 0
    var b = "0"
    var c = ArrayList<String>()
    override fun toString(): String {
        return "Data(a=$a, b='$b', c=$c)"
    }


}