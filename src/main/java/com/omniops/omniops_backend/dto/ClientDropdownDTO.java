package com.omniops.omniops_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientDropdownDTO {

    private Integer userId;

    private String fullName;

}