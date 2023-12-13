package ru.turing.playerslistapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Player(
    val id: UUID,
    val name: String,
    val club: String,
    val photoUrl: String,
    val clubUrl: String
) : Parcelable
