package com.msalcedo.dinnews.utils

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */

interface Constant {

    interface Location {
        companion object {
            val INTERVAL: Long = 60000
        }
    }

    interface Key {
        companion object {
            val ACCESS_TOKEN = "access_token"
            val AUTHORIZATION = "Authorization"
            val JWT = "JWT "
            val BEARER = "Bearer "

            val ID = "id"
            val ID_STR = "id_str"
            val NAME = "name"

            val USERS = "users"
            val AUTH = "auth"
        }
    }

    interface Preferences {
        companion object {
            val APP = "baseAppPreferences"
            val SESSION = "baseAppSessionPreferences"
            val CACHE = "okhttp_cache"
        }
    }

    interface Network {
        interface Status {
            companion object {
                val SUCCESS = 200
                val CREATED = 201
                val BAD_REQUEST = 400
                val UNAUTHORIZED = 401
                val FORBIDDEN = 403
                val NOT_FOUND = 404
                val CONFLICT = 409
            }
        }
    }

    interface Url {

        interface Api {
            companion object {
                const val version = Character.BAR + "v2" + Character.BAR
                const val everything = version + "everything"
                const val topHeadlines = version + "top-headlines"
            }
        }

        interface Character {
            companion object {
                const val BAR = "/"
                const val PARAMETER_OPEN = "{"
                const val PARAMETER_CLOSE = "}"
                const val QUERY = "?"
                const val EQUAL = "="
                const val OTHER_QUERY = "&"
            }
        }
    }
}
