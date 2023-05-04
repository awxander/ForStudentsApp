package com.example.forstudents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.forstudents.data.repository.ForStudentsRepositoryImpl
import com.example.forstudents.databinding.ActivityMainBinding
import com.example.forstudents.ui.fragments.MessagesFragment
import com.example.forstudents.ui.fragments.ProfileFragment
import com.example.forstudents.ui.fragments.QuestionsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val profileFragment = ProfileFragment()
    private val messagesFragment = MessagesFragment()
    private val questionsFragment = QuestionsFragment()
    val repository = ForStudentsRepositoryImpl()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomNav()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)//выключил night mode
        //TODO перенести в Application
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