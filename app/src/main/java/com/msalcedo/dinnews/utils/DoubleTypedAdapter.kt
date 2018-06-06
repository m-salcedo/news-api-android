package com.msalcedo.dinnews.utils

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
open class DoubleTypedAdapter : JsonSerializer<Double> {

    override fun serialize(src: Double?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement? {
        return if (src == null || src == -1.0) null else JsonPrimitive(src)

    }
}