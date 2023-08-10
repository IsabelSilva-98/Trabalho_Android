package com.example.trabalhopratico.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.trabalhopratico.R
import com.example.trabalhopratico.database.DBHelper
import com.example.trabalhopratico.databinding.ActivityNewCursoBinding

class NewCursoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewCursoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCursoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DBHelper(applicationContext)
        val i = intent

        binding.buttonGravar.setOnClickListener {
            val nome = binding.editNome.text.toString()
            val local = binding.editLocal.text.toString()
            val dataArranque = binding.editDataArranque.text.toString()
            val dataFim = binding.editDataFim.text.toString()
            val preco = binding.editPreco.text.toString().toDouble()
            val duracao = binding.editDuracao.text.toString().toInt()
            val edicao = binding.editEdicao.text.toString().toInt()

            if(nome.isNotEmpty() && local.isNotEmpty() && dataArranque.isNotEmpty() && dataFim.isNotEmpty()){
                val res = db.insertCurso(nome, local, dataArranque, dataFim, preco, duracao, edicao)
                if(res > 0){
                    Toast.makeText(applicationContext, "Dados inseridos", Toast.LENGTH_SHORT).show()
                    setResult(1, i)
                    finish()
                }
                else{
                    Toast.makeText(applicationContext, "Preencha todos os campos", Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.buttonCancelar.setOnClickListener {
            setResult(0, i)
            finish()
        }
    }
}