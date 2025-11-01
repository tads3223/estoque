/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.security.adapter;
// Use um HashMap simples ou Redis se estiver disponível

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

/**
 * Simula um repositório para tokens inválidos (melhor usar Redis em produção)
 *
 * @author 1513003
 */
@Repository
public class TokenBlacklistRepository {

    // Mapa: Token (hash ou ID) -> Data de Expiração
    private final Map<String, Instant> blacklist = new ConcurrentHashMap<>();

    public void blacklistToken(String tokenHash, Instant expirationTime) {
        blacklist.put(tokenHash, expirationTime);
    }

    // Verifica se o token está na lista negra e se ainda está válido (não expirou)
    public boolean isBlacklisted(String tokenHash) {
        // Limpa tokens expirados (manutenção básica)
        blacklist.entrySet().removeIf(entry -> entry.getValue().isBefore(Instant.now()));

        return blacklist.containsKey(tokenHash);
    }
}
