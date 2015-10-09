package com.sistearth.backend.controllers.payloads.impl;

import com.sistearth.backend.controllers.payloads.Payload;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class EmptyPayload implements Payload {
    @Override
    public boolean isValid() {
        return true;
    }
}