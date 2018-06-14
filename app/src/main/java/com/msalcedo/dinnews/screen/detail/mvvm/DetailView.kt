package com.msalcedo.dinnews.screen.detail.mvvm

import android.annotation.SuppressLint
import com.msalcedo.dinnews.common.RxActivity
import com.msalcedo.dinnews.common.mvp.MVVMView

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/11/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@SuppressLint("ViewConstructor")
class DetailView(override val activity: RxActivity, override var viewModel: DetailViewModel): MVVMView<DetailViewModel>(activity), DetailContract.View {
}