package org.bmserras.sot.security;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.bmserras.sot.data.db.user.User;
import org.bmserras.sot.data.repository.user.UserRepository;
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

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity {

    private final UserRepository userRepository;

    public SecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
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
                User user = userRepository.findByName(username);
                if (user == null) {
                    throw new UsernameNotFoundException("No user present with username: " + username);
                } else {
                    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasswordHash(),
                            getAuthorities(user));
                }
            }

            private static List<GrantedAuthority> getAuthorities(User user) {
                List<GrantedAuthority> list = new ArrayList<>();
                list.add(new SimpleGrantedAuthority("ROLE_USER"));
                return list;
            }
        };
    }
}
