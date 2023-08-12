package com.example.trabalhopratico.UI

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.trabalhopratico.R
import com.example.trabalhopratico.database.DBHelper
import com.example.trabalhopratico.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val db = DBHelper(this)

        sharedPreferences = application.getSharedPreferences("login", Context.MODE_PRIVATE)
        val username =sharedPreferences.getString("username", "")
        if (username != null) {
            if(username.isNotEmpty()){
                startActivity(Intent(this, CursosCESAEActivity::class.java))
            }
        }

        binding.buttonLogin.setOnClickListener {
            val username = binding.editUsername.text.toString()
            val password = binding.editPassword.text.toString()
            val logged = binding.checkboxLogin.isChecked

            if(username.isNotEmpty() && password.isNotEmpty()){

                if (db.login(username, password)) {
                    if(logged){
                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("username", username)
                        editor.apply()
                    }
                    startActivity(Intent(this, CursosCESAEActivity::class.java))
                    Toast.makeText(applicationContext, getString(R.string.login_v_lido), Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(applicationContext, getString(R.string.erro_no_registo), Toast.LENGTH_LONG).show()
                    binding.editUsername.setText("")
                    binding.editPassword.setText("")
                }
            }
            else{
                Toast.makeText(applicationContext, getString(R.string.por_favor_preencha_todos_os_campos_obrigat_rios), Toast.LENGTH_LONG).show()
            }
        }

        binding.textRegistar.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.textSobre.setOnClickListener {
            startActivity(Intent(this, SobreActivity::class.java))
        }

    }
}