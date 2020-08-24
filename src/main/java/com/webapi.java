package com;
import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class webapi {
    public static void main(String[] args) {
        get("/hello", (req, res)->"Hello world");
    }

    public void getRequest(Request req, Response res){
        System.out.println(req.body());
    }
}
