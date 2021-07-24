package com.example.otp.ui.sent_message_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.otp.R
import com.example.otp.database.MessagesSent
import com.example.otp.databinding.MessagesSentItemBinding
import com.example.otp.utils.getDateAndTimeString

class MessagesSentListAdapter(private val context: Context) :
    RecyclerView.Adapter<MessagesSentListAdapter.MessagesSentViewHolder>() {
    private val differCallback = object : DiffUtil.ItemCallback<MessagesSent>() {

        override fun areItemsTheSame(oldItem: MessagesSent, newItem: MessagesSent): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MessagesSent, newItem: MessagesSent): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list: List<MessagesSent>) {
        differ.submitList(list)
    }

    inner class MessagesSentViewHolder(private val binding: MessagesSentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MessagesSent) = with(binding) {
            contactName.text = item.name
            otpView.text = context.getString(R.string.otp, item.otp)
            timeView.text = context.getString(R.string.sent_at, getDateAndTimeString(item.time))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesSentViewHolder {
        val itemBinding =
            MessagesSentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MessagesSentViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MessagesSentViewHolder, position: Int) {
        val itemDetails = differ.currentList[position]
        with(holder) {
            bind(itemDetails)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}