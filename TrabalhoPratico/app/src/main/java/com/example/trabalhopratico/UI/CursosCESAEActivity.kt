package com.example.trabalhopratico.UI

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.trabalhopratico.R
import com.example.trabalhopratico.database.DBHelper
import com.example.trabalhopratico.databinding.ActivityCursosCesaeactivityBinding
import com.example.trabalhopratico.model.Cursos

class CursosCESAEActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCursosCesaeactivityBinding
    private lateinit var cursoList: ArrayList<Cursos>
    private lateinit var adapter: ArrayAdapter<Cursos>
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
            startActivity(Intent(applicationContext, NewCursoActivity::class.java))
        }
    }
}