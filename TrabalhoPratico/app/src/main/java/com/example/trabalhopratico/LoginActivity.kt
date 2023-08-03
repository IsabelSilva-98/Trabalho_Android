package com.example.trabalhopratico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trabalhopratico.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {  }

        binding.textRegistar.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.textRecuperarPass.setOnClickListener {  }
    }
}