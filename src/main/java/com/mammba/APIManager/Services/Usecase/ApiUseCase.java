package com.mammba.APIManager.Services.Usecase;

import com.mammba.APIManager.Model.API;
import com.mammba.APIManager.Repository.ApiTable;

import java.util.List;

public class ApiUseCase {

    public static List<API> apiList(String email) {
        return ApiTable.getApiByUser(email);
    }

    public static boolean ApiExists(String ApiId) {
        API api = ApiTable.getApiById(ApiId);

        return !(api.getName() == null);
    }

    public static void CreateApi(String email, String name) {
        API api = new API(null, email, name, null, null);
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
}
