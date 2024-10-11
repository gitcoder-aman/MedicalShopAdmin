package com.tech.mymedicalstoreadminapp.data.services


import com.tech.mymedicalstoreadminapp.data.response.signupLogin.MessageStatusResponse
import com.tech.mymedicalstoreadminapp.data.response.user.GetAllUsersResponseItem
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ApiServices {
    @GET("getAllUsers")
    suspend fun getAllUsers(): Response<ArrayList<GetAllUsersResponseItem>>

    @FormUrlEncoded
    @PATCH("updateUser")
    suspend fun updateApproveStatus(
        @Field("userId") userId : String,
        @Field("isApproved") isApproved : Int
    ):Response<MessageStatusResponse>

    @FormUrlEncoded
    @POST("addProduct")
    suspend fun addProduct(
        @Field("product_name") productName : String,
        @Field("product_category") productCategory : String,
        @Field("product_price") productPrice : Int,
        @Field("product_stock") productStock : Int,
        @Field("product_expiry_date") productExpiryDate : String,
        @Field("product_rating") productRating : Float,
        @Field("product_description") productDescription : String,
        @Field("product_image") productImage : String,
        @Field("product_power") productPower : String,
    ):Response<MessageStatusResponse>

}