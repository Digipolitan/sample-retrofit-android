package com.digipolitan.sample.retrofit.platform.common;


/**
 * @author Julien BACZYNSKI http://www.digipolitan.com on 10/04/2017.
 */
public interface IServiceResultListener<T> {

    void onResult(ServiceResult<T> result);
}
