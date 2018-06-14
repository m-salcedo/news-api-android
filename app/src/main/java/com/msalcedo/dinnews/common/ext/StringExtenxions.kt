package com.msalcedo.dinnews.common.ext

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */

fun String?.empty() = this == null || this.trim().isEmpty()
fun CharSequence?.empty() = this == null || this.trim().isEmpty()