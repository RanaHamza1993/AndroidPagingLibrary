package com.hamza.androidpaginglibrary.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamza.androidpaginglibrary.R
import com.hamza.androidpaginglibrary.adapters.UserAdapter
import com.hamza.androidpaginglibrary.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = UserAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        val itemViewModel = ViewModelProvider(this)
            .get(UserViewModel::class.java)
        itemViewModel.userPagedList.observe(this, Observer {
            adapter.submitList(it)
        })
        recyclerView.adapter = adapter
    }

}

