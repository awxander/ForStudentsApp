package com.example.forstudents.ui.fragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.forstudents.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}