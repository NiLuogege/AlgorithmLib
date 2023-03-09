package com.niluogege.mykotlin

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

fun main(args: Array<String>) {
    setUpUI()
}


fun setUpUI(){
    GlobalScope.launch(Dispatchers.Default) {
        println("111=${Thread.currentThread().name}")
        val dataDeferred  = requestDataAsync()
        println("333=${Thread.currentThread().name}")
//        doSomethingElse()
        //这会进行等待
        val data = dataDeferred.await()
        println("444=${Thread.currentThread().name}")
        processData(data)
    }
    println("看 ，没阻塞吧？？")
    //这里去掉延时 只会输出 doSomethingElse2 中的内容，说明 上面的代码不会阻塞当前线程
    Thread.sleep(3000)
    doSomethingElse2()
}

fun requestDataAsync(): Deferred<String> {
    // 启动一个异步协程去执行耗时任务
    return GlobalScope.async {
        println("222=${Thread.currentThread().name}")
        requestData()
    }
}

fun doSomethingElse2(){
    println("doSomethingElse2")
}

fun requestData(): String {
    Thread.sleep(2000)
    return "Ui Data"
}

fun doSomethingElse(){
    println("doSomethingElse")
}

fun processData(data: String) {
    println("updateUI$data")
}