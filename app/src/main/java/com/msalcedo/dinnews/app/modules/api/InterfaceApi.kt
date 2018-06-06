package com.msalcedo.dinnews.app.modules.api

import com.msalcedo.dinnews.models.Response
import com.msalcedo.dinnews.utils.Constant
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
interface InterfaceApi {

    @GET(Constant.Url.Api.topHeadlines)
    fun topHeadlines(): Single<List<Response>>

    @GET(Constant.Url.Api.everything)
    fun everything(): Single<List<Response>>

}