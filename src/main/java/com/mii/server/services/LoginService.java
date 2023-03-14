package com.mii.server.services;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mii.server.models.Employee;
import com.mii.server.models.Role;
import com.mii.server.models.Token;
import com.mii.server.models.User;
import com.mii.server.models.dto.requests.LoginRequest;
import com.mii.server.models.dto.requests.UserRequest;
import com.mii.server.models.dto.response.LoginResponse;
import com.mii.server.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginService {
    
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private AppUserDetailService appUserDetailsService;
    private TokenService tokenService;
    private UserService userService;
    private EmployeeService employeeService;

    private RoleService roleService;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private JavaMailSender mailSender;

    public LoginResponse login(LoginRequest loginRequest) {
        


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

    UserDetails userDetails = appUserDetailsService.loadUserByUsername(
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

public User register(UserRequest userRequest, String siteURL)
        throws UnsupportedEncodingException, MessagingException {
    User user = modelMapper.map(userRequest, User.class);
    Employee employee = modelMapper.map(userRequest, Employee.class);
    Token token = modelMapper.map(userRequest, Token.class);

    List<Role> roles = new ArrayList<>();
    roles.add(roleService.getById(1));
    user.setRoles(roles);

    // set password
    employee.setManager(employeeService.getById(userRequest.getManagerId()));
    user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

    // set role
    user.setEmployee(employee);
    employee.setUser(user);
    token.setUser(user);

    // set Token
    String code = UUID.randomUUID().toString();
    token.setToken(code);
    token.setCreated(LocalDateTime.now());
    token.setExpired(LocalDateTime.now().plusHours(1));

    sendVerificationEmail(user, siteURL, token);

//        Token confirmToken = new Token(
//                code,
//                LocalDateTime.now(),
//                LocalDateTime.now().plusHours(1),
//                user
//        );
    tokenService.create(token);

    return userRepository.save(user);
}

private void sendVerificationEmail(User user, String siteURL, Token token)
        throws MessagingException, UnsupportedEncodingException {

    String content = "Dear [[name]],<br>"
            + "Please click the link below to verify your registration:<br>"
            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
            + "Thank you,<br>"
            + "Metro Data.";

    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);

    // helper.setFrom(fromAddress, senderName);
    helper.setTo(user.getEmployee().getEmail());
    helper.setSubject("Please verify your registration");

    content = content.replace("[[name]]", user.getEmployee().getName());
    String verifyURL = siteURL + "/verify?code=" + token.getToken();

    content = content.replace("[[URL]]", verifyURL);

    helper.setText(content, true);

    mailSender.send(message);
}

@Transactional
public String verify(String verify) {
    Token token = tokenService
            .getToken(verify)
            .orElseThrow(()
                    -> new IllegalStateException("<div class=\"container text-center\">\n"
                    + "<h3>NOT FOUND!</h3>"
                    + "Sorry, we could not verify your user account.\n"
                    + "the token is incorrect.<br>\n"
                    + "</div>"));

    if (token.getConfirmed() != null) {
        return "<div class=\"container text-center\">\n"
                + "<h3>FAILED!</h3>"
                + "Sorry, we could not verify your user account because it already verified,\n"
                + "</div>";
    }

    LocalDateTime expired = token.getExpired();

    if (expired.isBefore(LocalDateTime.now())) {
        return "<div class=\"container text-center\">\n"
                + "<h3>EXPIRED</h3>"
                + "Your user account has been expired.<br>\n"
                + "Please register again.<br>\n"
                + "</div>";
    }

    tokenService.setConfirmed(verify);
    userService.accountUser(token.getUser().getUsername());
    return "<div class=\"container text-center\">\n"
            + "<h3>SUCCESS</h3>"
            + "Congratulations, your user account has been verified.<br>\n"
            + "</div>";
}
}
