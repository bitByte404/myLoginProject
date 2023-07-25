package com.example.loginproject

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore

object SharedViewModelHolder {
    private var sharedViewModel: MainViewModel? = null
    private val viewModelStore = ViewModelStore()

    fun getSharedViewModel(activity: AppCompatActivity): MainViewModel {
        if (sharedViewModel == null) {
            sharedViewModel = ViewModelProvider(viewModelStore, ViewModelProvider.AndroidViewModelFactory.getInstance(activity.application)).get(MainViewModel::class.java)
        }
        return sharedViewModel as MainViewModel
    }
}