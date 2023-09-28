package vn.skymapglobal.smartcheckingapp.services;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import vn.skymapglobal.smartcheckingapp.models.User;

public interface ApiService {
    @FormUrlEncoded
    @POST("https://armonitoring.skymapglobal.vn/api/authenticate") //
    Call<User> login(
            @Field("username") String username,
            @Field("password") String password
    );
}
