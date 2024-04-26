package org.fsk.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminEndpoint() {
        return "Welcome to Admin Endpoint";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String userEndpoint() {
        return "Welcome to User Endpoint";
    }

    @GetMapping("/superadmin")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public String superAdminEndpoint() {
        return "Welcome to Super Admin Endpoint";
    }



}
