package com.msalcedo.dinnews.screen.detail.mvvm

import com.msalcedo.dinnews.common.mvp.MVPContract

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/11/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */

class DetailContract {

    interface View : MVPContract.View<DetailViewModel>

    interface ViewModel: MVPContract.ViewModel
}