package com.hamza.androidpaginglibrary.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.hamza.androidpaginglibrary.models.User
import com.hamza.androidpaginglibrary.pagingData.UserDataSource
import com.hamza.androidpaginglibrary.pagingData.UserDataSourceOld
import com.hamza.androidpaginglibrary.pagingData.UserDataSourceFactory

class UserViewModel: ViewModel() {
    var userPagedList: LiveData<PagedList<User>>
    private var liveDataSource: LiveData<UserDataSource>
    init {
        val itemDataSourceFactory = UserDataSourceFactory(viewModelScope)
        liveDataSource = itemDataSourceFactory.getUserLiveDataSource()
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(UserDataSourceOld.PAGE_SIZE)
            .build()
        userPagedList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()
    }

}