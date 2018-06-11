package com.msalcedo.dinnews.screen.home.mvvm

import com.msalcedo.dinnews.common.mvp.MVPContract

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/7/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */

class HomeContract {

    interface View : MVPContract.View<HomeViewModel>

    interface ViewModel: MVPContract.ViewModel
}