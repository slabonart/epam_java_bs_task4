package pl.slabonart.jabs_4.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.slabonart.jabs_4.model.UserEntity;
import pl.slabonart.jabs_4.repository.UserRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUserName(username);
        if (userEntity == null) {
            log.error("Couldn't find user for name: " + username);
            throw new UsernameNotFoundException("Couldn't find user for name: " + username);
        }
        return userEntity;
    }
}