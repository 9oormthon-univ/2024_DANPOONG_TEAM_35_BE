package app.jasople.security;


import app.jasople.Config.Exception.handler.UserHandler;
import app.jasople.Config.code.status.ErrorStatus;
import app.jasople.User.entity.User;
import app.jasople.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userPk)  {

        User user = userRepository.findById(Long.parseLong(userPk))
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
        return new CustomUserDetail(user);	// 위에서 생성한 CustomUserDetails Class
    }
}

