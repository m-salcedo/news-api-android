package com.msalcedo.dinnews.screen.filterlist.mvvm

import com.msalcedo.dinnews.common.mvp.MVPContract

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/12/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class FilterListContract {

    interface View : MVPContract.View<FilterListViewModel>

    interface ViewModel: MVPContract.ViewModel
}