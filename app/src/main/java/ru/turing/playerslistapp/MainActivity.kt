package ru.turing.playerslistapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.launch
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.turing.playerslistapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val namesList = listOf("Cristiano Ronaldo", "Neymar", "Kilian Mbappe", "Erling Holland", "Anton TheBolotnyi")
    private val clubsList = listOf("Al-Nasr", "PSG", "Manchester City", "CSKA")
    private val facesList = listOf("https://img.a.transfermarkt.technology/portrait/big/70114-1629035251.png?lm=1",
        "https://img.a.transfermarkt.technology/portrait/big/8198-1694609670.jpg?lm=1",
        "https://img.a.transfermarkt.technology/portrait/big/418560-1694590614.jpg?lm=1",
        "https://img.a.transfermarkt.technology/portrait/big/342229-1682683695.jpg?lm=1",
        "https://img.a.transfermarkt.technology/portrait/big/68290-1697056482.png?lm=1")
    private val emblemsList = listOf("https://tmssl.akamaized.net/images/wappen/head/583.png?lm=1522312728",
        "https://tmssl.akamaized.net/images/wappen/head/281.png?lm=1467356331",
        "https://tmssl.akamaized.net/images/wappen/head/2410.png?lm=1409222667",
        "https://tmssl.akamaized.net/images/wappen/head/18544.png?lm=1693570524")
    private lateinit var binding: ActivityMainBinding
    private lateinit var playerDataSource: PlayerDataSource
    private lateinit var adapter: PlayersAdapter
    private val activityLauncher: ActivityResultLauncher<Unit> =
        registerForActivityResult(SecondActivityContract()) {
            if (it != null) {
                playerDataSource.addPlayer(it)
            } else {
                playerDataSource.addPlayer(Player(hashCode().toLong(), "", "", "", ""))
            }
        }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playerDataSource = PlayerDataSource()

        adapter = PlayersAdapter(
            object : PlayerActionListener {
                override fun onPlayerDelete(player: Player) {
                    playerDataSource.deletePlayer(player)
                }

                override fun onPlayerDetails(player: Player) {
                    Toast.makeText(this@MainActivity, "Игрок ${player.name}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        )

        val layoutManager = LinearLayoutManager(this)
        binding.playersListRecycler.layoutManager = layoutManager
        binding.playersListRecycler.adapter = adapter
        playerDataSource.addListener(userListener)

        binding.addPlayerButton.setOnClickListener {
            activityLauncher.launch()
        }
        binding.addRandomButton.setOnClickListener {
            val player = Player(
                id = hashCode().toLong(),
                name = namesList[(0..4).random()],
                club = clubsList[(0..3).random()],
                photoUrl = facesList[(0..4).random()],
                clubUrl = emblemsList[(0..3).random()]
            )

            playerDataSource.addPlayer(player)
            Log.d("GET_ID", player.id.toString())
        }
    }

    private val userListener: PlayerListener = {
        adapter.players = it
    }
}