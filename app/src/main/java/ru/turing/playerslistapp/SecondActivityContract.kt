package ru.turing.playerslistapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class SecondActivityContract : ActivityResultContract<Unit, Player?>() {
    override fun createIntent(context: Context, input: Unit): Intent {
        return Intent(context, AddPlayerActivity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Player? {
        //if (resultCode != Activity.RESULT_OK) return null
        return intent?.getParcelableExtra<Player>(RESULT_KEY)
    /* return intent?.getParcelableExtra<Player>(RESULT_KEY) ?: Player(
            hashCode().toLong(),
            "",
            "",
            "",
            ""
        )*/
    }

    companion object {
        const val RESULT_KEY = "my_result_key"
    }
}