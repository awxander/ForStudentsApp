package com.example.forstudents.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.example.forstudents.R
import com.example.forstudents.databinding.ActivityMainBinding
import com.example.forstudents.ui.fragment.MessagesFragment
import com.example.forstudents.ui.fragment.ProfileFragment
import com.example.forstudents.ui.fragment.QuestionsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val profileFragment = ProfileFragment()
    private val messagesFragment = MessagesFragment()
    private val questionsFragment = QuestionsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomNav()
        setCurrentFragment(questionsFragment)
    }

    private fun setBottomNav() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.profile -> {
                    setCurrentFragment(profileFragment)
                }
                R.id.messages -> {
                    setCurrentFragment(messagesFragment)
                }
                R.id.questions -> {
                    setCurrentFragment(questionsFragment)
                }
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, fragment)
            commit()
        }

    fun hideBottomNav() {
        if (binding.bottomNavigationView.visibility == View.VISIBLE) {
            val slideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down)
            binding.bottomNavigationView.apply {
                startAnimation(slideDown)
                visibility = View.GONE
            }
        }
    }

    fun showBottomNav() {
        if (binding.bottomNavigationView.visibility != View.VISIBLE) {
            val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)
            binding.bottomNavigationView.apply {
                startAnimation(slideUp)
                visibility = View.VISIBLE
            }
        }
    }


}