package com.dataacesso.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserCreateDTO {

    @NotBlank(message = "O NOME deve ser informado!")
    @Size(min = 3, message = "O nome deve ter no minimo 2 caracteres!")
    private String name;

    @NotEmpty(message = "A SENHA deve ser informado!")
    @Size(min = 8, max = 24, message = "A SENHA deve ter no minimo 8 caracteres e no maximo 24!")
    private String password;


    public String getName() {
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
