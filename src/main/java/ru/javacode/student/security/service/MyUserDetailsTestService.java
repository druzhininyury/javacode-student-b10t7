package ru.javacode.student.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.javacode.student.security.model.Authority;
import ru.javacode.student.security.model.User;
import ru.javacode.student.security.repository.AuthorityRepository;
import ru.javacode.student.security.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsTestService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> dbUserOptional = userRepository.findById(username);
        if (dbUserOptional.isEmpty()) {
            throw new UsernameNotFoundException("User " + username + " hasn't been found.");
        }
        User dbUser = dbUserOptional.get();

        Optional<Authority> dbAuthorityOptional = authorityRepository.findById(username);
        String userRole = dbAuthorityOptional.isPresent() ? dbAuthorityOptional.get().getAuthority() : "USER";

        return org.springframework.security.core.userdetails.User.builder()
                   .username(dbUser.getUsername())
                   .password(dbUser.getPassword())
                   .authorities(List.of(new SimpleGrantedAuthority(userRole)))
                   .build();
    }
}
