package app.jasople.User.service;

import app.jasople.User.dto.UserDtoReq;
import app.jasople.User.entity.User;
import app.jasople.User.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public User signup(UserDtoReq.SignUpReq signUpDto) {

        User user = User.builder()
                .email(signUpDto.getEmail())
                .password(signUpDto.getPassword())
                .receiveAds(signUpDto.getReceiveAds())
                .build();

        //비밀번호 암호화 필요함

        userRepository.save(user);

        return user;
    }
}
