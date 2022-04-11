package com.example.reto1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.reto1.Fragment.HomeFragment
import com.example.reto1.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private var userActive : Boolean = false
    private lateinit var name :String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sharedPreference =  getSharedPreferences("datos",Context.MODE_PRIVATE)

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::onResult)
        var editor = sharedPreference.edit()

        binding.loginButton.setOnClickListener {

            var user = binding.userET.text.toString()
            var password = binding.passwordEt.text.toString()

            if((user.equals("alfa@gmail.com") && password.equals("aplicacionesmoviles"))
                || (user.equals("beta@gmail.com")  && password.equals("aplicacionesmoviles"))){

                userActive = true
                if(user.equals("alfa@gmail.com")) name = "alfa"
                if(user.equals("beta@gmail.com"))  name = "beta"
                    editor.putBoolean("mode",userActive)
                    editor.commit()

                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("username",name)
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
