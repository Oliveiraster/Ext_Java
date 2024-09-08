package com.dataacesso.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisterCreateDTO {

    @NotBlank(message = "O TagID deve ser informado!")
    private String tagID;

    @NotBlank(message = "O NOME deve ser informado!")
    private String name;

    @NotNull(message = "A DATA deve ser informada!")
    private String horario;

    public @NotBlank(message = "O TagID deve ser informado!") String getTagID() {
        return tagID;
    }

    public void setTagID(@NotBlank(message = "O TagID deve ser informado!") String tagID) {
        this.tagID = tagID;
    }

    public @NotBlank(message = "O NOME deve ser informado!") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "O NOME deve ser informado!") String name) {
        this.name = name;
    }

    public @NotNull(message = "A DATA deve ser informada!") String getHorario() {
        return horario;
    }

    public void setHorario(@NotBlank(message = "A DATA deve ser informado!") String horario) {
        this.horario = horario;
    }
}
