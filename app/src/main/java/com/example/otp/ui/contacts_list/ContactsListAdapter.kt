package com.example.otp.ui.contacts_list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.otp.databinding.ContactsItemBinding
import com.example.otp.models.Contacts

class ContactsListAdapter : RecyclerView.Adapter<ContactsListAdapter.ContactsViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Contacts>() {

        override fun areItemsTheSame(oldItem: Contacts, newItem: Contacts): Boolean {
            return oldItem.phoneNumber == newItem.phoneNumber
        }

        override fun areContentsTheSame(oldItem: Contacts, newItem: Contacts): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list: List<Contacts>) {
        differ.submitList(list)
    }

    inner class ContactsViewHolder(private val binding: ContactsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Contacts) = with(binding) {
            contactName.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val itemBinding = ContactsItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ContactsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val contactDetails = differ.currentList[position]

        with(holder) {
            bind(contactDetails)
            itemView.setOnClickListener {
                onItemClickListener?.let { it(contactDetails) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Contacts) -> Unit)? = null

    fun setOnItemClickListener(listener: (Contacts) -> Unit) {
        onItemClickListener = listener
    }

}

