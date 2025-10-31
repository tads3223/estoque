/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  1513003
 * Created: 23 de jun. de 2025
 */

-- Dados de usuario
INSERT INTO perfil (id, nome) VALUES
(1, 'ROLE_ADMIN'),(2, 'ROLE_USUARIO');
ALTER SEQUENCE perfil_seq RESTART WITH 3;

INSERT INTO usuario (login, auth_provider, bloqueado, email, nome, status, senha) VALUES
('admin', 'LOCAL', false, 'admin@email.com', 'Administrador', 'ATIVO', '$2a$10$Z9c.3MlrnJ44YsiSW3JCWOlJPRrXUoA.I1c4DypDE4SzfCp1wj3MK');

INSERT INTO perfis_usuario (login_id, perfil_id) VALUES
('admin', 1);


-- INSERT INTO subgrupo
-- #Referência: https://listenx.com.br/blog/lista-de-produtos-para-montar-um-supermercado/#frios-e-produtos-refrigerados
INSERT INTO subgrupo_produto(id, nome, grupo_produto_id) VALUES
(1, 'Alimentos', null),
(2, 'Alimentos Congelados', null),
(3, 'Hortifrúti', null),
(4, 'Frios e produtos refrigerados', null),
(5, 'Bebidas', null),
(6, 'Padaria', null),
(7, 'Higiene Pessoal', null),
(8, 'Itens de limpeza', null),
(9, 'Arroz', 1),
(10, 'Azeite', 1),
(11, 'Café', 1),
(12, 'Chá', 1),
(13, 'Batata palito', 2),
(14, 'Frango', 2),
(15, 'Hambúrguer', 2),
(16, 'Lasanha', 2);

INSERT INTO joined_base(id, nome) VALUES
(1, 'Unidade'),
(2, 'Pacote'),
(3, 'Caixa'),
(4, 'Weimar Gonçalves Torres'),
(5, 'Campo Grande'),
(6, 'Dourados');

INSERT INTO unidade_medida (fracionado, id) VALUES
(false, 1),
(false, 2),
(false, 3);


INSERT INTO elemento_base (id, nome, elemento_type) values
(1, 'Zaeli', 'MARCA'),
(2, 'Naviraí', 'MARCA'),
(3, 'Frimeza', 'MARCA');

INSERT INTO tipo_logradouro(id, nome, sigla) values
(1, 'Avenida', 'Av'),
(2, 'Rua', 'R');
ALTER SEQUENCE table_per_class_base RESTART WITH 3;

INSERT INTO logradouro (id, tipo_logradouro_id) values
(4, 1),
(5, 1),
(6, 1);