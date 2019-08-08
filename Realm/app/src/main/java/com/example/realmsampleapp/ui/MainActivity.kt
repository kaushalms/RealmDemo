package com.example.realmsampleapp.ui

import android.os.Bundle
import com.example.realmsampleapp.R
import com.example.realmsampleapp.models.UserViewModel
import com.example.realmsampleapp.ui.fragments.FavoriteProcessFragment
import com.example.realmsampleapp.ui.fragments.UsersFragment
import com.example.realmsampleapp.ui.viewmodel.MainViewModel
import com.example.realmsampleapp.utils.ext.addFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(container.id, UsersFragment.newInstance(), UsersFragment.TAG)
    }

    fun openFavoriteProcessForUser(userViewModel: UserViewModel) {
        addFragment(container.id, FavoriteProcessFragment.newInstance(userViewModel), FavoriteProcessFragment.TAG)
    }
}
