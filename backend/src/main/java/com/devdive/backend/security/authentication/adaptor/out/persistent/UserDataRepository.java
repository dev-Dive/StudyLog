package com.devdive.backend.security.authentication.adaptor.out.persistent;

import com.devdive.backend.security.authentication.application.port.out.LoadMemberPort.UserData;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDataRepository extends JpaRepository<UserData, Long> {

    UserData findByMail(String mail);
}
