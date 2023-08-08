package ua.zxc.notes.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.zxc.notes.entity.UserEntity;
import ua.zxc.notes.repository.UserRepository;

import java.util.Collections;

import static ua.zxc.notes.config.JwtSecurityConfig.USER_ROLE;

@RequiredArgsConstructor
public class JwtUserProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String username = auth.getName();
        String password = auth.getCredentials().toString();
        UserEntity user = userRepository.findByUsername(username).orElse(null);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("External system authentication failed");
        }
        return new UsernamePasswordAuthenticationToken(username, user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(USER_ROLE)));
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
