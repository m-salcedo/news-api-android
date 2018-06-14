package com.msalcedo.dinnews.utils

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
open class CollectionTypedAdapter : JsonSerializer<Collection<*>> {

    override fun serialize(src: Collection<*>?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement? {
        if (src == null)
        // exclusion is made here
            return null

        val array = JsonArray()

        for (child in src) {
            val element = context.serialize(child)
            array.add(element)
        }

        return array
    }
}
