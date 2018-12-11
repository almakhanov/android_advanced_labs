package kz.batana.finalandroid.main

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_deatils.*
import kz.batana.finalandroid.R
import kz.batana.finalandroid.core.entity.Contact
import java.io.File

class DeatilsActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deatils)

        val contact = intent.getSerializableExtra("key") as Contact

        val imgFile = File(Environment.getExternalStorageDirectory().toString()+contact.phoneImageUrl)
        Log.d("accepted", contact.toString() +
                "\n${Environment.getExternalStorageDirectory().toString()+contact.phoneImageUrl}")
        if (imgFile.exists()) {
            Log.e("accepted", "existssssssss")
            val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
            image_view.setImageBitmap(myBitmap)
        }else{
            Log.e("accepted", "do not existssssssss")
        }

        edit_text_sign_in_username.text = "name : "+contact.name
        mobilephoneValue.text  = "mobilePhone : "+contact.mobilePhone
        homephoeValue.text = "homePhone : " + contact.homePhone
        workphoneValue.text = "work phone : " + contact.workPhone
        groupName.text = "group name : " +  contact.contactGroup.name + "   priority : " + contact.contactGroup.priority

    }
}
