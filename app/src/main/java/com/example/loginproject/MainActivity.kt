package com.example.loginproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.loginproject.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.io.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //private var viewModel = MainViewModel() //不能这样创建  没有建立生命周期关系
    private lateinit var mViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mViewModel = ShareModellProvider.get(this)

        //观察数据变化
        mViewModel.number.observe(this) {
            binding.result.text = "$it"
        }

        /**
        //取出保存的值显示到界面
        if (savedInstanceState != null) {
        number = savedInstanceState.getInt("numberKey")
        binding.result.text = "$number"
        }binding.button1.setOnClickListener {
        number++
        binding.result.text = "$number"
        }



         */

        binding.button1.setOnClickListener {
            mViewModel.addOne()
        }

        binding.button2.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    /**
    override fun onSaveInstanceState(outState: Bundle) {
    outState.putInt("numberKey", number)
    super.onSaveInstanceState(outState)
    }
     */

    fun objectOperation() {
        val user = User("jack", "123", true)
        val path = "${filesDir.path}/userInfo"
        val gson = Gson()

        //将对象转化为JSON格式字符串
        /**
        val jsonString = gson.toJson(user)
        FileWriter(path).use {
        it.write(jsonString)
        }
         */
        //读取JSON格式字符串
        FileReader(path).use {
            val jsonString = it.readText()
            //将json格式的字符串转化为具体的对象
            val u = gson.fromJson(jsonString, User::class.java)
            Log.v("pxd", "user: $u")
        }
    }

    fun userStringOperation() {
        val user = User("jack", "123", true)

        val str = "${user.name}-${user.password}-${user.isLogin}"

        val path = "${filesDir.path}/userInfo"
//        FileWriter(path).use {
//            it.write(str)
//        }
        FileReader(path).use {
            it.readLines().forEach { line ->
                line.split("-").also { infolist ->
                    User(infolist[0], infolist[1], infolist[2].toBoolean()).also { user ->
                        Log.v("pxd", "$user")
                    }
                }
            }
        }
    }

    fun bufferReadVideo() {
        val path = "${cacheDir.path}/test.mp4"
        val desPath = "${filesDir.path}/test.mp4"

        val start = System.currentTimeMillis()
        //输入流  电脑硬盘 -> 内存
        BufferedInputStream(FileInputStream(path)).use { bis ->
            //输出流  内存 -> 手机的硬盘
            BufferedOutputStream(FileOutputStream(desPath)).use { bos ->
                val buffer = ByteArray(1024)
                var len = 0
                len = bis.read(buffer, 0, 1024)
                while (len != -1) {
                    bos.write(buffer, 0, len)
                    len = bis.read(buffer, 0, 1024)
                }
                val end = System.currentTimeMillis()
                Log.v("pxd", "time: ${end - start}")
            }
        }

    }

    fun readVideo() {
        val path = "${cacheDir.path}/test.mp4"
        val desPath = "${filesDir.path}/test.mp4"

        val start = System.currentTimeMillis()
        //输入流  电脑硬盘 -> 内存
        FileInputStream(path).use { fis ->
            //输出流  内存 -> 手机的硬盘
            FileOutputStream(desPath).use { fos ->
                val buffer = ByteArray(1024)
                var len = 0
                len = fis.read(buffer, 0, 1024)
                while (len != -1) {
                    fos.write(buffer, 0, len)
                    len = fis.read(buffer, 0, 1024)
                }
                val end = System.currentTimeMillis()
                Log.v("pxd", "time: ${end - start}")
            }
        }

    }

    fun stringOperation() {
        val path = "${filesDir.path}/小说.txt"
        val file = File(path)
        if (!file.exists()) {
            file.createNewFile()
        }

        val buffer = CharArray(100)
        var len = 0
        FileWriter("${cacheDir.path}/小说.txt").use { fileWriter ->
            FileReader(path).use { fileReader ->
                len = fileReader.read(buffer, 0, 100)
                while (len != -1) {
                    fileWriter.write(buffer, 0, len)
                    len = fileReader.read(buffer, 0, 100)
                }
            }
        }
    }

    fun byteOperation() {
        val srcPath = "${filesDir.path}/dot_error.png"
        val fis = FileInputStream(srcPath)

        val desPath = "${cacheDir.path}/dot_error.png"
        val fos = FileOutputStream(desPath)

        val buffer = ByteArray(1024) //一次读取1024个字节
        var len = 0 //记录每次读取的长度
        //读取一次
        len = fis.read(buffer, 0, 1024)
        //判断是否读取结束
        while (len != -1) {
            //将读取的内容写入到输出流里面
            fos.write(buffer, 0, len)
            //下一次读取
            len = fis.read(buffer, 0, 1024)
        }

        fos.flush()//冲刷一下输出流
        fis.close()
        fos.close()
    }
}