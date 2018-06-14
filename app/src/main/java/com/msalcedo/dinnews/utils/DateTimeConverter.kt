package com.msalcedo.dinnews.utils

import com.google.gson.*
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import java.lang.reflect.Type

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class DateTimeConverter : JsonSerializer<DateTime>, JsonDeserializer<DateTime> {

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------


    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Methods
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    override fun serialize(src: DateTime, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        val fmt = ISODateTimeFormat.dateTimeParser()
        return JsonPrimitive(fmt.print(src))
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): DateTime? {
        // Do not try to deserialize null or empty values
        if (json.asString == null || json.asString.isEmpty()) {
            return null
        }

        val fmt = ISODateTimeFormat.dateTimeParser()
        return fmt.parseDateTime(json.asString)
    }

    companion object {

        // ---------------------------------------------------------------------------------------------
        // ---------------------------------------------------------------------------------------------
        // Constants
        // ---------------------------------------------------------------------------------------------
        // ---------------------------------------------------------------------------------------------

        private val TAG = DateTimeConverter::class.java.simpleName
    }
}