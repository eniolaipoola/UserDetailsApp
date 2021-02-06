package com.eniola.usermanagementapp

import com.eniola.usermanagementapp.repository.UserRepository
import com.eniola.usermanagementapp.repository.remote.NetworkService
import com.eniola.usermanagementapp.ui.users.UserViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(JUnit4::class)
class UserViewModelTest {

    lateinit var viewModel: UserViewModel
    @Mock
    lateinit var userRepository: UserRepository
    @Mock
    lateinit var networkService: NetworkService


/*
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = UserViewModel( networkService, userRepository)
    }
*/


    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun fetchAllUsers_returnData() {
        //val captor = argumentCaptor<DisposableSingleObserver<UserData>>()

        val listOfUsers = TestUserFactory.makeUsers()
        //assertThat(listOfUsers.size > 0)
    }
}