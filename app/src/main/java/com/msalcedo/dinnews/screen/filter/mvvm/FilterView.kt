package com.msalcedo.dinnews.screen.filter.mvvm

import android.annotation.SuppressLint
import com.msalcedo.dinnews.common.RxActivity
import com.msalcedo.dinnews.common.mvp.MVVMView

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@SuppressLint("ViewConstructor")
class FilterView(override val activity: RxActivity, override var viewModel: FilterViewModel): MVVMView<FilterViewModel>(activity), FilterContract.View {
}