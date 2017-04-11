package com.digipolitan.sample.retrofit.platform.user;

import com.digipolitan.sample.retrofit.domain.user.model.User;
import com.digipolitan.sample.retrofit.domain.user.service.IUserService;
import com.digipolitan.sample.retrofit.platform.common.IServiceResultListener;
import com.digipolitan.sample.retrofit.platform.common.ServiceException;
import com.digipolitan.sample.retrofit.platform.common.ServiceExceptionType;
import com.digipolitan.sample.retrofit.platform.common.ServiceResult;
import com.digipolitan.sample.retrofit.platform.common.Session;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Julien BACZYNSKI http://www.digipolitan.co m on 10/04/2017.
 */
public class UserService implements IUserService {
    private IRFUserservice mRfuserService;

    @Override
    public void create(User user, final IServiceResultListener<String> resultListener) {
        Call<ResponseBody> call = getmRfuserService().create(user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ServiceResult<String> result = new ServiceResult<>();
                if(response.code() == 201)
                    result.setData(response.headers().get("Resourceuri"));
                else
                    result.setError(new ServiceException(response.code()));
                if(resultListener != null)
                    resultListener.onResult(result);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ServiceResult<String> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if(resultListener != null)
                    resultListener.onResult(result);
            }
        });
    }

    @Override
    public void read(String resourceUri, final IServiceResultListener<User> resultListener) {
        Call<User> call = getmRfuserService().read(resourceUri);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                ServiceResult<User> result = new ServiceResult<>();
                if(response.code() == 200)
                    result.setData(response.body());
                else
                    result.setError(new ServiceException(response.code()));
                if(resultListener != null)
                    resultListener.onResult(result);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                ServiceResult<User> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if(resultListener != null)
                    resultListener.onResult(result);
            }
        });

    }

    private IRFUserservice getmRfuserService() {
        if (mRfuserService == null)
            mRfuserService = Session.getDefault().create(IRFUserservice.class);
        return mRfuserService;
    }
}
