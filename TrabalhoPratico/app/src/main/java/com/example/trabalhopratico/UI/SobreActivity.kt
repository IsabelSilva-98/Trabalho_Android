package com.example.trabalhopratico.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trabalhopratico.R
import com.example.trabalhopratico.databinding.ActivitySobreBinding

class SobreActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySobreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySobreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAnterior.setOnClickListener {
            finish()
        }
    }
}