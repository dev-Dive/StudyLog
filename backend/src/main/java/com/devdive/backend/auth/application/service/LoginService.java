package com.devdive.backend.auth.application.service;

import com.devdive.backend.auth.application.port.in.LoginUseCase;
import com.devdive.backend.auth.application.port.out.persistence.LoadMemberPort;
import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

    private final LoadMemberPort loadMemberPort;
    private final JwtProvider mailJwtProvider;
    private final JwtProvider accessJwtProvider;
    private final JwtProvider refreshJwtProvider;

    @Override
    public AuthToken login(String token) {
        if (!mailJwtProvider.isValid(token)) {
            throw new IllegalArgumentException("Invalid JWT: " + token);
        }

        String mail = mailJwtProvider.extractSubject(token);

        if (loadMemberPort.isRegisteredMail(mail)) {
            log.info("{} login success", mail);
            return generateAuthToken(mail);
        }

        throw new IllegalStateException("Must be registered as a member before logging in");
    }

    private AuthToken generateAuthToken(String mail) {
        String accessToken = accessJwtProvider.createJwtToken(mail);
        String refreshToken = refreshJwtProvider.createJwtToken(mail);

        return new AuthToken(accessToken, refreshToken);
    }
}
