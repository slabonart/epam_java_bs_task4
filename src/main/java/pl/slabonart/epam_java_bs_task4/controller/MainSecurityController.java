package pl.slabonart.epam_java_bs_task4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.slabonart.epam_java_bs_task4.model.CachedValue;
import pl.slabonart.epam_java_bs_task4.model.UserEntity;
import pl.slabonart.epam_java_bs_task4.repository.UserRepository;
import pl.slabonart.epam_java_bs_task4.service.LoginAttemptService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static pl.slabonart.epam_java_bs_task4.handler.CustomAuthenticationFailureHandler.LAST_USERNAME_KEY;

@Controller
@RequiredArgsConstructor
public class MainSecurityController {

    private final LoginAttemptService loginAttemptService;

    private final UserRepository userRepository;

    @GetMapping("/info")
    public String info() {
        return "info";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "about";
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "admin";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", defaultValue = "false") boolean loginError,
                        final ModelMap model, HttpSession session) {
        String username = getUsername(session);

        if (loginError && username != null) {
            if (loginAttemptService.isBlocked(username)) {
                model.addAttribute("accountLocked", Boolean.TRUE);
            }
        }
        return "login";
    }

    @GetMapping("/logoutSuccess")
    public String getLogoutSuccess() {
        return "logout";
    }

    @GetMapping("/blocked")
    public String blocked(Model model) {
        List<UserEntity> userEntities = userRepository.findAll();
        Map<String, CachedValue> blockedUsers = userEntities.stream()
                .map(UserEntity::getUsername)
                .filter(userName -> loginAttemptService.isBlocked(userName))
                .collect(Collectors.toMap(user -> user, user -> loginAttemptService.getCachedValue(user)));

        if (!blockedUsers.isEmpty()) {
            model.addAttribute("blockedUsers", blockedUsers);
        }

        return "blocked";
    }

    private String getUsername(HttpSession session) {
        final String username = (String) session.getAttribute(LAST_USERNAME_KEY);
        if (username != null && !username.isEmpty()) {
            session.removeAttribute(LAST_USERNAME_KEY);

        }
        return username;
    }
}
