package com.classmanager.classservice.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableMethodSecurity
@RestController
@RequestMapping("/api/${api.version}/public")
public class ProfileController {
    @PreAuthorize("hasAuthority('TEACHER')")
    @GetMapping(value = "/")
    public ResponseEntity<String> publicResource() {
        return ResponseEntity.ok().body("this is a public route!");
    }
}
