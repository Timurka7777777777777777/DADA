package com.example.praktik_zadanie3__penkov.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.content.Intent
import com.example.praktik_zadanie3__penkov.R
import com.example.praktik_zadanie3__penkov.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val text = intent?.getStringExtra(Intent.EXTRA_TEXT)
        binding.edit.setText(text)

        binding.ok.setOnClickListener {
            val intent = Intent()
            if (binding.edit.text.isNullOrBlank()) {
                setResult(Activity.RESULT_CANCELED, intent)
            } else {
                val content = binding.edit.text.toString()
                intent.putExtra(Intent.EXTRA_TEXT, content)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }
}