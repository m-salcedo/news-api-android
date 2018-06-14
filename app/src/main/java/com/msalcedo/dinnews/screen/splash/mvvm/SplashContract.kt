package com.msalcedo.dinnews.screen.splash.mvvm

import com.msalcedo.dinnews.common.mvp.MVPContract

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/7/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class SplashContract {

    interface View : MVPContract.View<SplashViewModel>

    interface ViewModel: MVPContract.ViewModel
}