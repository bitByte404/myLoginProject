package com.example.loginproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    //不希望界面旋转或跳转而丢失的数据数据
    //长时间保存的数据
    var number: MutableLiveData<Int> = MutableLiveData(0)

    fun addOne() {
        //取出对应的数据进行计算操作
        val result = number.value!! + 1
        //将新的数据写入LineData对象中
        number.postValue(result)
    }

    fun getNumber() = number.value!!

}