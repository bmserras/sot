package org.bmserras.sot.security;

import com.vaadin.flow.spring.security.AuthenticationContext;
import org.bmserras.sot.data.entity.user.User;
import org.bmserras.sot.data.entity.user.UserRole;
import org.bmserras.sot.data.repository.user.UserRepository;
import org.bmserras.sot.data.service.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticatedUser {

    private final UserService userService;
    private final AuthenticationContext authenticationContext;
    private final ApplicationEventPublisher eventPublisher;

    public AuthenticatedUser(AuthenticationContext authenticationContext, UserService userService, ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.authenticationContext = authenticationContext;
        this.eventPublisher = eventPublisher;
    }

    public Optional<User> get() {
        try {
            Optional<UserDetails> authenticatedUserEmail = authenticationContext.getAuthenticatedUser(UserDetails.class);
            return authenticatedUserEmail.map(userDetails -> userService.findByName(userDetails.getUsername()).get());
        }
        catch (Exception e) {

        }
        return Optional.empty();
    }

    public void register(String username, String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User(username, bCryptPasswordEncoder.encode(password));
        userService.save(user);
    }

    public void logout() {
        authenticationContext.logout();
    }

}
