package com.vaccae.nanomsg.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.vaccae.nanomsg.R
import com.vaccae.nanomsg.databinding.ActivityPipepushBinding
import com.vaccae.nanomsg.bean.VaccaeShare
import com.vaccae.nanomsg.mvvm.viewmodel.PipePushViewModel

class PipePushActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityPipepushBinding
    lateinit var mViewMode: PipePushViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil
            .setContentView<ActivityPipepushBinding>(
                this,
                R.layout.activity_pipepush
            )

        mViewMode = PipePushViewModel()

        mBinding.vmpush = mViewMode
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewMode.threadrun = false
        System.gc()
    }
}
