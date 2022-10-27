package com.codegym.dating.dto;

import com.codegym.dating.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {
    private Integer idInvoice;
    @NotBlank
    @Pattern(regexp = "^\\d+$")
    private String price;
    private LocalDate time;
    private User user;
}
