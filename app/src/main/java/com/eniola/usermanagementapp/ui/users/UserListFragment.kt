package com.eniola.usermanagementapp.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eniola.usermanagementapp.R
import com.eniola.usermanagementapp.base.BaseFragment


class UserListFragment : BaseFragment() {

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


        //initiate service to update users table

        //fetch all users from database

        //on click of users, navigate to the detail page

        //design detail page

        //show user data on detail page


        //write test

        //write readMe

        //try ci/cd


    }

    override fun observeData() {

    }
}