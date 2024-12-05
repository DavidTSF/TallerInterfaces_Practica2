package dev.davidvega.practica2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import dev.davidvega.practica2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        val adapter = FragmentAdapter(this)
        binding.pager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.pager) {
                tab, position -> tab.text = "TAB ${(position + 1)}"
        }.attach()


    }
}