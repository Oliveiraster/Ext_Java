package com.dataacesso.api.dto.mapper;

import com.dataacesso.api.dto.RegisterCreateDTO;
import com.dataacesso.api.dto.RegisterResponseDTO;
import com.dataacesso.api.model.Register;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RegisterMapper {

    @Autowired
    private ModelMapper mapper;


    public Register toEntity(RegisterCreateDTO dto) {
        return mapper.map(dto, Register.class);
    }

    public RegisterResponseDTO toDTO(Register entity) {
        RegisterResponseDTO dto = mapper.map(entity, RegisterResponseDTO.class);
        String horario = formatDateString(entity.getHorario());
        dto.setHorario(horario);

        return dto;
    }

    public List<RegisterResponseDTO> toDTO(List<Register> registerList) {
        return registerList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    private String formatDateString(String dateString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("HHmmssddMMyyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        try {
            Date date = inputFormat.parse(dateString);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Ou lance uma exceção customizada
        }
    }
}