package com.example.reto1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.reto1.Fragment.HomeFragment
import com.example.reto1.Fragment.NewPublicationFragment
import com.example.reto1.Fragment.ProfileFragment
import com.example.reto1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var homeFragment: HomeFragment
    private lateinit var newPublicationFragment: NewPublicationFragment
    private lateinit var profileFragment: ProfileFragment

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

        homeFragment = HomeFragment.newInstance()
        newPublicationFragment = NewPublicationFragment.newInstance()
        profileFragment = ProfileFragment.newInstance()

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




}