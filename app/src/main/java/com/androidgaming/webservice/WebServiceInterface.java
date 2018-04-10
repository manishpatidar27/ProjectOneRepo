package com.androidgaming.webservice;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by HP on 03-05-2017.
 */

public interface WebServiceInterface {


    @POST("admin/api/login")
    Call<ResponseBody> login(@Body Map<String,String> requestMap);


    @GET("admin/api/check/{api_token}")
    Call<ResponseBody> checkSessionLogin(@Path("api_token") String api_token);


    @GET("admin/api/logout/{api_token}")
    Call<ResponseBody> logout(@Path("api_token") String api_token);


    @GET("admin/api/dashboard")
    Call<ResponseBody> dashBoard(@Query("api_token") String api_token);


    @GET("admin/api/location")
    Call<ResponseBody> location(@Query("api_token") String api_token);


    @GET("admin/api/segments")
    Call<ResponseBody> segments(@Query("api_token") String api_token);



    @GET("admin/api/areas")
    Call<ResponseBody> areas(@Query("api_token") String api_token);

    @GET("api/categories/main")
    Call<ResponseBody> categories(@Query("api_token") String api_token);

    @POST("admin/api/categories/subcat/{category_id}")
    Call<ResponseBody> subCategory(@Path("category_id") String category_id, @Query("api_token") String api_token);




    @POST("api/feedback/audio")
    Call<ResponseBody> feedbackAudio(@Query("api_token") String api_token);



    @POST("api/feedback/video")
    Call<ResponseBody> feedbackVideo(@Query("api_token") String api_token);



    @POST("api/feedback/image")
    Call<ResponseBody> feedbackImage(@Query("api_token") String api_token);



