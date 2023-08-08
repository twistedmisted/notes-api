package ua.zxc.notes.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ua.zxc.notes.repository.UserRepository;
import ua.zxc.notes.service.security.JwtUserProvider;
import ua.zxc.notes.web.filter.JwtUserRequestFilter;

import java.util.Collections;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@RequiredArgsConstructor
public class JwtSecurityConfig {

    public static final String USER_ROLE = "USER";

    private static final String API_PATH = "/api";

    private static final String[] AUTH_WHITELIST = {
            API_PATH + "/auth/register",
            API_PATH + "/auth/login"
    };

    private final JwtUserRequestFilter jwtRequestFilter;
    private final UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtUserProvider jwtUserProvider() {
        return new JwtUserProvider(userRepository, passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(jwtUserProvider()));
    }

    @Bean
    public SecurityFilterChain configure(final HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authReq -> authReq
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().hasAuthority(USER_ROLE))
                .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer.sessionCreationPolicy(STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
