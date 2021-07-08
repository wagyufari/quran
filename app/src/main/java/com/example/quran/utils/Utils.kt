package com.example.quran.utils

import android.app.Activity
import android.content.res.Resources
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
  crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }

fun Activity.io(runnable:suspend ()->Unit){
    CoroutineScope(Dispatchers.IO).launch {
        runnable.invoke()
    }
}
fun Fragment.io(runnable:suspend ()->Unit){
    CoroutineScope(Dispatchers.IO).launch {
        runnable.invoke()
    }
}
fun Activity.main(runnable:suspend ()->Unit){
    CoroutineScope(Dispatchers.Main).launch {
        runnable.invoke()
    }
}
fun Fragment.main(runnable:suspend ()->Unit){
    CoroutineScope(Dispatchers.Main).launch {
        runnable.invoke()
    }
}



fun dpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}

fun dpToPx(dp: Float): Float {
    return (dp * Resources.getSystem().displayMetrics.density)
}