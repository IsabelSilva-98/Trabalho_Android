package com.example.trabalhopratico.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.trabalhopratico.R
import com.example.trabalhopratico.database.DBHelper
import com.example.trabalhopratico.databinding.ActivityNewCursoBinding

class NewCursoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewCursoBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private var id: Int? = 0

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
            var imagemId = 0
            if(id!=null){
                imagemId = id as Int
            }

            if(nome.isNotEmpty() && local.isNotEmpty() && dataArranque.isNotEmpty() && dataFim.isNotEmpty()){
                val res = db.insertCurso(nome, local, dataArranque, dataFim, preco, duracao, edicao, imagemId)
                if(res > 0){
                    Toast.makeText(applicationContext, getString(R.string.dados_inseridos), Toast.LENGTH_SHORT).show()
                    setResult(1, i)
                    finish()
                }
                else{
                    Toast.makeText(applicationContext, getString(R.string.preencha_todos_os_campos), Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.buttonCancelar.setOnClickListener {
            setResult(0, i)
            finish()
        }

        binding.imageAddCurso.setOnClickListener {
            launcher.launch(Intent(applicationContext, CursoSelecaoImagemActivity::class.java))
        }

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.data != null && it.resultCode == 1) {
                id = it.data?.extras?.getInt("id")
                binding.imageAddCurso.setImageResource(id!!)
            }
            else{
                id = -1
                binding.imageAddCurso.setImageResource(R.drawable.adicionar)
            }
        }
    }
}