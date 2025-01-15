package com.mammba.APIManager.Services.Usecase;

import com.mammba.APIManager.Model.API;
import com.mammba.APIManager.Model.Endpoints;
import com.mammba.APIManager.Repository.ApiTable;
import com.mammba.APIManager.Repository.EndpointsTable;

import java.util.List;

public class EndPointUseCase {

    public static List<Endpoints> EndPointList(String ApiId) {

        return EndpointsTable.getEndpointByApiId(ApiId);
    }

    public static Endpoints GetEndPointById(String id){
        return EndpointsTable.getEndpointById(id);
    }
    public static void CreateEndPoint(Endpoints endpoint) {
        EndpointsTable.insertEndpoint(endpoint);

    }
    public static void RemoveEndpoint(String EndPointId){
        EndpointsTable.removeEndpointById(EndPointId);
    }
    public static void RemoveAllApiEndpoint(String ApiId){
        EndpointsTable.removeEndpointByApiId(ApiId);
    }


}
