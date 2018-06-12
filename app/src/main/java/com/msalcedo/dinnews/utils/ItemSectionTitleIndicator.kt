package com.msalcedo.dinnews.utils

import android.content.Context
import android.util.AttributeSet
import xyz.danoz.recyclerviewfastscroller.sectionindicator.title.SectionTitleIndicator

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/12/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class ItemSectionTitleIndicator : SectionTitleIndicator<String> {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun setSection(item: String) {
        setTitleText(item)
    }

}