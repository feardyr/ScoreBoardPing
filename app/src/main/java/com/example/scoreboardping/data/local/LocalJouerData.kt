package com.example.scoreboardping.data.local

import com.example.scoreboardping.data.Jouer
import com.example.scoreboardping.data.local.LocalJouerData.joueurs

object LocalJouerData {
    val joueurs = listOf(
        Jouer(1, 1100, "Nathan Boulay"),
        Jouer(2, 850, "Yannick Boulay"),

    )
}

fun getJouerById(id: Long): Jouer? {
    return joueurs.find { it.id == id }
}
val defaultJouer = Jouer(0, 0, "")
