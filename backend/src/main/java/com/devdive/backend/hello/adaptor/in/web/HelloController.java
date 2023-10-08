package com.devdive.backend.hello.adaptor.in.web;

import com.devdive.backend.security.core.resolver.AuthenticationPrincipal;
import com.devdive.backend.security.authentication.domain.UserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/hello/admin")
    public ResponseEntity<String> admin(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(user.getUsername());
    }

}
