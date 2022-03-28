package com.example.reto1.Fragment

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.reto1.R
import com.example.reto1.UtilDomi
import com.example.reto1.databinding.FragmentNewPublicationBinding
import java.io.File


class NewPublicationFragment : Fragment() {

    private lateinit var binding: FragmentNewPublicationBinding

    private var file:File? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewPublicationBinding.inflate(inflater,container,false)

        val launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ::onGallareyResult)

        val camLauncer = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(), ::onCameraResult)

        binding.galeryBtt.setOnClickListener {
            val i = Intent(Intent.ACTION_GET_CONTENT)
            i.type = "image/*"
            launcher.launch(i)
        }

        binding.cameraBtt.setOnClickListener {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            file = File("${context?.getExternalFilesDir(null)}/photo.png")
            val uri =
                context?.let { it1 -> FileProvider.getUriForFile(it1,"com.example.reto1",file!!) }
            i.putExtra(MediaStore.EXTRA_OUTPUT,uri)
            Log.e(">>>>",file?.path.toString())
            camLauncer.launch(i)
        }

        return binding.root
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

    }

    private fun onCameraResult(activityResult: ActivityResult ) {
        val bitmap = BitmapFactory.decodeFile(file?.path)
        val aspectRation = (bitmap.width.toFloat())/bitmap.height
        val scaleBitmap =
            Bitmap.createScaledBitmap(
                bitmap,
                300,
                (300/aspectRation).toInt(),
                true
            )

    }

        companion object {

        @JvmStatic
        fun newInstance() = NewPublicationFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}