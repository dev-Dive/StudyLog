package com.devdive.backend.auth.application.service;

import com.devdive.backend.auth.application.port.in.RegisterUseCase;
import com.devdive.backend.auth.application.port.out.persistence.LoadMemberPort;
import com.devdive.backend.auth.application.port.out.persistence.UpdateMemberPort;
import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import com.devdive.backend.auth.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterService implements RegisterUseCase {

    private final LoadMemberPort loadMemberPort;
    private final UpdateMemberPort updateMemberPort;
    private final JwtProvider mailJwtProvider;
    private final JwtProvider accessJwtProvider;
    private final JwtProvider refreshJwtProvider;

    @Override
    public AuthToken register(String token) {
        if (!mailJwtProvider.isValid(token)) {
            throw new IllegalArgumentException("Invalid JWT: " + token);
        }

        String mail = mailJwtProvider.extractSubject(token);

        if (loadMemberPort.isRegisteredMail(mail)) {
            log.info("This mail has already been registered: {}", mail);
            return generateAuthToken(mail);
        }

        Member member = new Member(mail);
        updateMemberPort.register(member);

        return generateAuthToken(mail);
    }

    private AuthToken generateAuthToken(String mail) {
        String accessToken = accessJwtProvider.createJwtToken(mail);
        String refreshToken = refreshJwtProvider.createJwtToken(mail);

        return new AuthToken(accessToken, refreshToken);
    }
}
