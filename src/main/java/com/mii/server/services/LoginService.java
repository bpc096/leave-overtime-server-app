package com.mii.server.services;

import com.mii.server.models.User;
import com.mii.server.models.dto.requests.LoginRequest;
import com.mii.server.models.dto.responses.LoginResponse;
import com.mii.server.repositories.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

        private AuthenticationManager authenticationManager;
        private UserRepository userRepository;
        private AppUserDetailService appUserDetailService;

        public LoginResponse login(LoginRequest loginRequest) {
                // loginRequest username & password
                UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword());

                // set principle
                Authentication auth = authenticationManager.authenticate(authReq);
                SecurityContextHolder.getContext().setAuthentication(auth);

                User user = userRepository
                                .findByUsernameOrEmployee_Email(
                                                loginRequest.getUsername(),
                                                loginRequest.getUsername())
                                .get();

                UserDetails userDetails = appUserDetailService.loadUserByUsername(
                                loginRequest.getUsername());

                List<String> authorities = userDetails
                                .getAuthorities()
                                .stream()
                                .map(authority -> authority.getAuthority())
                                .collect(Collectors.toList());

                return new LoginResponse(
                                user.getUsername(),
                                user.getEmployee().getEmail(),
                                authorities);
        }
}
