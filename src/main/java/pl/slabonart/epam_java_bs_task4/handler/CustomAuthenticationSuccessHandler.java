package pl.slabonart.epam_java_bs_task4.handler;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.slabonart.epam_java_bs_task4.model.UserEntity;
import pl.slabonart.epam_java_bs_task4.service.LoginAttemptService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private LoginAttemptService loginAttemptService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        loginAttemptService.loginSuccess(user.getUsername());
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
