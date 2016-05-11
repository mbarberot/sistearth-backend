package com.sistearth.spark.extractors;

import com.sistearth.view.request.PayloadException;
import com.sistearth.view.request.payloads.UserUpdatePayload;

import java.util.Map;

import static com.sistearth.spark.extractors.utils.PayloadExtractingUtils.fillUserPayload;

public class UserUpdatePayloadExtractor extends BasePayloadExtractor<UserUpdatePayload> {

    @Override
    protected UserUpdatePayload extractPayload(String requestBody, Map<String, String> requestHeaders) throws PayloadException {
        UserUpdatePayload payload = new UserUpdatePayload();
        fillUserPayload(payload, requestBody);
        return payload;
    }
}