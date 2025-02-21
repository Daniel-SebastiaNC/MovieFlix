package br.com.movieflix.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record CategoryRequest(@NotEmpty(message = "Nome obrigatorio") String name) {
}
