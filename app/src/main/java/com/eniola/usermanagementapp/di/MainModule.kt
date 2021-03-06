package com.eniola.usermanagementapp.di

import androidx.lifecycle.ViewModel
import com.eniola.usermanagementapp.ui.MainActivity
import com.eniola.usermanagementapp.ui.users.FetchUserService
import com.eniola.usermanagementapp.ui.users.UserDetailFragment
import com.eniola.usermanagementapp.ui.users.UserListFragment
import com.eniola.usermanagementapp.ui.users.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
abstract class MainModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun userListFragment(): UserListFragment

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun userDetailFragment(): UserDetailFragment

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun fetchUserService(): FetchUserService

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    internal abstract fun userViewModel(viewModel: UserViewModel): ViewModel
}