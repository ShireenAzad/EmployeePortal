package com.everestengineering.employeeportalapplication.domain;

import com.everestengineering.employeeportalapplication.config.Encoder;
import com.everestengineering.employeeportalapplication.entities.Employee;
import com.everestengineering.employeeportalapplication.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component("userDetailsService")
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;
    private final Encoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String everestEmailId) throws UsernameNotFoundException {
        final Optional<Employee> byUsername =employeeRepository.findByEverestEmailId(everestEmailId);
        if (byUsername.isEmpty()) {
            throw new UsernameNotFoundException("User with everestEmail:" + everestEmailId + "not found");
        }
        final Employee domainUser = byUsername.get();
        Collection<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(domainUser.getDesignation()));
        return new User(domainUser.getEverestEmailId(), passwordEncoder.passwordEncoder().encode(domainUser.getPassword()), authorities);

    }
}