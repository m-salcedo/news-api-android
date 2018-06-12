package com.msalcedo.dinnews.utils

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/12/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class DisableableAppBarLayoutBehavior : AppBarLayout.Behavior {
    var isEnabled: Boolean = false

    constructor() : super() {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun onStartNestedScroll(parent: CoordinatorLayout, child: AppBarLayout, directTargetChild: View, target: View, nestedScrollAxes: Int): Boolean {
        return isEnabled && super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes)
    }
}