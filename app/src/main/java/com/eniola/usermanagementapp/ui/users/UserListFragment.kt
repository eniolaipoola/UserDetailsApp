package com.eniola.usermanagementapp.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.eniola.studyapp.utility.hide
import com.eniola.studyapp.utility.show
import com.eniola.studyapp.utility.toast
import com.eniola.usermanagementapp.BuildConfig
import com.eniola.usermanagementapp.R
import com.eniola.usermanagementapp.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_list.*
import javax.inject.Inject


class UserListFragment : BaseFragment(), UserListAdapter.UserClickedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<UserViewModel> { viewModelFactory }
    private val adapter by lazy { UserListAdapter(this) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //make api call to fetch all users
        val apiKey = BuildConfig.API_KEY
        viewModel.fetchAllUsers(apiKey)

        //fetch all users from database
        viewModel.getUserFromDatabase()


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
                    //pass data list fetched from db to recyclerview
                    adapter.setListItems(viewState.data)
                    user_list_recyclerview.layoutManager = LinearLayoutManager(context,
                        LinearLayoutManager.VERTICAL, false)
                    user_list_recyclerview.adapter = adapter
                }

                is ViewState.ERROR -> {
                    loader.hide()
                    activity?.toast(viewState.errorMessage)
                }

                is ViewState.LOADING -> {
                    if(viewState.loading){
                        loader.show()
                    } else {
                        loader.hide()
                    }
                }

                is ViewState.USER -> {
                    //get user detail object, pass it to the detail page
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJob()
    }

    //on click of users, navigate to the detail page
    override fun onUserClicked(view: View, item: UserData) {
        //pick user id
        val userId = item.id

        //fetch user details
        viewModel.getAUser(BuildConfig.API_KEY, userId)

    }
}