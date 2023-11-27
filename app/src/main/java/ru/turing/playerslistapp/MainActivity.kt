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
    private lateinit var binding: ActivityMainBinding
    private lateinit var playerDataSource: PlayerDataSource

    //get() = (applicationContext as App).playerDataSource
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
            Log.d("KEY_ADD_PLAYER", playerDataSource.getPlayers()[playerDataSource.getPlayers().size - 1].name)
            //val toAddPlayerActivity = Intent(this@MainActivity, AddPlayerActivity::class.java)
            //startActivity(toAddPlayerActivity)
            // playerDataSource.addPlayer(intent?.getParcelableExtra("pupupu")?: Player(hashCode().toLong(), "", "", "", ""))
        }


        /*Player(
            id = hashCode().toLong(),
            name = intent.getStringExtra("name") ?: "",
            club = intent.getStringExtra("club")?: "",
            photoUrl = intent.getStringExtra("player image")?: "",
            clubUrl = intent.getStringExtra("club image")?: ""
        )*/


    }

    private val userListener: PlayerListener = {
        adapter.players = it
    }

    /* override fun onSaveInstanceState(outState: Bundle) {
         super.onSaveInstanceState(outState)
         val listState = layoutManager
     }

     override fun onRestoreInstanceState(savedInstanceState: Bundle) {
         super.onRestoreInstanceState(savedInstanceState)
     }

     companion object {
         const val RECYCLERVIEW_BUNDLE_KEY = "recyclerview"*/
}