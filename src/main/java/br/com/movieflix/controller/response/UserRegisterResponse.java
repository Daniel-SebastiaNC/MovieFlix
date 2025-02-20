package br.com.movieflix.controller.response;

import lombok.Builder;

@Builder
public record UserRegisterResponse(Long id,
                                   String name,
                                   String email) {
}
