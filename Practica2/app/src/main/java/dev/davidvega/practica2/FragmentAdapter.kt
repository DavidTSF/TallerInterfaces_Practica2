package dev.davidvega.practica2

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(fragment: MainActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Tab1Fragment()
            1 -> Tab2Fragment()
            2 -> Tab3Fragment()
            else -> Fragment()
        }
    }
}