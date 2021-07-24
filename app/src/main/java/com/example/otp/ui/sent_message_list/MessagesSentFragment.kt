package com.example.otp.ui.sent_message_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.otp.databinding.FragmentMessagesSentBinding


class MessagesSentFragment : Fragment() {
    private var _binding: FragmentMessagesSentBinding? = null
    private val binding get() = _binding!!
    lateinit var messagesSentAdapter: MessagesSentListAdapter
    private val viewModel by viewModels<MessagesSentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessagesSentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()


    }

    private fun setupRecyclerView() {
        messagesSentAdapter = MessagesSentListAdapter(requireContext())
        binding.apply {
            messagesSentRecyclerView.adapter = messagesSentAdapter
            messagesSentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.messagesSentList.observe(viewLifecycleOwner) {
            messagesSentAdapter.submitList(it)
        }
    }
}