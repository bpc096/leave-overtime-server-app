// package com.mii.server.services;

// import com.mii.server.models.AppUserDetail;
// import com.mii.server.models.User;
// import com.mii.server.repositories.UserRepository;
// import lombok.AllArgsConstructor;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import
// org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// @Service
// @AllArgsConstructor
// public class AppUserDetailService implements UserDetailsService {

// private UserRepository userRepository;
// private final static String USER_NOT_FOUND = "user or email : %s, not found";

// @Override
// public UserDetails loadUserByUsername(String email)
// throws UsernameNotFoundException {
// User user = userRepository
// .findByUsernameOrEmployee_Email(email, email)
// .orElseThrow(() -> new
// UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
// return new AppUserDetail(user);
// }
// }
