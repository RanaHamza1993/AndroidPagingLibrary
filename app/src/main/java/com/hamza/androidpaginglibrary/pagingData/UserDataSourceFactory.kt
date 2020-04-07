package com.hamza.androidpaginglibrary.pagingData

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.hamza.androidpaginglibrary.models.User

class UserDataSourceFactory: DataSource.Factory<Int, User>() {
    private val userLiveDataSource = MutableLiveData<UserDataSource>()

    override fun create(): DataSource<Int, User> {
        val userDataSource = UserDataSource()
        userLiveDataSource.postValue(userDataSource)
        return userDataSource

    }
    fun getUserLiveDataSource():LiveData<UserDataSource>{
        return userLiveDataSource
    }
}