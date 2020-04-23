package com.shaquibquraishi.kotlindemo.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

object Coroutines {

    fun main(work: (() -> Unit)) =
        CoroutineScope(Main).launch {
            work()
        }


}