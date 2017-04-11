package com.digipolitan.sample.retrofit.platform.common;

import com.digipolitan.sample.retrofit.domain.Constants;
import com.digipolitan.sample.retrofit.platform.gson.Exclude;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Julien BACZYNSKI http://www.digipolitan.com on 10/04/2017.
 */

public class Session {
    private static Retrofit retrofit;
    private static OkHttpClient httpClient;
    private static Gson gson;

    public static Retrofit getDefault() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .client(getClient())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getClient() {
        if (httpClient == null) {
            Interceptor interceptor = new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder().addHeader("apikey", Constants.API_KEY).build();
                    return chain.proceed(newRequest);
                }
            };

            // Add the interceptor to OkHttpClient
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(interceptor);
            HttpLoggingInterceptor logingInterceptor = new HttpLoggingInterceptor();
            logingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logingInterceptor);
            httpClient = builder.build();
        }
        return httpClient;
    }

    public static Gson getGson() {
        if (gson == null) {
            GsonBuilder builder = getDefaultGsonBuilder();
            //builder.registerTypeAdapter(User.class, User.getGsonSerializer());
            //builder.registerTypeAdapter(User.class, User.getGsonDeserializer());
            gson = builder.create();
        }
        return gson;
    }

    public static GsonBuilder getDefaultGsonBuilder() {
        GsonBuilder defaultGsonBuilder = new GsonBuilder();
        defaultGsonBuilder.addSerializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getAnnotation(Exclude.class) != null;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        });
        defaultGsonBuilder.setDateFormat("yyyy-MM-dd");
        return defaultGsonBuilder;
    }
}
