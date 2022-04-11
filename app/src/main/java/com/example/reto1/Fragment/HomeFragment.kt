package com.example.reto1.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reto1.Fragment.ProfileFragment.*
import com.example.reto1.Post
import com.example.reto1.PostsAdapter
import com.example.reto1.R
import com.example.reto1.User
import com.example.reto1.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment(), NewPublicationFragment.onNewPostListener{

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter = PostsAdapter()

    var user = User("alfa","alfa@gmail.com",null)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root

        //Recrear el estado
        val postRecycler = binding.postRecycler
        postRecycler.setHasFixedSize(true)
        postRecycler.layoutManager = LinearLayoutManager(activity)
        postRecycler.adapter = adapter

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun onNewPost(post: Post) {
        val newpost = Post(UUID.randomUUID().toString(),post.nameUser,post.photoUser,post.description, post.photo,post.date,post.city)
        adapter.adapterPost(newpost)

    }






}