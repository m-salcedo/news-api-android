package com.msalcedo.dinnews.screen.filterlist.mvvm

import android.annotation.SuppressLint
import com.msalcedo.dinnews.common.RxActivity
import com.msalcedo.dinnews.common.mvp.MVVMView

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/12/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */

@SuppressLint("ViewConstructor")
class FilterListView(override val activity: RxActivity, override var viewModel: FilterListViewModel): MVVMView<FilterListViewModel>(activity), FilterListContract.View {
}