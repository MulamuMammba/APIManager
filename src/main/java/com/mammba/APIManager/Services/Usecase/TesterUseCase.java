package com.mammba.APIManager.Services.Usecase;

import com.mammba.APIManager.Model.API;
import com.mammba.APIManager.Model.Endpoints;
import com.mammba.APIManager.Model.Response;
import com.mammba.APIManager.Services.ApiTester;

public class TesterUseCase {
    static ApiTester test = new ApiTester();
    public static Response TestEndpoint(String endPointId) {
        Endpoints endpoint = EndPointUseCase.GetEndPointById(endPointId);
        API api = ApiUseCase.FetchApi(endpoint.getApiId());

        return test.ApiTest(api.getBaseUrl(), endpoint.getUrl(),"",endpoint.getMethod());

    }
}
