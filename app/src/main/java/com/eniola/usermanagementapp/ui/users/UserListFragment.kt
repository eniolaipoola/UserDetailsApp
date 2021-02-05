package com.eniola.usermanagementapp.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.eniola.studyapp.utility.hide
import com.eniola.studyapp.utility.show
import com.eniola.studyapp.utility.toast
import com.eniola.usermanagementapp.BuildConfig
import com.eniola.usermanagementapp.R
import com.eniola.usermanagementapp.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_list.*
import javax.inject.Inject


class UserListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<UserViewModel> { viewModelFactory }


    companion object {
        @JvmStatic
        fun newInstance() = UserListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //make api call to fetch all users
        val apiKey = BuildConfig.API_KEY
        viewModel.fetchAllUsers(apiKey)

        //initiate service to update users table

        //fetch all users from database

        //on click of users, navigate to the detail page

        //design detail page

        //show user data on detail page


        //write test

        //write readMe

        //try ci/cd


        observeData()

    }

    override fun observeData() {
        viewModel.state.observe(viewLifecycleOwner) { viewState ->
            when(viewState) {
                is ViewState.SUCCESS -> {

                }

                is ViewState.ERROR -> {
                    activity?.toast(viewState.errorMessage)
                }

                is ViewState.LOADING -> {
                    if(viewState.loading){
                        loader.show()
                    } else {
                        loader.hide()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJob()
    }
}