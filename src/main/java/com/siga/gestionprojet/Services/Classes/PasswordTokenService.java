package com.siga.gestionprojet.Services.Classes;

import com.siga.gestionprojet.dao.entities.RestPasswordToken;
import com.siga.gestionprojet.dao.repositories.RestPasswordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PasswordTokenService {
    private final RestPasswordRepository passwordTokenRepository;


    public void saveConfirmationToken(RestPasswordToken token) {
        passwordTokenRepository.save(token);
    }

    public Optional<RestPasswordToken> getToken(String token) {
        return passwordTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return passwordTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    public void deleteAllTokens(){
        passwordTokenRepository.deleteAll();
    }

}
