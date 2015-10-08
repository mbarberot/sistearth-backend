package com.sistearth.backend.services.impl;

import com.sistearth.backend.services.Service;
import com.sistearth.backend.services.ServiceException;
import com.sistearth.core.database.PostManager;
import com.sistearth.core.database.UserManager;
import com.sistearth.core.models.Post;
import com.sistearth.core.models.User;
import com.sistearth.core.serializers.JSONApiPostBuilder;
import com.sistearth.core.serializers.JsonSerializer;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.sistearth.api.database.Database.getDatabase;
import static java.lang.Integer.valueOf;
import static spark.Spark.get;

public class PostsRestService implements Service {

    @Override
    public void registerRoutes() throws ServiceException {
        PostManager postManager = new PostManager(getDatabase());
        UserManager userManager = new UserManager(getDatabase());

        get("/api/posts", (request, response) -> {
            List<Post> posts = postManager.getAll();
            List<User> authors = newArrayList();
            for (Post post : posts) {
                authors.add(userManager.getById(post.getAuthor()));
            }
            return new JSONApiPostBuilder().build(posts, authors);
        }, new JsonSerializer());

        get("/api/posts/:id", (request, response) -> {
            Post post = postManager.getById(valueOf(request.params("id")));
            User author = userManager.getById(post.getAuthor());
            return new JSONApiPostBuilder().build(post, author);
        }, new JsonSerializer());
    }


}