package kz.batana.finalandroid.main

import android.graphics.BitmapFactory
import android.os.Environment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_contact.view.*
import kz.batana.finalandroid.R
import kz.batana.finalandroid.core.entity.Contact
import java.io.File


class MainAdapter(private var contacts: ArrayList<Contact>,
                  private val listener: OnItemClickListener)
    : RecyclerView.Adapter<MainAdapter.ContactsViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bind(contacts[position])
    }


    inner class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        fun bind(contact: Contact) {
            itemView.itemName.text = contact.name
            itemView.itemGroup.text = contact.contactGroup.name

            val imgFile = File(Environment.getExternalStorageDirectory().toString()+contact.phoneImageUrl)
            Log.d("accepted", contact.toString() +
                    "\n${Environment.getExternalStorageDirectory().toString()+contact.phoneImageUrl}")
            if (imgFile.exists()) {
                Log.e("accepted", "existssssssss")
                val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
                itemView.ivItem.setImageBitmap(myBitmap)
            }else{
                Log.e("accepted", "do not existssssssss")
            }
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            var pos: Int = adapterPosition
            listener.onCourseClicked(contacts[pos])
        }

    }

    interface OnItemClickListener {
        fun onCourseClicked(course: Contact)
    }
}