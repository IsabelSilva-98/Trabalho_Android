package com.example.trabalhopratico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trabalhopratico.databinding.ActivityMainBinding
import com.example.trabalhopratico.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonRegistar.setOnClickListener {
            finish()
        }


    }
}