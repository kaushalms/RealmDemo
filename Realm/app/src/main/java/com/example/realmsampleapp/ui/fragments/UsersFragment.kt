package com.example.realmsampleapp.ui.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.realmsampleapp.R
import com.example.realmsampleapp.models.UserViewModel
import com.example.realmsampleapp.ui.MainActivity
import com.example.realmsampleapp.ui.adapter.UserAdapter
import com.example.realmsampleapp.ui.adapter.UserClickListener
import com.example.realmsampleapp.ui.viewmodel.UsersFragmentViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_user_selection.*
import javax.inject.Inject

class UsersFragment : DaggerFragment(), UserClickListener {

    companion object {
        const val TAG = "UsersFragment"
        fun newInstance(): UsersFragment = UsersFragment()
    }

    @Inject
    lateinit var viewModel: UsersFragmentViewModel

    private lateinit var usersAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_user_selection, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupLiveDataObservables()
        setUpRecyclerView()
        viewModel.onCreate()
    }

    private fun setUpRecyclerView() {
        userSelectionRecyclerView.layoutManager = LinearLayoutManager(context)
        usersAdapter = UserAdapter(this)
        userSelectionRecyclerView.adapter = usersAdapter
    }

    private fun setupLiveDataObservables() {
        viewModel.userViewModelList()
            .observe(this, Observer { it?.let { userViewModels -> usersAdapter.setProcessValues(userViewModels) } })
    }

    override fun onUserClicked(
        userViewModel: UserViewModel,
        adapterClickedPosition: Int
    ) {
        userSelectionRecyclerView.post { usersAdapter.clearPreviousSelection(adapterClickedPosition) }
        (activity as? MainActivity)?.openFavoriteProcessForUser(userViewModel)
    }
}
