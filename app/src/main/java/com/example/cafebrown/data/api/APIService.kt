package com.example.cafebrown.data.api

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Url

interface APIService {
//    @FormUrlEncoded
//    @POST(SUB_URL_REGISTER)
//    suspend fun signUp(
//        @Field(FIRST_NAME) firstName: String,
//        @Field(LAST_NAME) lastName: String,
//        @Field(PHONE_NUMBER) phoneNumber: String,
//        @Field(MOBILE_NUMBER) mobileNumber: String,
//        @Field(USERNAME) username: String,
//        @Field(PASSWORD) password: String,
//        @Field(EMAIL) email: String,
//        @Field(REAGENT_TOKEN) reagentToken: String
//    ): Response<APISignUpResponse>
//
//    @GET(SUB_URL_PROFILE)
//    suspend fun profile(
//        @Header(AUTHORIZATION) token: String,
//    ): Response<APIProfileResponse>
//
//    @FormUrlEncoded
//    @POST(SUB_URL_ADD_ADDRESS)
//    suspend fun addAddress(
//        @Header(AUTHORIZATION) token: String,
//        @Field(TITLE) title: String,
//        @Field(ADDRESS) address: String,
//        @Field(MAP_POINT) mapPoint: String
//    ): Response<APIAddAddressResponse>
//
//    @GET
//    suspend fun getMessagesList(
//        @Url url: String,
//    ): Response<APIMessageListResponse>
}