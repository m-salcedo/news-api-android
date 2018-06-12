package com.msalcedo.dinnews.app.modules.api

import com.msalcedo.dinnews.models.ResponseArticle
import com.msalcedo.dinnews.models.ResponseSource
import com.msalcedo.dinnews.utils.Constant
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
interface InterfaceApi {

    @GET(Constant.Url.Api.topHeadlines)
    fun topHeadlines(@Query("page") page: Int,
                     @Query("sources") sources: String?,
                     @Query("category") category: String?,
                     @Query("country") country: String?,
                     @Query("language") language: String?,
                     @Query("q") q: String?,
                     @Query("sortBy") sortBy: String?)
            : Single<ResponseArticle>

    @GET(Constant.Url.Api.everything)
    fun everything(@Query("page") page: Int,
                   @Query("sources") sources: String?,
                   @Query("language") language: String?,
                   @Query("from") from: String?,
                   @Query("to") to: String?,
                   @Query("q") q: String?,
                   @Query("sortBy") sortBy: String?)
            : Single<ResponseArticle>

    @GET(Constant.Url.Api.sources)
    fun sources(): Single<ResponseSource>

}