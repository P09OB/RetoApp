package com.example.reto1.Fragment

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.reto1.User
import com.example.reto1.UtilDomi
import com.example.reto1.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!
    var listerner : OnUserDataChangedListener? = null
    var listernerUser: onUserListener? = null

    var bitmap :Bitmap? = null
    lateinit var userName : String
    var userRegister : Boolean = false
    lateinit var bundle : Bundle

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
            Toast.makeText(context,"Pulsa el botón editar perfil para efectuar los cambios",Toast.LENGTH_LONG).show()

        }
        if(!userRegister){
            userName = requireActivity().intent.extras?.getString("username").toString()
        }
        if(userName.equals("")){
        } else{
            binding.nameProfile.text = userName
        }
        binding.imageProfile.setImageBitmap(bitmap)

        binding.logoutBttn.setOnClickListener {
            val user = User(userName,"correo",bitmap)
            var active = false
            listernerUser?.let {
                it.onUserListener(active)
            }

        }


        binding.editProfileBttn.setOnClickListener {
            userName = binding.nameET.text.toString()
            val user = User(userName,"correo",bitmap)
            if(userName.equals("")){
            } else{
                binding.nameProfile.text = userName
                listerner?.let {
                    it.onUserDataChanged(user)
                }
                binding.nameET.setText("")
                userRegister = true
            }
            Toast.makeText(context,"Se ha editado tu perfil con éxito",Toast.LENGTH_LONG).show()

        }


        return view
    }



    fun onGallareyResult(activityResult: ActivityResult?) {
        val uri = activityResult?.data?.data
        val path = UtilDomi.getPath(requireContext(),uri!!)
        bitmap = BitmapFactory.decodeFile(path)
        /*val scaledBitmap = Bitmap.createScaledBitmap(
            bitmap,
            bitmap.width/2,
            bitmap.height/2,
            true
        )*/
        binding.imageProfile.setImageBitmap(bitmap)

    }


    interface OnUserDataChangedListener{
        fun onUserDataChanged (user : User)
    }
    interface onUserListener{
        fun onUserListener (active: Boolean)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listernerUser = context as? onUserListener

    }
    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()

    }


}

