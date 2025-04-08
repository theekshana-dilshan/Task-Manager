package com.example.task_manager.service.impl;
import com.example.greenshadowbackendspringboot.dao.UserDao;
import com.example.greenshadowbackendspringboot.dto.impl.UserDTO;
import com.example.greenshadowbackendspringboot.entity.impl.UserEntity;
import com.example.greenshadowbackendspringboot.secure.JWTAuthResponse;
import com.example.greenshadowbackendspringboot.secure.SignIn;
import com.example.greenshadowbackendspringboot.service.AuthService;
import com.example.greenshadowbackendspringboot.service.JWTService;
import com.example.greenshadowbackendspringboot.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceIMPL implements AuthService {
    private final UserDao userDao;
    private final Mapping mapping;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
        var user = userDao.findByEmail(signIn.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var generatedToken = jwtService.generateToken(user);
        return JWTAuthResponse.builder().token(generatedToken).build();
    }

    @Override
    public JWTAuthResponse signUp(UserDTO userDTO) {
      //Save user
        UserEntity savedUser = userDao.save(mapping.toUserEntity(userDTO));
      //Generate the token and return it
        var generatedToken = jwtService.generateToken(savedUser);
       return JWTAuthResponse.builder().token(generatedToken).build();
    }

    @Override
    public JWTAuthResponse refreshToken(String accessToken) {
        //extract user name
        var userName = jwtService.extractUserName(accessToken);
        //check the user availability in the DB
        var findUser = userDao.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var refreshToken = jwtService.refreshToken(findUser);
        return JWTAuthResponse.builder().token(refreshToken).build();
    }
}
