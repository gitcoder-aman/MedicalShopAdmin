package com.tech.mymedicalstoreadminapp.data.services


import com.tech.mymedicalstoreadminapp.data.response.order.MedicalOrderResponseItem
import com.tech.mymedicalstoreadminapp.data.response.product.ProductModelItem
import com.tech.mymedicalstoreadminapp.data.response.signupLogin.MessageStatusResponse
import com.tech.mymedicalstoreadminapp.data.response.user.GetAllUsersResponseItem
import com.tech.mymedicalstoreadminapp.data.response.user.SellHistoryResponseItem
import com.tech.mymedicalstoreadminapp.data.response.user.UserStockResponseItem
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

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
    @PATCH("updateUser")
    suspend fun updateBlockStatus(
        @Field("userId") userId : String,
        @Field("block") isBlock : Int
    ):Response<MessageStatusResponse>

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
    @POST("getSpecificOrder")
    suspend fun getSpecificOrder(
        @Field("order_id") orderId : String
    ):Response<ArrayList<MedicalOrderResponseItem>>

    @FormUrlEncoded
    @PATCH("updateOrder")
    suspend fun doApprovedOrder(
        @Field("orderId") orderId : String,
        @Field("isApproved") isApprovedOrder : Int,
        @Field("order_status") orderStatusStep : String
    ) : Response<MessageStatusResponse>

    @FormUrlEncoded
    @PATCH("updateOrder")
    suspend fun updateShippedOrder(
        @Field("orderId") orderId : String,
        @Field("shipped_date") shippedDate : String,
        @Field("order_status") orderStatusStep : String
    ) : Response<MessageStatusResponse>

    @FormUrlEncoded
    @PATCH("updateOrder")
    suspend fun updateOutOfDeliveryOrder(
        @Field("orderId") orderId : String,
        @Field("out_of_delivery_date") outOfDeliveryDate : String,
        @Field("order_status") orderStatusStep : String
    ) : Response<MessageStatusResponse>

    @FormUrlEncoded
    @PATCH("updateOrder")
    suspend fun updateDeliveredOrder(
        @Field("orderId") orderId : String,
        @Field("delivered_date") deliveredDate : String,
        @Field("order_status") orderStatusStep : String
    ) : Response<MessageStatusResponse>

    @FormUrlEncoded
    @PATCH("updateOrder")
    suspend fun updateCancelledOrder(
        @Field("orderId") orderId : String,
        @Field("order_cancel_status") orderCancelStatus : String,
        @Field("order_status") orderStatusStep : String
    ) : Response<MessageStatusResponse>


    @GET("getAllProduct")
    suspend fun getAllProduct() : Response<ArrayList<ProductModelItem>>


    @FormUrlEncoded
    @POST("getSpecificProduct")
    suspend fun getSpecificProduct(
        @Field("productId") productId : String
    ) : Response<ArrayList<ProductModelItem>>

    @FormUrlEncoded
    @PATCH("updateProduct")
    suspend fun updateStockProduct(
        @Field("productId") productId: String,
        @Field("product_stock") productStock: Int
    ): Response<MessageStatusResponse>

    @FormUrlEncoded
    @POST("stock")
    suspend fun addOrderInUserStock(
        @Field("user_id") userId: String,
        @Field("order_id") orderId: String,
        @Field("product_id") productId: String,
        @Field("product_name") productName: String,
        @Field("user_name") userName: String,
        @Field("certified") certified: Boolean,
        @Field("stock") productStock: Int,
        @Field("price") productPrice: Int,
        @Field("product_category") productCategory: String
    ) : Response<MessageStatusResponse>

    @Multipart
    @PATCH("updateProduct")
    suspend fun updateProduct(
        @Part("productId") productId: RequestBody,
        @Part("product_name") productName: RequestBody,
        @Part("product_category") productCategory: RequestBody,
        @Part("product_price") productPrice: RequestBody,
        @Part("product_stock") productStock: RequestBody,
        @Part("product_expiry_date") productExpiryDate: RequestBody,
        @Part("product_rating") productRating: RequestBody,
        @Part("product_description") productDescription: RequestBody,
        @Part("product_power") productPower: RequestBody,
        @Part pic: MultipartBody.Part
    ):Response<MessageStatusResponse>

    @DELETE("deleteProduct")
    suspend fun deleteProduct(
        @Query("productId") productId: String
    ): Response<MessageStatusResponse>

    @DELETE("deleteUser")
    suspend fun deleteUser(
        @Query("userId") userId: String
    ): Response<MessageStatusResponse>

    @FormUrlEncoded
    @POST("sell_history")
    suspend fun addSellHistory(
        @Field("user_id") userId: String,
        @Field("product_id") productId: String,
        @Field("quantity") quantity: String,
        @Field("remaining_stock") remainingStock: String,
        @Field("date_of_sell") dateOfSell: String,
        @Field("total_amount") totalAmount: String,
        @Field("price") productPrice: String,
        @Field("product_name") productName: String,
        @Field("product_category") productCategory: String,
        @Field("user_name") userName: String,
    ): Response<MessageStatusResponse>

    @GET("getAllSellHistory")
    suspend fun getAllSellHistory(): Response<ArrayList<SellHistoryResponseItem>>

    @GET("getAllStock")
    suspend fun getAllUserStock(): Response<ArrayList<UserStockResponseItem>>





}