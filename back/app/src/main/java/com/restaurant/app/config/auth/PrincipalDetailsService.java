package com.restaurant.app.config.auth;

import com.restaurant.app.model.User;
import com.restaurant.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        System.out.println("loadUserByUsername");

        User user = userRepository.findUserByEmail(email);

        if(user == null) {
            throw new UsernameNotFoundException("UserEmail is not exist.");
        }
        return new PrincipalDetails(user);
    }
}
