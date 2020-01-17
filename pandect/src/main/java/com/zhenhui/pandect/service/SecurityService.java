package com.zhenhui.pandect.service;

import org.rapidpm.frp.model.Result;
import org.rapidpm.frp.model.serial.Pair;

import java.time.LocalDateTime;

import static org.rapidpm.frp.matcher.Case.match;
import static org.rapidpm.frp.matcher.Case.matchCase;
import static org.rapidpm.frp.model.Result.failure;
import static org.rapidpm.frp.model.Result.success;

public class SecurityService {

    public static class User extends Pair<String, LocalDateTime> {
        public User(String name, LocalDateTime timestamp) {
            super(name, timestamp);
        }
    }

    public Result<User> checkLogin(String username, String password) {
        return match(
                matchCase(() -> failure("security_service.login.denied")),
                matchCase(() -> username == null, () -> failure("security_service.username.null")),
                matchCase(username::isEmpty, () -> failure("security_service.username.is-empty")),
                matchCase(() -> password == null, () -> failure("security_service.password.null")),
                matchCase(password::isEmpty, () -> failure("security_service.password.is-empty")),
                matchCase(() -> username.equals("admin") && password.equals("admin"),
                        () -> success(new User("Jon Doe", LocalDateTime.now())))
        );
    }
}
