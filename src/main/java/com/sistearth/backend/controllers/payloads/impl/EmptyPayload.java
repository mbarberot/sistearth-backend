package com.sistearth.backend.controllers.payloads.impl;

import com.sistearth.backend.controllers.payloads.Payload;
import com.sistearth.backend.utils.Error;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
public class EmptyPayload implements Payload<Void> {
    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Void getEntity() {
        return null;
    }

    @Override
    public List<Error> getErrors() {
        return null;
    }
}
