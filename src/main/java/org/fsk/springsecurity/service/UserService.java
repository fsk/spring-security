package org.fsk.springsecurity.service;

import lombok.RequiredArgsConstructor;
import org.fsk.springsecurity.dto.RegisterUserDTO;
import org.fsk.springsecurity.entities.Role;
import org.fsk.springsecurity.entities.User;
import org.fsk.springsecurity.enums.RoleEnum;
import org.fsk.springsecurity.repository.RoleRepository;
import org.fsk.springsecurity.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> allUsers() {

        return new ArrayList<>(userRepository.findAll());
    }

    public User createAdministrator(RegisterUserDTO input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new User();

        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRole(optionalRole.get());

        return userRepository.save(user);
    }
}
