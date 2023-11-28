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
}