package com.example.trabalhopratico.UI

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.example.trabalhopratico.database.DBHelper
import com.example.trabalhopratico.databinding.ActivityCursoDetailBinding
import com.example.trabalhopratico.model.Cursos

class CursoDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCursoDetailBinding
    private lateinit var db: DBHelper
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private var curso = Cursos()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCursoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val i = intent

        val id = i.extras?.getInt("id")
        db = DBHelper(applicationContext)

        if (id != null) {
            curso = db.selectCurso(id)
            populate()
        }
        else{
            finish()
        }

        binding.buttonReturnar.setOnClickListener {
            setResult(0, i)
            finish()
        }

        binding.buttonEditar.setOnClickListener {
            binding.layoutDelete.visibility = View.VISIBLE
            binding.layoutEdit.visibility = View.GONE
            changeEditText(true)
        }

        binding.buttonCancelar.setOnClickListener {
            binding.layoutDelete.visibility = View.GONE
            binding.layoutEdit.visibility = View.VISIBLE
            populate()
            changeEditText(false)
        }

        binding.buttonGravar.setOnClickListener {
            val res = db.updateCurso(
                id = curso.id,
                nome = binding.editNome.text.toString(),
                local = binding.editLocal.text.toString(),
                dataArranque = binding.editDataArranque.text.toString(),
                dataFim = binding.editDataFim.text.toString(),
                preco = binding.editPreco.text.toString().toDouble(),
                duracao = binding.editDuracao.text.toString().toInt(),
                edicao = binding.editEdicao.text.toString().toInt(),
                imagemId = curso.imagemId
            )

            if (res > 0){
                Toast.makeText(applicationContext, "Editado", Toast.LENGTH_SHORT).show()
                setResult(1, i)
                finish()
            }
            else{
                Toast.makeText(applicationContext, "Erro ao editar", Toast.LENGTH_SHORT).show()
                setResult(0, i)
                finish()
            }
        }

        binding.buttonEliminar.setOnClickListener {
            val res = db.deleteCurso(curso.id)

            if (res > 0){
                Toast.makeText(applicationContext, "Eliminado", Toast.LENGTH_SHORT).show()
                setResult(1, i)
                finish()
            }
            else{
                Toast.makeText(applicationContext, "Erro ao eliminar", Toast.LENGTH_SHORT).show()
                setResult(0, i)
                finish()
            }
        }

        binding.imageAddCurso.setOnClickListener {
            if(binding.editNome.isEnabled){
                launcher.launch(Intent(applicationContext, CursoSelecaoImagemActivity::class.java))
            }
        }

    }

    private fun changeEditText(status: Boolean){
        binding.editNome.isEnabled = status
        binding.editLocal.isEnabled = status
        binding.editDataArranque.isEnabled = status
        binding.editDataFim.isEnabled = status
        binding.editPreco.isEnabled = status
        binding.editDuracao.isEnabled = status
        binding.editEdicao.isEnabled = status
    }

    private fun populate() {
        binding.editNome.setText(curso.nome)
        binding.editLocal.setText(curso.local)
        binding.editDataArranque.setText(curso.dataArranque)
        binding.editDataFim.setText(curso.dataFim)
        binding.editPreco.setText(curso.preco.toString())
        binding.editDuracao.setText(curso.duracao.toString())
        binding.editEdicao.setText(curso.edicao.toString())
    }
}