package org.bmserras.sot.security;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.bmserras.sot.data.domain.User;
import org.bmserras.sot.data.service.UserService;
import org.bmserras.sot.views.auth.LoginView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity {

    private final UserService userService;

    public SecurityConfiguration(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests().requestMatchers(
                new AntPathRequestMatcher("/public/**"),
                new AntPathRequestMatcher("/icons/**"),
                new AntPathRequestMatcher("/line-awesome/**/*.svg")
        ).permitAll();

        super.configure(http);

        // Allow access to LoginView
        setLoginView(http, LoginView.class);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<User> byName = userService.findByName(username);
                if (byName.isEmpty()) {
                    throw new UsernameNotFoundException("No user present with username: " + username);
                } else {
                    return new org.springframework.security.core.userdetails.User(byName.get().getUsername(),
                            byName.get().getPasswordHash(),
                            getAuthorities());
                }
            }

            private static List<GrantedAuthority> getAuthorities() {
                List<GrantedAuthority> list = new ArrayList<>();
                list.add(new SimpleGrantedAuthority("ROLE_USER"));
                return list;
            }
        };
    }
}
