package com.msalcedo.dinnews.screen.news.mvvm

import android.annotation.SuppressLint
import com.msalcedo.dinnews.common.RxActivity
import com.msalcedo.dinnews.common.mvp.MVVMView

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@SuppressLint("ViewConstructor")
class NewsView(override val activity: RxActivity, override var viewModel: NewsViewModel): MVVMView<NewsViewModel>(activity), NewsContract.View {
}