package com.devdive.backend.security.access;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SecurityMetaDataSourceTest {

    @Test
    @DisplayName("정확히 일치하는 url권한 조회")
    void getRole() {
        List<String> roles = List.of("ADMIN", "USER");

        LinkedHashMap<String, List<String>> map = new LinkedHashMap<>();
        map.put("/user",List.of("USER"));
        map.put("/read",roles);
        SecurityMetaDataSource securityMetaDataSource = new SecurityMetaDataSource(map);

        List<String> result = securityMetaDataSource.getRoles(new MockHttpServletRequest("GET", "/read"));
        assertThat(result).isEqualTo(roles);
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("하위 url권한 조회")
    void testSubURLPermissionQuery() {
        List<String> roles = List.of("ADMIN", "USER");

        LinkedHashMap<String, List<String>> map = new LinkedHashMap<>();
        map.put("/user",List.of("USER"));
        map.put("/read/**",roles);
        SecurityMetaDataSource securityMetaDataSource = new SecurityMetaDataSource(map);

        List<String> result = securityMetaDataSource.getRoles(new MockHttpServletRequest("GET", "/read/user/name"));
        assertThat(result).isEqualTo(roles);
        assertThat(result.size()).isEqualTo(2);
    }
}