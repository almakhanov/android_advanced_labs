package kz.batana.finalandroid.add_contact

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_contact.*
import kz.batana.finalandroid.R
import kz.batana.finalandroid.core.entity.Contact
import kz.batana.finalandroid.core.entity.ContactGroup
import kz.batana.finalandroid.core.util.Logger
import org.koin.android.ext.android.inject
import java.io.FileNotFoundException




class AddContactActivity : AppCompatActivity(), AddContactContract.View, AdapterView.OnItemSelectedListener {


    override val presenter: AddContactContract.Presenter by inject()
    private val RESULT_LOAD_IMG = 123
    private var url = ""
    private var groupList = ArrayList<String>()
    private lateinit var groupObj: ContactGroup
    private var groupObjs = ArrayList<ContactGroup>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        presenter.attachView(this)
        presenter.getGroups()

        image_view.setOnClickListener{
            chooseImage()
        }

        btnSave.setOnClickListener{
            presenter.saveContact(Contact(0, edit_text_sign_in_username.text.toString(),
                    mobilephoneValue.text.toString(),
                    homephoeValue.text.toString(),
                    workphoneValue.text.toString(),
                    url,
                    groupObj))
        }
    }

    override fun goBack() {
        onBackPressed()
    }


    private fun chooseImage(){
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG)
    }

    override fun onActivityResult(reqCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(reqCode, resultCode, data)


        if (resultCode == Activity.RESULT_OK) {
            try {
                val imageUri = data!!.data
                url = imageUri.path
                Log.e("accepted", url)
                val imageStream = contentResolver.openInputStream(imageUri!!)
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                image_view.setImageBitmap(selectedImage)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                Toast.makeText(this@AddContactActivity, "Something went wrong", Toast.LENGTH_LONG).show()
            }

        } else {
            Toast.makeText(this@AddContactActivity, "You haven't picked Image", Toast.LENGTH_LONG).show()
        }
    }

    override fun msg(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun setGroups(list: ArrayList<ContactGroup>) {
        groupObjs = list
        for(it in list){
            groupList.add(it.name!!)
        }
        Logger.msg("accepted", "wqeqwe ${list.size}")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, groupList)
        spinner1.adapter = adapter
        spinner1.onItemSelectedListener = this
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.e("accepted", position.toString())
        groupObj = groupObjs[position]
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}
