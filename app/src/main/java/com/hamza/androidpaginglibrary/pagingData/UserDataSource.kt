package com.hamza.androidpaginglibrary.pagingData

import androidx.paging.PageKeyedDataSource
import com.hamza.androidpaginglibrary.endpoints.UserService
import com.hamza.androidpaginglibrary.endpoints.UserServiceOld
import com.hamza.androidpaginglibrary.models.User
import com.hamza.androidpaginglibrary.models.UserResponse
import com.hamza.androidpaginglibrary.utils.ServiceBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Morris on 03,June,2019
 */
class UserDataSource(private val scope: CoroutineScope) : PageKeyedDataSource<Int, User>() {
    private val service = ServiceBuilder(UserService::class.java)
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        scope.launch {
            try {
                val response: Response<UserResponse> = service.getUsers(params.key)
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.users
                    val key = if (params.key > 1) params.key - 1 else 0
                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }
            } catch (e: Exception) {

            }
        }

    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, User>
    ) {
        scope.launch {
            try {
                val response = service.getUsers(FIRST_PAGE)
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.users
                    responseItems?.let {
                        callback.onResult(responseItems, null, FIRST_PAGE + 1)
                    }
                }

            } catch (e: Exception) {

            }
        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        scope.launch {
            try {
                val response = service.getUsers(params.key)
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.users
                    val key = params.key + 1
                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }
            } catch (e: Exception) {

            }
        }
    }
    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }


    companion object {
        const val PAGE_SIZE = 6
        const val FIRST_PAGE = 1
    }
}