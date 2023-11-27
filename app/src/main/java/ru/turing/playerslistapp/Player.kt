package ru.turing.playerslistapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val id: Long,
    val name: String,
    val club: String,
    val photoUrl: String,
    val clubUrl: String
) : Parcelable
