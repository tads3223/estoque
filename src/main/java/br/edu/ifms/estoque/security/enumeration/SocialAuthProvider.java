/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.edu.ifms.estoque.security.enumeration;

/**
 *
 * @author 1513003
 */
public enum SocialAuthProvider {
    // Os nomes dos ENUMs DEVEM ser o mesmo que o registrationId configurado no application.yml
    GOOGLE, 
    GITHUB,
    FACEBOOK,
    
    // Caso o usuário se registre via formulário tradicional
    LOCAL; 
    
    /**
     * Método utilitário para converter a String do Spring para o Enum.
     * @param registrationId A string de identificação do provedor (e.g., "google").
     * @return O Enum AuthProvider correspondente.
     * @throws IllegalArgumentException se o provedor não for suportado.
     */
    public static SocialAuthProvider fromRegistrationId(String registrationId) {
        if (registrationId == null || registrationId.isEmpty()) {
            return LOCAL; // Se não houver ID de registro, é um login local (opcional)
        }
        
        try {
            // Converte a string para maiúsculas para corresponder aos nomes do Enum
            return SocialAuthProvider.valueOf(registrationId.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Provedor de autenticação social não suportado: " + registrationId);
        }
    }
}
