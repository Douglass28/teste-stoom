package br.com.stoom.store.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exception {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String msg;
    private String path;
}
