package br.com.movieflix.mapper;

import br.com.movieflix.controller.request.UserRequest;
import br.com.movieflix.controller.response.UserRegisterResponse;
import br.com.movieflix.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public static User toUser(UserRequest userRequest){
        return User
                .builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .password(userRequest.password())
                .build();
    }

    public static UserRegisterResponse toUserRegisterResponse(User user){
        return UserRegisterResponse
                .builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
