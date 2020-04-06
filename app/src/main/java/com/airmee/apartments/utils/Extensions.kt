package com.airmee.apartments.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import kotlin.math.abs
import kotlin.math.pow
import kotlin.properties.Delegates

inline fun <VH : RecyclerView.ViewHolder, T> RecyclerView.Adapter<VH>.diffUtil(
    initialValue: List<T>,
    crossinline areItemsTheSame: (T, T) -> Boolean = { old, new -> old == new },
    crossinline areContentsTheSame: (T, T) -> Boolean = { old, new -> old == new }
) =
    Delegates.observable(initialValue) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                areItemsTheSame(old[oldItemPosition], new[newItemPosition])

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                areContentsTheSame(old[oldItemPosition], new[newItemPosition])

            override fun getOldListSize(): Int = old.size
            override fun getNewListSize(): Int = new.size
        }).dispatchUpdatesTo(this@diffUtil)
    }

inline fun <A, B, R> ifNotNull(a: A?, b: B?, action: (A, B) -> R) {
    if (a != null && b != null) action(a, b)
}

fun <T> MutableLiveData<T>.toLiveData() = this as LiveData<T>

fun Float.format(): String = NumberFormat.getInstance().format(this / 1000)
fun Float.formatDist(): String? {
    return when {
        this < 1000 -> {
            this.toInt().toString() + "m"
        }
        this < 10000 -> {
            formatDec(this / 1000f, 1).toString() + "km"
        }
        else -> {
            (this / 1000f).toInt().toString() + "km"
        }
    }
}

fun formatDec(f: Float, dec: Int): String? {
    val factor = 10.0.pow(dec.toDouble()).toInt()
    val front = f.toInt()
    val back = abs(f * factor).toInt() % factor
    return "$front.$back"
}


