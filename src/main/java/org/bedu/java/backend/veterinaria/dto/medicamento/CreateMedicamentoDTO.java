package org.bedu.java.backend.veterinaria.dto.medicamento;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
public class CreateMedicamentoDTO {

    @Schema(description = "Nombre del medicamento", example = "Doxiciclina")
    @NotBlank(message = "El nombre del medicamento es obligatorio")
    @Length(min = 1, max = 100, message = "El nombre del medicamento no puede exceder de 100 caracteres")
    private String nombre;

    @Schema(description = "Clasificación o tipo de medicamento", example = "Antibióticos")
    @NotBlank(message = "La clasificación del medicamento es obligatoria")
    @Length(min = 1, max = 100, message = "El nombre no puede exceder de 75 caracteres")
    private String clasificacion;

    @Schema(description = "Descripción del medicamento", example = "Útil para tratar enfermedades transmitidas por garrapatas y enfermedades respiratorias.")
    @NotBlank(message ="La descripción del medicamento es obligatoria")
    private String descripcion;

    @Schema(description = "Fecha en la que caduca el medicamento", example = "2025-01-01")
    @NotNull(message = "La fecha de caducidad del medicamento no puede ser nula") //Verificar
    @DateTimeFormat(pattern = "yyyy-MM-dd") //Verificar
    private Date fechaCaducidad;

    @Schema(description = "Cantidad de medicamentos en existencia", example = "100")
    @Min(value = 1, message = "La cantidad o existencia del medicamento debe ser mayor o igual que 1")
    private int existencia;

    @Schema(description = "Precio del medicamento", example = "125.50")
    @DecimalMin(value = "0.0", message = "El precio del medicamento debe ser mayor o igual que 0.0")
    private float precio;

    @Schema(description = "Instrucciones de uso del medicamento", example = "Por lo general, se administra de 2 a 5 mg por kg de peso corporal cada 12 o 24 horas.")
    @NotBlank(message = "Las instrucciones de uso del medicamento son obligatorias")
    private String instruccionesUso;
}