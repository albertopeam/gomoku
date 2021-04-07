package com.github.albertopeam.gomoku

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@Suppress("UNCHECKED_CAST")
fun <T> LiveData<T>.await(time: Long = 2, timeUnit: TimeUnit = TimeUnit.SECONDS): T {
    var value: T? = null
    val countDownLatch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(newValue: T?) {
            value = newValue
            countDownLatch.countDown()
            this@await.removeObserver(this)
        }
    }
    this.observeForever(observer)
    if (!countDownLatch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }
    return value as T
}