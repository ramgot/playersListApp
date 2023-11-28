package ru.turing.playerslistapp


typealias PlayerListener = (players: List<Player>) -> Unit
class PlayerDataSource() {

    private val playerList = Array(1) {
        Player(
            id = 1L,
            name = "Лионель Месси",
            club = "Интер Майами",
            photoUrl = "https://img.a.transfermarkt.technology/portrait/header/28003-1694590254.jpg?lm=1",
            clubUrl = "https://tmssl.akamaized.net/images/wappen/normquad/69261.png?lm=1573561237"
        )
    }.toMutableList()

    private val listeners = mutableListOf<PlayerListener>()
    fun getPlayers(): List<Player> = playerList
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
}