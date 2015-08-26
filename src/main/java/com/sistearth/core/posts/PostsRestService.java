package com.sistearth.core.posts;

import com.sistearth.api.service.Service;
import com.sistearth.api.service.ServiceException;
import com.sistearth.core.posts.routes.PostsGetRoute;

import static spark.Spark.get;

public class PostsRestService implements Service {
    @Override
    public void registerRoutes() throws ServiceException {
        get("/posts", new PostsGetRoute());
    }
}