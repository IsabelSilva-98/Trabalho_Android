package com.example.trabalhopratico.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.trabalhopratico.R
import com.example.trabalhopratico.database.DBHelper
import com.example.trabalhopratico.databinding.ActivityMainBinding
import com.example.trabalhopratico.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val db = DBHelper(this)

        binding.buttonRegistar.setOnClickListener {
            val username = binding.editUsername.text.toString()
            val password =  binding.editPassword.text.toString()
            val passConfirm = binding.editConfirmarPassword.text.toString()

            if(username.isNotEmpty() && password.isNotEmpty() && passConfirm.isNotEmpty()) {

                if (password == passConfirm) {
                    val res = db.insertUser(username, password)
                    if(res > 0){
                        Toast.makeText(applicationContext, getString(R.string.registo_efetuado), Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    else{
                        Toast.makeText(applicationContext, getString(R.string.erro_no_registo2), Toast.LENGTH_SHORT).show()

                        binding.editUsername.setText("")
                        binding.editPassword.setText("")
                        binding.editConfirmarPassword.setText("")
                    }
                }
                else{
                    Toast.makeText(applicationContext, getString(R.string.as_passwords_n_o_coincidem), Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(applicationContext, getString(R.string.por_favor_preencha_todos_os_campos_obrigat_rios2), Toast.LENGTH_LONG).show()
            }
        }
    }
}