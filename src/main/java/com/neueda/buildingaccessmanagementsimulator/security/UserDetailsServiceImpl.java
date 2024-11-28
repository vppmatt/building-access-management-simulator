package com.neueda.buildingaccessmanagementsimulator.security;

import com.neueda.buildingaccessmanagementsimulator.data.LoginRepository;
import com.neueda.buildingaccessmanagementsimulator.model.Login;
import com.neueda.buildingaccessmanagementsimulator.security.bearer.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Login login = loginRepository.findByName(name).orElseThrow( () -> new UsernameNotFoundException(name));
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + login.getRole().toString()));
        return new org.springframework.security.core.userdetails.User(login.getName(), login.getPassword(),roles);
    }
}
