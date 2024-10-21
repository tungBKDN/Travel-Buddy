package com.travelbuddy.auth.permission;

import com.travelbuddy.auth.admin.AdminEntity;
import com.travelbuddy.auth.admin.AdminRepository;
import com.travelbuddy.user.UserEntity;
import com.travelbuddy.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (userRepository.existsByEmail(email)) {
            return userRepository.findByEmailOrUsername(email, email)
                    .map(CustomUserDetailsService::create)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
        } else {
            return adminRepository.findByEmailOrUsername(email, email)
                    .map(CustomUserDetailsService::create)
                    .orElseThrow(() -> new UsernameNotFoundException("Admin Not Found with email or username: " + email));
        }
    }

    public static CustomUserDetails create(UserEntity user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return CustomUserDetails.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .enabled(user.isEnabled())
                .authorities(authorities)
                .build();
    }

    public static CustomUserDetails create(AdminEntity admin) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (GroupEntity groupEntity : admin.getGroupEntities()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + groupEntity.getName()));
            authorities.addAll(groupEntity.getPermissionEntities().stream()
                    .map(p -> new SimpleGrantedAuthority(p.getName()))
                    .toList());
        }

        return CustomUserDetails.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .password(admin.getPassword())
                .enabled(admin.isEnabled())
                .authorities(authorities)
                .build();
    }
}
