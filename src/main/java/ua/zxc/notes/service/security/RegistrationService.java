package ua.zxc.notes.service.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.zxc.notes.entity.UserEntity;
import ua.zxc.notes.payload.RegistrationRequest;
import ua.zxc.notes.repository.UserRepository;

import static org.springframework.http.HttpStatus.CONFLICT;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(RegistrationRequest registrationRequest) {
        if (existsByUsername(registrationRequest.getUsername())) {
            throw new ResponseStatusException(CONFLICT, "The user with such username already exists.");
        }
        if (existsByEmail(registrationRequest.getEmail())) {
            throw new ResponseStatusException(CONFLICT, "The user with such email already exists.");
        }
        userRepository.save(createUserEntity(registrationRequest));
    }

    private boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    private UserEntity createUserEntity(RegistrationRequest registrationRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registrationRequest.getUsername());
        userEntity.setEmail(registrationRequest.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        return userEntity;
    }
}
