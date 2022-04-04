package com.example.reto1.Fragment

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.reto1.R
import com.example.reto1.UtilDomi
import com.example.reto1.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        val view = binding.root

        val launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ::onGallareyResult)

        binding.editimageporfile.setOnClickListener{
            val i = Intent(Intent.ACTION_GET_CONTENT)
            i.type = "image/*"
            launcher.launch(i)
        }
        return view
    }

    fun onGallareyResult(activityResult: ActivityResult?) {
        val uri = activityResult?.data?.data
        val path = UtilDomi.getPath(requireContext(),uri!!)
        val bitmap = BitmapFactory.decodeFile(path)
        val scaledBitmap = Bitmap.createScaledBitmap(
            bitmap,
            bitmap.width/2,
            bitmap.height/2,
            true
        )
        binding.imagePorfile.setImageBitmap(bitmap)

    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}