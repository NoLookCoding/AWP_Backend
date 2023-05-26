package com.nolookcoding.userservice.domain;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    private static Map<String, Long> store = new ConcurrentHashMap<>();

    public Cookie createSession(User user) {
        String token = UUID.randomUUID().toString();
        Cookie cookie = new Cookie(SessionConst.sessionId, token);
        store.put(token, user.getIndex());
        return cookie;
    }

    public Long getSession(String value) {
        return findCookie(value);
    }

    public Long findCookie(String value) {
        if (store.containsKey(value)) {
            return store.get(value);
        }
        return null;
    }

    public void sessionExpire(String value) {
        store.remove(value);
        System.out.println("세션 삭제!");
    }
}
