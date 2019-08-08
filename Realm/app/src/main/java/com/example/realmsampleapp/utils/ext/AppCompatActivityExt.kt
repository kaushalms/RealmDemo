package com.example.realmsampleapp.utils.ext

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.addFragment(id: Int, fragment: Fragment?, tag: String) {
    if (fragment != null) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(tag)
            .add(id, fragment, tag)
            .commit()
    }
}