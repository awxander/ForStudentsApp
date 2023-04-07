package com.example.forstudents.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.forstudents.MainActivity


fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}


fun Fragment.hideBottomNavigation() {
    val activity = activity as MainActivity
    activity.hideBottomNav()
}


fun Fragment.showBottomNavigation() {
    val activity = activity as MainActivity
    activity.showBottomNav()
}