package com.devdive.backend.security.core;

import com.devdive.backend.security.core.LoadMemberPort.UserData;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDataRepository extends JpaRepository<UserData, Long> {

    UserData findByMail(String mail);
}
