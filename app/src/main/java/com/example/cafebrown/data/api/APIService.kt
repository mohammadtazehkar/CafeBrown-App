package com.example.cafebrown.data.api

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.desk.APIGetDeskResponse
import com.example.cafebrown.data.models.home.APIGetHomeDataResponse
import com.example.cafebrown.data.models.profile.APIUpdateProfileRequest
import com.example.cafebrown.data.models.reserve.APIGetReserveBaseInfoResponse
import com.example.cafebrown.data.models.verify.APIPostVerificationCodeRequest
import com.example.cafebrown.data.models.verify.APIPostVerificationCodeResponse
import com.example.cafebrown.utils.ServerConstants.AUTHORIZATION
import com.example.cafebrown.utils.ServerConstants.SUB_URL_GET_COFFEE_SHOP_DATA
import com.example.cafebrown.utils.ServerConstants.SUB_URL_GET_RESERVE_TIMES
import com.example.cafebrown.utils.ServerConstants.SUB_URL_GET_TABLES
import com.example.cafebrown.utils.ServerConstants.SUB_URL_POST_VERIFICATION
import com.example.cafebrown.utils.ServerConstants.SUB_URL_PUT_UPDATE_PROFILE
import com.example.cafebrown.utils.ServerConstants.TABLE_ID
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
    suspend fun postMobileNumber(@Url url:String): Response<APIGlobalResponse>

    @POST(SUB_URL_POST_VERIFICATION)
    suspend fun postVerificationCode(@Body apiPostVerificationCodeRequest: APIPostVerificationCodeRequest): Response<APIPostVerificationCodeResponse>

    @PUT(SUB_URL_PUT_UPDATE_PROFILE)
//    @Headers({"Content-Type: application/json"})
    suspend fun updateProfile(@Header(AUTHORIZATION) token: String, @Body apiPutUpdateProfileRequest: APIUpdateProfileRequest):Response<APIGlobalResponse>

    @GET(SUB_URL_GET_COFFEE_SHOP_DATA)
    suspend fun getHomeData(@Header(AUTHORIZATION) token: String): Response<APIGetHomeDataResponse>

    @GET(SUB_URL_GET_TABLES)
    suspend fun getDeskList(@Header(AUTHORIZATION) token: String): Response<APIGetDeskResponse>

    @GET(SUB_URL_GET_RESERVE_TIMES)
    suspend fun getReserveTimes(@Header(AUTHORIZATION) token: String, @Query(TABLE_ID) tableId: Int): Response<APIGetReserveBaseInfoResponse>
}