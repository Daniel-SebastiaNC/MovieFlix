package br.com.movieflix.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record StreamingRequest(@NotEmpty(message = "Nome obrigatorio") String name) {
}
