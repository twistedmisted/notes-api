package ua.zxc.notes.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.zxc.notes.entity.UserEntity;
import ua.zxc.notes.repository.UserRepository;

import java.util.Collections;

import static ua.zxc.notes.config.JwtSecurityConfig.USER_ROLE;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " is not found"));
        return new User(username, user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(USER_ROLE)));
    }
}
