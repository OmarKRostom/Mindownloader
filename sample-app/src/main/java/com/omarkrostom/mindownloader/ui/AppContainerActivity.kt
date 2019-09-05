package com.omarkrostom.mindownloader.ui

import android.os.Bundle
import com.omarkrostom.mindownloader.R
import com.omarkrostom.mindownloader.architecture.BaseNavActivity
import dagger.android.AndroidInjection

class AppContainerActivity: BaseNavActivity() {
    override fun getLayoutId(): Int = R.layout.activity_container_app
}