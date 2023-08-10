package com.example.trabalhopratico.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trabalhopratico.R
import com.example.trabalhopratico.databinding.ActivityCursoSelecaoImagemBinding

class CursoSelecaoImagemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCursoSelecaoImagemBinding
    private lateinit var i: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCursoSelecaoImagemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        i = intent

        binding.image1.setOnClickListener { sendID(R.drawable.foto1) }
        binding.image2.setOnClickListener { sendID(R.drawable.foto2) }
        binding.image3.setOnClickListener { sendID(R.drawable.foto3) }
        binding.image4.setOnClickListener { sendID(R.drawable.foto4) }
        binding.image5.setOnClickListener { sendID(R.drawable.foto5) }
        binding.image6.setOnClickListener { sendID(R.drawable.foto6) }

        binding.buttonRemover.setOnClickListener { sendID(R.drawable.adicionar) }
    }

    private fun sendID(id: Int){
        i.putExtra("id", id)
        setResult(1, i)
        finish()
    }
}