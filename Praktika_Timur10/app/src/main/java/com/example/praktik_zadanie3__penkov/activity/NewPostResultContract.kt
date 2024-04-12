package com.example.praktik_zadanie3__penkov.activity
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class NewPostResultContract : ActivityResultContract<String, String?>() {

    override fun createIntent(context: Context, input: String): Intent {
        val intent = Intent(context, NewPostActivity::class.java)
        intent.putExtra(Intent.EXTRA_TEXT, input)
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? =
        if (resultCode == Activity.RESULT_OK) {
            intent?.getStringExtra(Intent.EXTRA_TEXT)

        } else {
            null
        }
}