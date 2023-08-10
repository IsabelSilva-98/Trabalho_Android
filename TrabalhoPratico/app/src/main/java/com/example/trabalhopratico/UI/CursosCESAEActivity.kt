package com.example.trabalhopratico.UI

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.trabalhopratico.R
import com.example.trabalhopratico.database.DBHelper
import com.example.trabalhopratico.databinding.ActivityCursosCesaeactivityBinding
import com.example.trabalhopratico.model.Cursos

class CursosCESAEActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCursosCesaeactivityBinding
    private lateinit var cursoList: ArrayList<Cursos>
    private lateinit var adapter: ArrayAdapter<Cursos>
    private lateinit var result: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCursosCesaeactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DBHelper(this)
        val sharedPreferences = application.getSharedPreferences("login", Context.MODE_PRIVATE)

        binding.buttonLogout.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("username", "")
            editor.apply()
            finish()
        }

        cursoList = db.selectAllCurso()

        adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, cursoList)

        binding.listViewCursos.adapter = adapter

        binding.listViewCursos.setOnItemClickListener { _, _, position, _ ->
            /*Toast.makeText(applicationContext, cursoList[position].nome, Toast.LENGTH_SHORT).show()*/
            val intent = Intent(applicationContext, CursoDetailActivity::class.java)
            intent.putExtra("id", cursoList[position].id)
            startActivity(intent)
        }

        binding.buttonAdd.setOnClickListener {
            result.launch(Intent(applicationContext, NewCursoActivity::class.java))
        }

        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.data!=null && it.resultCode == 1){
                cursoList = db.selectAllCurso()

                adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, cursoList)

                binding.listViewCursos.adapter = adapter
            }
            else if(it.data!=null && it.resultCode == 0){
                Toast.makeText(applicationContext, "Operação cancelada", Toast.LENGTH_SHORT).show()
            }
        }
    }
}