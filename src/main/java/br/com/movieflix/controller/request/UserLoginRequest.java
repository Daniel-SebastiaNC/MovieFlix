package br.com.movieflix.controller.request;

public record UserLoginRequest(String email,
                               String password) {
}
