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
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.reto1.Post
import com.example.reto1.R
import com.example.reto1.User
import com.example.reto1.UtilDomi
import com.example.reto1.databinding.FragmentNewPublicationBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class NewPublicationFragment : Fragment(), ProfileFragment.OnUserDataChangedListener {



    private lateinit var binding: FragmentNewPublicationBinding
    private lateinit var city : String
    lateinit var userName : String
    private lateinit var bitmapGalerry : Bitmap
    private var file:File? = null
    private lateinit var user : User
    private  var userRegister : Boolean = false

    //Listener
    var listener: onNewPostListener? = null

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

        val cities = resources.getStringArray(R.array.cities)

        if(!userRegister){
            userName = requireActivity().intent.extras?.getString("username").toString()
            user = User(userName,"alfa@gmail.com",null)
            userRegister =true
        }
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

        binding.citySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                city = cities[position]
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        val nameUser = user.name
        binding.addBtn.setOnClickListener {
            val text = binding.newdescriptionET.text.toString()
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")
            val currentDate = sdf.format(Date())
            val newpost = Post(UUID.randomUUID().toString(),nameUser,user.photo,text, bitmapGalerry,currentDate,city)

            listener?.let {
                it.onNewPost(newpost)
            }

            binding.newdescriptionET.setText("")

            Toast.makeText(context,"Publicado",Toast.LENGTH_LONG).show()
        }

        return binding.root
    }

    fun onGallareyResult(activityResult: ActivityResult?) {
        val uri = activityResult?.data?.data
        val path = UtilDomi.getPath(requireContext(),uri!!)
        val bitmap = BitmapFactory.decodeFile(path)
        bitmapGalerry = Bitmap.createScaledBitmap(
            bitmap,
            bitmap.width/2,
            bitmap.height/2,
            true
        )
        binding.postimage.setImageBitmap(bitmapGalerry)
    }

    private fun onCameraResult(activityResult: ActivityResult ) {
        val bitmap = BitmapFactory.decodeFile(file?.path)
        val aspectRation = (bitmap.width.toFloat())/bitmap.height
        bitmapGalerry =
            Bitmap.createScaledBitmap(
                bitmap,
                300,
                (300/aspectRation).toInt(),
                true
            )


        binding.postimage.setImageBitmap(bitmapGalerry)

    }

    interface onNewPostListener{
        fun onNewPost(post:Post)
    }

    override fun onUserDataChanged(user: User) {
        this.user = user
        userRegister = true
    }

        companion object {

        @JvmStatic
        fun newInstance() = NewPublicationFragment()
    }


}