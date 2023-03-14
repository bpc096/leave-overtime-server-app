package com.mii.server.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mii.server.models.AppUserDetail;
import com.mii.server.models.User;
import com.mii.server.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmployee_Email(username, username).orElseThrow(()->
        new UsernameNotFoundException("Username  or email not found"));
      return new AppUserDetail(user);  
    }
    
}
