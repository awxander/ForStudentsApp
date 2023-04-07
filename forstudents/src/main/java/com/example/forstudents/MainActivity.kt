package com.example.forstudents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.example.forstudents.databinding.ActivityMainBinding
import com.example.forstudents.fragments.MessagesFragment
import com.example.forstudents.fragments.ProfileFragment
import com.example.forstudents.fragments.QuestionsFragment

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
        val slideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        binding.bottomNavigationView.apply {
            startAnimation(slideDown)
            visibility = View.GONE
        }
    }

    fun showBottomNav() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }


}