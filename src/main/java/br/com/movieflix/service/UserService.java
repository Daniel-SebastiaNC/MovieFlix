package br.com.movieflix.service;

import br.com.movieflix.controller.request.UserRequest;
import br.com.movieflix.controller.response.UserRegisterResponse;
import br.com.movieflix.entity.User;
import br.com.movieflix.mapper.UserMapper;
import br.com.movieflix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserRegisterResponse register(UserRequest userRequest){
        User user = UserMapper.toUser(userRequest);
        User saveUser = userRepository.save(user);
        return UserMapper.toUserRegisterResponse(saveUser);
    }
}
