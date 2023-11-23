package com.zocky.historyofindonesia.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class History(
    val name: String,
    val description: String,
    val photo: String,
    val time: String,
) : Parcelable