    @POST("api/feedback")
    Call<ResponseBody> feeback(@Query("api_token") String api_token);


































/*
    @POST("directions/json")
    Call<ResponseBody> getDirectionsUrl(@Query("origin") String origin,
                                        @Query("key") String key,
                                        @Query("destination") String destination,
                                        @Query("sensor") String sensor,
                                        @Query("mode") String mode);

    *//*APIS RELATED TO AMREK*//*
    @GET("account/check")
    Call<ResponseBody> checkAccount(@Header("language") String language,
                                    @Query("mobile") String mobileNumber,
                                    @Query("role") String roleOfUser);


    @GET("user/check-password")
    Call<ResponseBody> checkPassword(@Header("language") String language,
                                     @Header("access-token") String accessToken,
                                     @Query("password") String passwordEntered);


    @POST("user/send-otp")
    Call<ResponseBody> regGenerateOTP(@Header("language") String language,
                                      @Body HashMap mobileNumber);


    @Multipart
    @POST("account/signup/mover")
    Call<ResponseBody> signUpOfDriver(@Header("language") String language,
                                      @PartMap Map<String, RequestBody> allParameters);

    @POST("account/login")
    Call<ResponseBody> driverLogin(@Header("language") String language,
                                   @Body HashMap driverLoginHashMap);



    *//*-----------------------------HAVING ERROR IN ARRAY--------------------------------------------*//*

    @Multipart
    @POST("user/update")
    Call<ResponseBody> driverUpdateProfile(@Header("language") String language,
                                           @Header("access-token") String accessToken,
                                           @PartMap Map<String, RequestBody> allParameters);

    @GET("user/profile")
    Call<ResponseBody> driverProfile(@Header("language") String language,
                                     @Header("access-token") String accessToken,
                                     @Query("user_id") String user_id);

    @POST("user/logout")
    Call<ResponseBody> driverLogout(@Header("language") String language,
                                    @Header("access-token") String accessToken);

    @POST("account/forgot")
    Call<ResponseBody> driverForgotPasswordGenerateOTP(@Header("language") String language,
                                                       @Body HashMap dataToPost);

    @POST("account/reset-password")
    Call<ResponseBody> driverResetPassword(@Header("language") String language,
                                           @Body HashMap resetPassword);

    @POST("account/reset-password")
    Call<ResponseBody> driverResetPasswordByMobile(@Header("language") String language,
                                                   @Body HashMap resetPassword);


    @POST("account/check-email")
    Call<ResponseBody> checkEmailPlusNumber(@Header("language") String language,
                                            @Query("email") String email,
                                            @Query("mobile") String mobile,
                                            @Query("role") String role);

    @POST("mover/update-location")
    Call<ResponseBody> driverUpdateLocation(@Header("language") String language,
                                            @Header("access-token") String accessToken,
                                            @Body HashMap dataToPost);

    @POST("mover/update-status")
    Call<ResponseBody> driverUpdateStatus(@Header("language") String language,
                                          @Header("access-token") String accessToken,
                                          @Body HashMap dataToPost);

    @POST("mover/accept-order")
    Call<ResponseBody> driverAcceptPackageRequest(@Header("language") String language,
                                                  @Header("access-token") String accessToken,
                                                  @Query("order_id") String order_id);

    @GET("user/cancel-reason")
    Call<ResponseBody> driverGetCancellationReason(@Header("language") String language,
                                                   @Header("access-token") String accessToken,
                                                   @Query("role") String role);


    @POST("user/cancel-delivery")
    Call<ResponseBody> driverCancelDelivery(@Header("language") String language,
                                            @Header("access-token") String accessToken,
                                            @Body HashMap dataToPost);

    @GET("master/contact-reason-list")
    Call<ResponseBody> driverReasonForContactAdmin(@Header("language") String language,
                                                   @Header("access-token") String accessToken,
                                                   @Query("role") String role);

    @POST("master/contact-us")
    Call<ResponseBody> driverPostToContactAdmin(@Header("language") String language,
                                                @Header("access-token") String accessToken,
                                                @Body HashMap dataToPost);

    @GET("master/ratting-review-message")
    Call<ResponseBody> driverReasonToRateCustomer(@Header("language") String language,
                                                  @Header("access-token") String accessToken,
                                                  @Query("role") String role);

    @POST("user/ratting")
    Call<ResponseBody> driverRateCustomerAfterDelivery(@Header("language") String language,
                                                       @Header("access-token") String accessToken,
                                                       @QueryMap Map<String, String> allParams);


    @POST("mover/cash-collected")
    Call<ResponseBody> driverCashCollected(@Header("language") String language,
                                           @Header("access-token") String accessToken,
                                           @Query("order_id") String order_id);

    @GET("order/delivery/{ORDER_ID}")
    Call<ResponseBody> deliveryDetails(@Header("language") String language,
                                       @Header("access-token") String accessToken,
                                       @Path("ORDER_ID") String orderID);

    @GET("order/all-deliveries")
    Call<ResponseBody> getAllDeliveries(@Header("language") String language,
                                        @Header("access-token") String accessToken,
                                        @Query("time_zone") String time_zone,
                                        @Query("role") String role,
                                        @Query("page") String page);



    @Multipart
    @POST("order/update-progress")
    Call<ResponseBody> driverUpdateProgress(@Header("language") String language,
                                            @Header("access-token") String accessToken,
                                            @PartMap Map<String, RequestBody> dataToPost);

    @GET("mover/my-ratting")
    Call<ResponseBody> getDriverRatings(@Header("language") String language,
                                        @Header("access-token") String accessToken,
                                        @Query("user_id") String user_id,
                                        @Query("time_zone") String time_zone,
                                        @Query("page") String page);


    @GET("mover/last-today-delivery")
    Call<ResponseBody> driverHomeScreenData(@Header("language") String language,
                                            @Header("access-token") String accessToken,
                                            @Query("time_zone") String time_zone);


    @GET("mover/my-earning")
    Call<ResponseBody> driverEarnings(@Header("language") String language,
                                      @Header("access-token") String accessToken,
                                      @Query("time_zone") String time_zone);

    @GET("mover/delivery-of-day")
    Call<ResponseBody> deliveriesOnParticularDay(@Header("language") String language,
                                                 @Header("access-token") String accessToken,
                                                 @Query("today_date") String todayDate);

    @GET("mover/delivery-of-week")
    Call<ResponseBody> deliveriesBetweenDates(@Header("language") String language,
                                              @Header("access-token") String accessToken,
                                              @Query("start_date") String todayDate,
                                              @Query("end_date") String endDate);


    @GET("master/faq-list")
    Call<ResponseBody> driverFAQ(@Header("language") String language,
                                 @Header("access-token") String accessToken);


    @GET("notification")
    Call<ResponseBody> getDriverNotification(@Header("language") String language,
                                             @Header("access-token") String accessToken,
                                             @Query("time_zone") String time_zone,
                                             @Query("page") String page);


    @GET("user/check-mobile-number")
    Call<ResponseBody> checkMobileNumberForUpdate(@Header("language") String language,
                                                  @Header("access-token") String accessToken,
                                                  @Query("mobile") String mobileNumber,
                                                  @Query("role") String role);


    @GET("mover/amrek-android-version")
    Call<ResponseBody> checkVersionCode();


    @GET("user/free-delivery")
    Call<ResponseBody> getInviteCode(@Header("language") String language,
                                     @Header("access-token") String accessToken);

    @POST("user/apply-invite-code")
    Call<ResponseBody> applyInviteCode(@Header("language") String language,
                                       @Header("access-token") String accessToken,
                                       @Body HashMap inviteCode);


    @POST("account/check-email")
    Call<ResponseBody> checkEmail(@Header("language") String language,
                                  @Query("email") String email,
                                  @Query("role") String role);*/

}
