package com.example.otp.ui.contacts_list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.otp.R
import com.example.otp.databinding.FragmentMainBinding
import com.example.otp.ui.details_screen.DetailsActivity
import com.example.otp.utils.AppConstants.CONTACT_DETAILS

class ContactsListFragment : Fragment() {

    private val contactsViewModel by viewModels<ContactsViewModel>()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val contactsAdapter = ContactsListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            val json = resources.openRawResource(R.raw.contacts).bufferedReader()
                .use { it.readText() }
            contactsViewModel.getContactsFromJson(json)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        setupRecyclerView()

        contactsViewModel.contactsList.observe(viewLifecycleOwner) { contactsList ->
            contactsAdapter.submitList(contactsList)
        }

    }

    private fun setupRecyclerView() {
        binding.contactsRecyclerView.apply {
            adapter = contactsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        contactsAdapter.setOnItemClickListener {
            val intent = Intent(requireContext(), DetailsActivity::class.java).apply {
                putExtra(CONTACT_DETAILS, it)
            }
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}