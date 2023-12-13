package ru.turing.playerslistapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.turing.playerslistapp.databinding.ActivityAddPlayerBinding
import java.util.UUID

class AddPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            val toMainActivityIntent = Intent(this@AddPlayerActivity, MainActivity::class.java)
            val player = Player(
                id = UUID.randomUUID(),
                name = binding.getNameText.text.toString(),
                club = binding.getClubNameText.text.toString(),
                photoUrl = binding.getPhotourlText.text.toString(),
                clubUrl = binding.getCluburlText.text.toString()
            )
            toMainActivityIntent.putExtra(AddPlayerContract.RESULT_KEY, player)
            setResult(Activity.RESULT_OK, toMainActivityIntent)
            finish()
        }
    }
}