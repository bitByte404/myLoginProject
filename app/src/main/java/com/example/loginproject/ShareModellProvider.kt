package com.example.loginproject

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

object ShareModellProvider {
    private var vm: MainViewModel? = null
    private var mdefaultOwner: ViewModelStoreOwner? = null
    fun get(owner: ViewModelStoreOwner): MainViewModel {
        if (vm == null) {
            vm = ViewModelProvider(owner)[MainViewModel::class.java]
        }
            return vm!!
    }
}

