package com.example.reto1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.reto1.Fragment.HomeFragment
import com.example.reto1.Fragment.NewPublicationFragment
import com.example.reto1.Fragment.ProfileFragment
import com.example.reto1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProfileFragment.onUserListener {

    private lateinit var homeFragment: HomeFragment
    private lateinit var newPublicationFragment: NewPublicationFragment
    private lateinit var profileFragment: ProfileFragment
    private var modeUser: Boolean = false
    private val launcher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::onResult)

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        requestPermissions(arrayOf(
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA
        ),1)

        val preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE)
        modeUser = preferencias.getBoolean("mode", false)

        user(modeUser, launcher)

        homeFragment = HomeFragment.newInstance()
        newPublicationFragment = NewPublicationFragment.newInstance()
        profileFragment = ProfileFragment.newInstance()

        newPublicationFragment.listener = homeFragment
        profileFragment.listerner = newPublicationFragment

        showFragment(homeFragment)

        binding.navigator.setOnItemSelectedListener { menuItem ->

            if(menuItem.itemId == R.id.homeitem){

                showFragment(homeFragment)

            } else if (menuItem.itemId == R.id.publicationitem){

                showFragment (newPublicationFragment)

            } else if(menuItem.itemId == R.id.profileitem) {

                showFragment (profileFragment)

            }
            true
        }

    }

    fun showFragment(fragment : Fragment){

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer,fragment)
        transaction.commit()
    }

    fun user(active: Boolean, launcher: ActivityResultLauncher<Intent>){
        if(!active){
            val intent = Intent(this, Login::class.java)
            launcher.launch(intent)
            val sharedPreference =  getSharedPreferences("datos",Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putBoolean("mode",active)
            editor.commit()
        }


    }

    private fun onResult(activityResult: ActivityResult) {

    }

    override fun onUserListener(active: Boolean) {
        this.modeUser = active
        user(active,launcher)

    }


}