package com.sistearth.view.request.payloads;


import com.sistearth.db.beans.User;
import com.sistearth.view.request.Payload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Data
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public abstract class UserPayload implements Payload<User> {
    protected String id;
    protected String username;
    protected String password;
    protected String email;
    protected String actualPassword;
}
