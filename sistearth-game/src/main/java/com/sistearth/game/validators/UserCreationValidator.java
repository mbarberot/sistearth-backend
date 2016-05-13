package com.sistearth.game.validators;

import com.sistearth.api.beans.Error;
import com.sistearth.api.payloads.UserCreationPayload;
import com.sistearth.api.validators.Validator;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class UserCreationValidator implements Validator {

    private UserCreationPayload payload;

    public UserCreationValidator(UserCreationPayload payload) {
        this.payload = payload;
    }

    @Override
    public boolean isValid() {
        return isNotBlank(payload.getUsername())
                && isNotBlank(payload.getEmail())
                && isNotBlank(payload.getPassword());
    }

    @Override
    public List<Error> getErrors() {
        List<Error> errors = newArrayList();
        if (isBlank(payload.getUsername())) {
            errors.add(new Error("400", "username"));
        }
        if (isBlank(payload.getPassword())) {
            errors.add(new Error("400", "email"));
        }
        if (isBlank(payload.getPassword())) {
            errors.add(new Error("400", "password"));
        }
        return errors;
    }
}
