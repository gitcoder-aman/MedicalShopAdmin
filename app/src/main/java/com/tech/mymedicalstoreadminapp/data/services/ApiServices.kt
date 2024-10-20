package com.tech.mymedicalstoreadminapp.data.services


import com.tech.mymedicalstoreadminapp.data.response.order.MedicalOrderResponseItem
import com.tech.mymedicalstoreadminapp.data.response.signupLogin.MessageStatusResponse
import com.tech.mymedicalstoreadminapp.data.response.user.GetAllUsersResponseItem
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiServices {
    @GET("getAllUsers")
    suspend fun getAllUsers(): Response<ArrayList<GetAllUsersResponseItem>>

    @FormUrlEncoded
    @PATCH("updateUser")
    suspend fun updateApproveStatus(
        @Field("userId") userId : String,
        @Field("isApproved") isApproved : Int
    ):Response<MessageStatusResponse>

//    @FormUrlEncoded
//    @Multipart
//    @POST("addProduct")
//    suspend fun addProduct(
//        @Field("product_name") productName : String,
//        @Field("product_category") productCategory : String,
//        @Field("product_price") productPrice : Int,
//        @Field("product_stock") productStock : Int,
//        @Field("product_expiry_date") productExpiryDate : String,
//        @Field("product_rating") productRating : Float,
//        @Field("product_description") productDescription : String,
//        @Part pic: MultipartBody.Part,
//        @Field("product_power") productPower : String,
//    ):Response<MessageStatusResponse>

    @Multipart
    @POST("addProduct")
    suspend fun addProduct(
        @Part("product_name") productName: RequestBody,
        @Part("product_category") productCategory: RequestBody,
        @Part("product_price") productPrice: RequestBody,
        @Part("product_stock") productStock: RequestBody,
        @Part("product_expiry_date") productExpiryDate: RequestBody,
        @Part("product_rating") productRating: RequestBody,
        @Part("product_description") productDescription: RequestBody,
        @Part pic: MultipartBody.Part,
        @Part("product_power") productPower: RequestBody
    ): Response<MessageStatusResponse>

    @GET("getAllOrders")
    suspend fun getAllOrders() : Response<ArrayList<MedicalOrderResponseItem>>

    @FormUrlEncoded
    @PATCH("updateOrder")
    suspend fun doApprovedOrder(
        @Field("orderId") orderId : String,
        @Field("isApproved") isApprovedOrder : Int
    ) : Response<MessageStatusResponse>

}