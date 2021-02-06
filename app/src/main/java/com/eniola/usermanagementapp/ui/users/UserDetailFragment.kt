package com.eniola.usermanagementapp.ui.users

import android.annotation.SuppressLint
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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user_detail.*
import javax.inject.Inject

class UserDetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<UserViewModel> { viewModelFactory }
    private var userId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun observeData() {
        viewModel.state.observe(viewLifecycleOwner) { viewState ->
            when(viewState) {
                is ViewState.ERROR -> {
                    loader.hide()
                    activity?.toast(viewState.errorMessage) }

                is ViewState.LOADING -> {
                    if(viewState.loading){
                        loader.show()
                    } else {
                        loader.hide()
                    }
                }

                is ViewState.USERDETAIL -> {
                    loader.hide()
                    //get user detail object, display required detail on page
                    val userObject = viewState.data
                    if(userObject != null){
                        profile_name.text = """${userObject.firstName} ${userObject.lastName}"""

                        val imageUrl = userObject.picture
                        //load image url into imageView using picasso
                        Picasso.get().load(imageUrl).placeholder(
                                R.drawable.ic_profile_icon).fit().into(image)

                        user_email.text = getString(R.string.detail_page_email) + userObject.email
                        user_location.text = userObject.location.country+ ", " + userObject.location.state
                        phone_number_text.text = getString(R.string.detail_page_phone_text) + userObject.phone
                        gender.text = getString(R.string.detail_page_gender) + userObject.gender
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get passed in id
        val bundle = arguments
        if(bundle != null){
            userId = bundle.getString("userId")
            if (userId != null) {
                //makes api call to fetch data
                viewModel.fetchUserDetailFromApi(BuildConfig.API_KEY, userId!!)

                //fetch user information from db
                viewModel.getUserDetailFromDatabase(userId!!)
            }
        }

        observeData()
    }
}