package com.mammba.APIManager.Services.Usecase;

import com.mammba.APIManager.Model.API;
import com.mammba.APIManager.Repository.ApiTable;

import java.util.List;
import java.util.Objects;

public class ApiUseCase {

    public static List<API> apiList(String email) {
        return ApiTable.getApiByUser(email);
    }

    public static boolean ApiExists(String ApiId) {
        API api = ApiTable.getApiById(ApiId);

        return !(api.getName() == null);
    }

    public static void CreateApi(API api) {
        ApiTable.insertApi(api);

    }

    public static void RemoveApi(String ApiId) {
        ApiTable.RemoveApi(ApiId);
        EndPointUseCase.RemoveAllApiEndpoint(ApiId);
    }

    public static void RemoveAllUserApi(String email) {
        List<API> apiList = ApiTable.getApiByUser(email);
        apiList.forEach(api -> {
            EndPointUseCase.RemoveAllApiEndpoint(api.getId());
        });

        ApiTable.removeUserByApi(email);
    }

    private static API FetchApi(API api) {
        return ApiTable.getApiById(api.getId());
    }
    public static API FetchApi(String apiId) {
        return ApiTable.getApiById(apiId);
    }

    public static API FetchApiById(API api) {
        API savedApi = FetchApi(api);

        if (Objects.equals(savedApi.getUserEmail(), api.getUserEmail())) {
            return savedApi;
        } else return new API(null, null, null, null, null);

    }

}
