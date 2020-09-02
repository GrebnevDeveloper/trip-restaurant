package com.grebnev.data.api;

import com.grebnev.data.BuildConfig;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .addHeader(BuildConfig.API_HOST_NAME, BuildConfig.API_HOST_VALUE)
                .addHeader(BuildConfig.API_KEY_NAME, BuildConfig.API_KEY_VALUE)
                .build();

        return chain.proceed(request);
    }
}
