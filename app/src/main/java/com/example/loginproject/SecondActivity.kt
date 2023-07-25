package com.example.loginproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.loginproject.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySecondBinding.inflate(layoutInflater)
        viewModel = ShareModellProvider.get(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.textView.text = viewModel.number.toString()

        //观察数据变化
        viewModel.number.observe(this) {
            binding.textView.text = it.toString()
        }

        binding.addOne.setOnClickListener {
            viewModel.addOne()
        }
    }


}