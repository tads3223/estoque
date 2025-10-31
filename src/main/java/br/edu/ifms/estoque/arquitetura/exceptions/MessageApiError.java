/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.arquitetura.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author 1513003
 */
public class MessageApiError {

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Instant timestamp;
    private Integer code;
    private String status;
    private List<String> errors;

    public MessageApiError() {
    }

    public MessageApiError(Instant timestamp, Integer code, String status, List<String> errors) {
        this.timestamp = timestamp;
        this.code = code;
        this.status = status;
        this.errors = errors;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
