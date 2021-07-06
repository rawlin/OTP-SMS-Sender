package com.example.otp.ui.mainScreen

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.otp.R
import com.example.otp.ui.contactsList.ContactsListFragment
import com.example.otp.ui.sentMessageList.MessagesSentFragment

private val TAB_TITLES = arrayOf(
    R.string.contacts,
    R.string.messages
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                ContactsListFragment()
            }
            1 -> {
                MessagesSentFragment()
            }
            else -> ContactsListFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}