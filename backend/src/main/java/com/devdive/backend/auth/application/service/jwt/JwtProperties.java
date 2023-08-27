package com.devdive.backend.auth.application.service.jwt;


public class JwtProperties {
    // TODO : yml 파일에서 받아 사용
    public final String secret = "devdive";
    public int expireJwtTime = 1_000 * 60 * 60;

    public JwtProperties(int hour){
        this.expireJwtTime *= hour;
    }
}
