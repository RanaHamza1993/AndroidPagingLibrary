package com.hamza.androidpaginglibrary.pagingData
import androidx.paging.PageKeyedDataSource
import com.hamza.androidpaginglibrary.endpoints.UserServiceOld
import com.hamza.androidpaginglibrary.models.User
import com.hamza.androidpaginglibrary.models.UserResponse
import com.hamza.androidpaginglibrary.utils.ServiceBuilder
import kotlinx.coroutines.CoroutineScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
/**
 * Created by Morris on 03,June,2019
 */
class UserDataSourceOld(scope:CoroutineScope) : PageKeyedDataSource<Int, User>() {
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        val service = ServiceBuilder(UserServiceOld::class.java)
        val call = service.getUsers(params.key)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.users
                    val key = if (params.key > 1) params.key - 1 else 0
                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            }
        })
    }
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, User>) {
        val service = ServiceBuilder(UserServiceOld::class.java)
        val call = service.getUsers(FIRST_PAGE)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.users
                    responseItems?.let {
                        callback.onResult(responseItems, null, FIRST_PAGE + 1)
                    }
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            }
        })
    }
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        val service = ServiceBuilder(UserServiceOld::class.java)
        val call = service.getUsers(params.key)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.users
                    val key = params.key + 1
                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            }
        })
    }
    companion object {
        const val PAGE_SIZE = 6
        const val FIRST_PAGE = 1
    }
}