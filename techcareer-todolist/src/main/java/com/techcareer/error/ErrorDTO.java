package com.techcareer.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
// LOMBOK
@Data
@Builder
@AllArgsConstructor
public class ErrorDTO {
    private Date timestamp;
    private int status;
    private String path;
    private String message ;
}
