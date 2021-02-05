package com.eniola.usermanagementapp.base

import dagger.android.support.DaggerFragment


abstract class BaseFragment : DaggerFragment(){
    abstract fun observeData()
}