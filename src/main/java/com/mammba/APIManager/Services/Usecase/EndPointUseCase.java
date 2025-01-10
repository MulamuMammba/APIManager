package com.mammba.APIManager.Services.Usecase;

import com.mammba.APIManager.Model.API;
import com.mammba.APIManager.Model.Endpoints;
import com.mammba.APIManager.Repository.ApiTable;
import com.mammba.APIManager.Repository.EndpointsTable;

import java.util.List;

public class EndPointUseCase {

    public static List<Endpoints> EndPointList(String email) {
        return EndpointsTable.getEndpointByUserEmail(email);
    }

    public static boolean EpiExists(String EndPointId) {
        Endpoints endpoint = EndpointsTable.getEndpointById(EndPointId);

        assert endpoint != null;
        return !(endpoint.getName() == null);
    }

    public static void CreateEndPoint(String email, String name) {
        API api = new API(null, email, name, null, null);
        ApiTable.insertApi(api);

    }
    public static void RemoveEndpoint(String EndPointId){
        EndpointsTable.removeEndpointById(EndPointId);
    }
    public static void RemoveAllApiEndpoint(String ApiId){
        EndpointsTable.removeEndpointByApiId(ApiId);
    }
}
