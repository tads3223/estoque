/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package br.edu.ifms.estoque.security.dto;

/**
 *
 * @author 1513003
 */
public record ResetPasswordRequest(String token, String newPassword) {}