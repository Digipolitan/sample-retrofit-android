package com.digipolitan.sample.retrofit.platform.user;

import com.digipolitan.sample.retrofit.domain.user.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author Julien BACZYNSKI http://www.digipolitan.com on 10/04/2017.
 */
public interface IRFUserservice {

    @POST("users/")
    Call<ResponseBody> create(@Body User user);

    @GET("{path}")
    Call<User> read(@Path(value = "path", encoded = true) String path);
}
