package com.techcareer.business.dto;


import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDTO implements Serializable {

    public static final Long serialVersionUID=1L;
    // ID
    private Long id;

    // CONTENT
    @NotEmpty(message = "Content cannot be empty.")
    @Lob
    private String content;

    // IS DONE
    private boolean done;

    // DATE
    @Builder.Default
    private Date systemCreatedDate = new Date(System.currentTimeMillis());
}
