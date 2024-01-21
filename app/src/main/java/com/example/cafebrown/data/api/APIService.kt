package com.example.cafebrown.data.api

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.profile.APIUpdateProfileRequest
import com.example.cafebrown.data.models.verify.APIPostVerificationCodeRequest
import com.example.cafebrown.data.models.verify.APIPostVerificationCodeResponse
import com.example.cafebrown.utils.ServerConstants.AUTHORIZATION
import com.example.cafebrown.utils.ServerConstants.SUB_URL_POST_VERIFICATION
import com.example.cafebrown.utils.ServerConstants.SUB_URL_PUT_UPDATE_PROFILE
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Url

interface APIService {

    @POST
    suspend fun postMobileNumber(@Url url:String): Response<APIGlobalResponse>

    @POST(SUB_URL_POST_VERIFICATION)
    suspend fun postVerificationCode(@Body apiPostVerificationCodeRequest: APIPostVerificationCodeRequest): Response<APIPostVerificationCodeResponse>

    @PUT(SUB_URL_PUT_UPDATE_PROFILE)
//    @Headers({"Content-Type: application/json"})
    suspend fun updateProfile(@Header(AUTHORIZATION) token: String, @Body apiPutUpdateProfileRequest: APIUpdateProfileRequest):Response<APIGlobalResponse>
}