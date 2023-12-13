package ru.turing.playerslistapp

import java.util.UUID

typealias PlayerListener = (players: List<Player>) -> Unit

class PlayerDataSource() {

    private val namesList = listOf(
        "Cristiano Ronaldo",
        "Neymar",
        "Kilian Mbappe",
        "Erling Holland",
        "Anton TheBolotnyi"
    )
    private val clubsList = listOf("Al-Nasr", "PSG", "Manchester City", "CSKA")
    private val facesList = listOf(
        "https://img.a.transfermarkt.technology/portrait/big/70114-1629035251.png?lm=1",
        "https://img.a.transfermarkt.technology/portrait/big/8198-1694609670.jpg?lm=1",
        "https://img.a.transfermarkt.technology/portrait/big/418560-1694590614.jpg?lm=1",
        "https://img.a.transfermarkt.technology/portrait/big/342229-1682683695.jpg?lm=1",
        "https://img.a.transfermarkt.technology/portrait/big/68290-1697056482.png?lm=1"
    )
    private val emblemsList = listOf(
        "https://tmssl.akamaized.net/images/wappen/head/583.png?lm=1522312728",
        "https://tmssl.akamaized.net/images/wappen/head/281.png?lm=1467356331",
        "https://tmssl.akamaized.net/images/wappen/head/2410.png?lm=1409222667",
        "https://tmssl.akamaized.net/images/wappen/head/18544.png?lm=1693570524"
    )

    private val playerList = Array(1) {
        Player(
            id = UUID.randomUUID(),
            name = "Лионель Месси",
            club = "Интер Майами",
            photoUrl = "https://img.a.transfermarkt.technology/portrait/header/28003-1694590254.jpg?lm=1",
            clubUrl = "https://tmssl.akamaized.net/images/wappen/normquad/69261.png?lm=1573561237"
        )
    }.toMutableList()

    private val listeners = mutableListOf<PlayerListener>()
    fun deletePlayer(player: Player) {
        val indexToDelete = playerList.indexOfFirst { it.id == player.id }
        if (indexToDelete != -1) {
            playerList.removeAt(indexToDelete)
            notifyChanges()
        }
    }

    fun addPlayer(player: Player) {
        playerList.add(player)
        notifyChanges()
    }

    fun addListener(listener: PlayerListener) {
        listeners.add(listener)
        listener.invoke(playerList)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(playerList) }
    }

    fun formRandomPlayer(): Player {
        return Player(
            id = UUID.randomUUID(),
            name = namesList[(0..4).random()],
            club = clubsList[(0..3).random()],
            photoUrl = facesList[(0..4).random()],
            clubUrl = emblemsList[(0..3).random()],
        )
    }
}