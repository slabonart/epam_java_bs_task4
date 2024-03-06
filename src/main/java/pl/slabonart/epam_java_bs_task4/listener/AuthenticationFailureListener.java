package pl.slabonart.epam_java_bs_task4.listener;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import pl.slabonart.epam_java_bs_task4.model.UserEntity;
import pl.slabonart.epam_java_bs_task4.repository.UserRepository;
import pl.slabonart.epam_java_bs_task4.service.LoginAttemptService;

@Component
@AllArgsConstructor
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private LoginAttemptService loginAttemptService;

    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        if (event.getAuthentication().getPrincipal() instanceof String) {
            String username = (String) event.getAuthentication().getPrincipal();
            UserEntity user = userRepository.findByUserName(username);
            if (user != null) {
                loginAttemptService.loginFailed(username);
            }
        }

    }
}
