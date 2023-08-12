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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trabalhopratico.R
import com.example.trabalhopratico.adapter.CursoListAdapter
import com.example.trabalhopratico.adapter.listener.CursoOnClickListener
import com.example.trabalhopratico.database.DBHelper
import com.example.trabalhopratico.databinding.ActivityCursosCesaeactivityBinding
import com.example.trabalhopratico.model.Cursos

class CursosCESAEActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCursosCesaeactivityBinding
    private lateinit var cursoList: List<Cursos>
    //private lateinit var adapter: ArrayAdapter<Cursos>
    private lateinit var adapter: CursoListAdapter
    private lateinit var db: DBHelper
    private lateinit var result: ActivityResultLauncher<Intent>
    private var ascDesc: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCursosCesaeactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DBHelper(this)
        val sharedPreferences = application.getSharedPreferences("login", Context.MODE_PRIVATE)

        binding.buttonLogout.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("username", "")
            editor.apply()
            finish()
        }

        binding.recyclerViewCursos.layoutManager = LinearLayoutManager(applicationContext)

        loadList()

        /*cursoList = db.selectAllCurso()

        adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, cursoList)

        binding.listViewCursos.adapter = adapter*/

        /*binding.listViewCursos.setOnItemClickListener { _, _, position, _ ->
            /*Toast.makeText(applicationContext, cursoList[position].nome, Toast.LENGTH_SHORT).show()*/
            val intent = Intent(applicationContext, CursoDetailActivity::class.java)
            intent.putExtra("id", cursoList[position].id)
            //startActivity(intent)

            result.launch(intent)
        }*/

        binding.buttonAdd.setOnClickListener {
            result.launch(Intent(applicationContext, NewCursoActivity::class.java))
        }

        binding.buttonOrdem.setOnClickListener {
            if(ascDesc){
                binding.buttonOrdem.setImageResource(R.drawable.baseline_arrow_upward_24)
            }
            else{
                binding.buttonOrdem.setImageResource(R.drawable.baseline_arrow_downward_24)
            }
            ascDesc = !ascDesc
            cursoList = cursoList.reversed()
            placeAdapter()
        }

        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.data!=null && it.resultCode == 1){
                loadList()
            }
            else if(it.data!=null && it.resultCode == 0){
                Toast.makeText(applicationContext, getString(R.string.opera_o_cancelada), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun placeAdapter(){
        adapter = CursoListAdapter(cursoList, CursoOnClickListener { curso ->
            val intent = Intent(applicationContext, CursoDetailActivity::class.java)
            intent.putExtra("id", curso.id)
            result.launch(intent)
        })
        binding.recyclerViewCursos.adapter = adapter
    }
    private fun loadList() {
        cursoList = db.selectAllCurso().sortedWith(compareBy{it.nome})
        cursoList = db.selectAllCurso().sortedWith(compareBy{it.dataArranque})
        placeAdapter()
    }
}