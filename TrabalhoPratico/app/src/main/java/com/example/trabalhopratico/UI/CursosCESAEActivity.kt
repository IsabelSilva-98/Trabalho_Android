package com.example.trabalhopratico.UI

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trabalhopratico.R
import com.example.trabalhopratico.databinding.ActivityCursosCesaeactivityBinding

class CursosCESAEActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCursosCesaeactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCursosCesaeactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = application.getSharedPreferences("login", Context.MODE_PRIVATE)

        binding.buttonLogout.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("username", "")
            editor.apply()
            finish()
        }
    }
}