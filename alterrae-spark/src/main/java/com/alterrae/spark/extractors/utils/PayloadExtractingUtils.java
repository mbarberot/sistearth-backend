package com.alterrae.spark.extractors.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alterrae.api.payloads.UserPayload;
import com.alterrae.view.request.PayloadException;

import java.io.IOException;

import static com.alterrae.spark.extractors.PayloadExtractorUtils.isFilled;

public class PayloadExtractingUtils {
    public static void fillUserPayload(UserPayload payload, String requestBody) throws PayloadException {
        JsonNode rootNode = parseJson(requestBody);

        JsonNode attributes = rootNode.path("data").path("attributes");
        JsonNode usernameNode = attributes.path("username");
        JsonNode passwordNode = attributes.path("password");
        JsonNode emailNode = attributes.path("email");
        JsonNode actualPasswordNode = attributes.path("actualPassword");

        if (isFilled(usernameNode)) {
            payload.setUsername(usernameNode.asText());
        }
        if (isFilled(passwordNode)) {
            payload.setPassword(passwordNode.asText());
        }
        if (isFilled(emailNode)) {
            payload.setEmail(emailNode.asText());
        }
        if (isFilled(actualPasswordNode)) {
            payload.setActualPassword(actualPasswordNode.asText());
        }
    }

    public static JsonNode parseJson(String requestBody) throws PayloadException {
        JsonNode rootNode;
        try {
            rootNode = new ObjectMapper().readTree(requestBody);
        } catch (IOException e) {
            throw new PayloadException("Failed to extract", e);
        }
        return rootNode;
    }
}
