package com.msalcedo.dinnews.screen.filter.mvvm

import com.msalcedo.dinnews.common.mvp.MVPContract

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class FilterContract {

    interface View : MVPContract.View<FilterViewModel>

    interface ViewModel: MVPContract.ViewModel
}