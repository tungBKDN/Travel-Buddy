package com.travelbuddy.auth.admin;

import com.travelbuddy.auth.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AdminDetailsService implements UserDetailsService {
    @Autowired
    AdminRepository adminRepository;
    @Override
    public UserDetails loadUserByUsername(String emailOrUsername) throws UsernameNotFoundException {
            AdminEntity admin = adminRepository.findByEmailOrUsername(emailOrUsername, emailOrUsername)
                    .orElseThrow(() -> new UsernameNotFoundException("Admin Not Found with email or username: " + emailOrUsername));
            return AdminDetails.builder()
                    .id(admin.getId())
                    .email(admin.getEmail())
                    .password(admin.getPassword())
                    .enabled(admin.isEnabled())
                    .authorities(List.of(new SimpleGrantedAuthority("ROLE_" + Role.ADMIN)))
                    .build();
    }
}
