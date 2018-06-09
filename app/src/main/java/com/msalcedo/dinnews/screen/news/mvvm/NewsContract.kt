package com.msalcedo.dinnews.screen.news.mvvm

import com.msalcedo.dinnews.common.mvp.MVPContract

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class NewsContract {

    interface View : MVPContract.View<NewsViewModel>

    interface ViewModel: MVPContract.ViewModel
}