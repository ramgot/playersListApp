package ru.turing.playerslistapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.turing.playerslistapp.databinding.ActivityAddPlayerBinding

class AddPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*binding.addButton.setOnClickListener {
            startActivity(Intent(this@AddPlayerActivity, MainActivity::class.java).apply {
                putExtra(NAME, binding.getNameText.text.toString())
                putExtra(CLUB, binding.getClubNameText.text.toString())
                putExtra(CLUB_IMG_REFERENCE, binding.getCluburlText.text.toString())
                putExtra(PLAYER_IMG_REFERENCE, binding.getPhotourlText.text.toString())
            })
        }*/

        binding.addButton.setOnClickListener {
            val i = Intent(this@AddPlayerActivity, MainActivity::class.java)
            val player = Player(
                id = hashCode().toLong(),
                name = binding.getNameText.text.toString(),
                club = binding.getClubNameText.text.toString(),
                photoUrl = binding.getPhotourlText.text.toString(),
                clubUrl = binding.getCluburlText.text.toString()
            )
            i.putExtra(SecondActivityContract.RESULT_KEY, player)
            setResult(Activity.RESULT_OK, i)
            finish()
        }
    }

    companion object {
        const val NAME = "name"
        const val CLUB = "club"
        const val CLUB_IMG_REFERENCE = "club image"
        const val PLAYER_IMG_REFERENCE = "player image"
    }
}