package com.digipolitan.sample.retrofit.platform.common;


/**
 * @author Julien BACZYNSKI http://www.digipolitan.com on 10/04/2017.
 */
public class ServiceResult<T> {
    private T mData;
    private ServiceException mError;

    public ServiceResult() {
        super();
    }

    public ServiceResult(T data, ServiceException error) {
        this.mData = data;
        this.mError = error;
    }

    public T getData() {
        return mData;
    }

    public void setData(T data) {
        this.mData = data;
    }

    public ServiceException getError() {
        return mError;
    }

    public void setError(ServiceException error) {
        this.mError = error;
    }

    @Override
    public String toString() {
        return "ServiceResult{" +
                "mData=" + mData +
                ", mError=" + mError +
                '}';
    }
}
