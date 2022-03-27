package com.example.reto1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.reto1.databinding.ActivityMainBinding

class Login : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::onResult)


        binding.loginButton.setOnClickListener {

            var user = binding.userET.text.toString()
            var password = binding.passwordEt.text.toString()

            if((user.equals("alfa@gmail.com") && password.equals("aplicacionesmoviles"))
                || (user.equals("beta@gmail.com")  && password.equals("aplicacionesmoviles"))){

                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("user",user)
                }
                launcher.launch(intent)

            } else {
                Toast.makeText(this, "Verifique el correo y la contraseña", Toast.LENGTH_LONG).show()
            }


        }
    }

    private fun onResult(activityResult: ActivityResult) {



    }
}