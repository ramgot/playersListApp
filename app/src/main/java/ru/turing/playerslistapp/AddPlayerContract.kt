package ru.turing.playerslistapp

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class AddPlayerContract : ActivityResultContract<Unit, Player?>() {
    override fun createIntent(context: Context, input: Unit): Intent {
        return Intent(context, AddPlayerActivity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Player? {
        return intent?.getParcelableExtra<Player>(RESULT_KEY)
    }

    companion object {
        const val RESULT_KEY = "my_result_key"
    }
}