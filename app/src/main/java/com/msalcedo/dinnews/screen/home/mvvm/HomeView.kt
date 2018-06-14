package com.msalcedo.dinnews.screen.home.mvvm

import android.annotation.SuppressLint
import com.msalcedo.dinnews.common.RxActivity
import com.msalcedo.dinnews.common.mvp.MVVMView

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/7/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@SuppressLint("ViewConstructor")
class HomeView(override val activity: RxActivity, override var viewModel: HomeViewModel): MVVMView<HomeViewModel>(activity), HomeContract.View {
}