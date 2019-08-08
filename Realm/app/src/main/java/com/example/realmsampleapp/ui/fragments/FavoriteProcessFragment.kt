package com.example.realmsampleapp.ui.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.realmsampleapp.R
import com.example.realmsampleapp.models.ProcessViewModel
import com.example.realmsampleapp.models.UserViewModel
import com.example.realmsampleapp.ui.adapter.ProcessAdapter
import com.example.realmsampleapp.ui.adapter.ProcessClickListener
import com.example.realmsampleapp.ui.viewmodel.FavoriteProcessFragmentViewModel
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_favorite_process_selection.*
import javax.inject.Inject

class FavoriteProcessFragment : DaggerFragment(), ProcessClickListener {

    companion object {
        const val TAG = "FavoriteProcessFragment"
        private const val KEY_USER_VIEW_MODEL = "USER_VIEW_MODEL"
        fun newInstance(userViewModel: UserViewModel): FavoriteProcessFragment {
            val moshi = Moshi.Builder().build()

            val fragment = FavoriteProcessFragment()
            val scheduleValueJsonString =
                moshi.adapter(UserViewModel::class.java).toJson(userViewModel)
            val bundle = Bundle()
            bundle.putString(KEY_USER_VIEW_MODEL, scheduleValueJsonString)
            fragment.arguments = bundle

            return fragment
        }
    }

    @Inject
    lateinit var viewModel: FavoriteProcessFragmentViewModel
    private lateinit var processAdapter: ProcessAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_favorite_process_selection, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupLiveDataObservables()
        setUpRecyclerView()
        setUserViewModelIfPossible()
    }

    private fun setUpRecyclerView() {
        favoriteProcessRecyclerView.layoutManager = LinearLayoutManager(context)
        processAdapter = ProcessAdapter(this)
        favoriteProcessRecyclerView.adapter = processAdapter
    }

    private fun setupLiveDataObservables() {
        viewModel.favoriteProcessViewModelList()
            .observe(
                this,
                Observer {
                    it?.let { favoriteProcessViewModels ->
                        processAdapter.setProcessValues(favoriteProcessViewModels)
                    }
                })
    }

    private fun setUserViewModelIfPossible() {
        val userViewModelJsonString = arguments?.getString(KEY_USER_VIEW_MODEL)

        userViewModelJsonString?.let {
            try {
                val moshi = Moshi.Builder().build()
                val userViewModel =
                    moshi.adapter(UserViewModel::class.java).fromJson(userViewModelJsonString)
                userViewModel?.let {
                    viewModel.onCreateWithData(userViewModel)
                } ?: Log.w(javaClass.simpleName, "Error parsing schedule job value.")
                return
            } catch (e: JsonDataException) {
                Log.w(javaClass.simpleName, e)

            }
        }
    }

    override fun onProcessClicked(process: ProcessViewModel, isFavorited: Boolean) {
        viewModel.onFavoriteProcessClicked(process, isFavorited)
    }
}