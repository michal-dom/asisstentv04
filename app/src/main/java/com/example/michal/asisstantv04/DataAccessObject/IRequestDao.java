package com.example.michal.asisstantv04.DataAccessObject;

import com.example.michal.asisstantv04.Models.Request;

import java.util.List;

public interface IRequestDao {
    public Request fetchRequestById(int requestId);
    public List<Request> fetchAllRequests();
    public int addRequest(Request request);
    public boolean deleteAllRequests();
}
