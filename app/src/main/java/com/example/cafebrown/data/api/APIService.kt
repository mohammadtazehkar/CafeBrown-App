package com.example.cafebrown.data.api

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.menu.APIGetMenuResponse
import com.example.cafebrown.data.models.product.APIGetSubMenuAndProductResponse
import com.example.cafebrown.data.models.profile.APIUpdateProfileRequest
import com.example.cafebrown.data.models.verify.APIPostVerificationCodeRequest
import com.example.cafebrown.data.models.verify.APIPostVerificationCodeResponse
import com.example.cafebrown.utils.ServerConstants.AUTHORIZATION
import com.example.cafebrown.utils.ServerConstants.MENU_ID
import com.example.cafebrown.utils.ServerConstants.SUB_URL_GET_MENU
import com.example.cafebrown.utils.ServerConstants.SUB_URL_GET_SUB_MENU_AND_PRODUCT
import com.example.cafebrown.utils.ServerConstants.SUB_URL_POST_VERIFICATION
import com.example.cafebrown.utils.ServerConstants.SUB_URL_PUT_UPDATE_PROFILE
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query
import retrofit2.http.Url

interface APIService {

    @POST
    suspend fun postMobileNumber(@Url url: String): Response<APIGlobalResponse>

    @POST(SUB_URL_POST_VERIFICATION)
    suspend fun postVerificationCode(@Body apiPostVerificationCodeRequest: APIPostVerificationCodeRequest): Response<APIPostVerificationCodeResponse>

    @PUT(SUB_URL_PUT_UPDATE_PROFILE)
    suspend fun updateProfile(
        @Header(AUTHORIZATION) token: String,
        @Body apiPutUpdateProfileRequest: APIUpdateProfileRequest
    ): Response<APIGlobalResponse>


    @GET(SUB_URL_GET_MENU)
    suspend fun getMenuList(@Header(AUTHORIZATION) token: String): Response<APIGetMenuResponse>

    @GET(SUB_URL_GET_SUB_MENU_AND_PRODUCT)
    suspend fun getSubMenuAndProductList(
        @Header(AUTHORIZATION) token: String,
        @Query(MENU_ID) menuId: Int
    ): Response<APIGetSubMenuAndProductResponse>
}