package com.devdive.backend.security.core;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface LoadMemberPort {
    UserData findByEmail(String mail);

    @Entity
    @Table(name = "members")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class UserData {
        @Id
        private Long id;
        @Column
        private String mail;
    }
}
