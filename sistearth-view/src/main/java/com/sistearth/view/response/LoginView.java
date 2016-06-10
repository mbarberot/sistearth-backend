package com.sistearth.view.response;

import com.sistearth.db.api.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public abstract class LoginView extends UserView {
    protected String token;
    public LoginView(User user, String token) {
        super(user);
        this.token = token;
    }
}
