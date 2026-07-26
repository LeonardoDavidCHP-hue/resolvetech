--base de datos resolvetech - grupo 11

DROP DATABASE IF EXISTS bdresolvetech;

CREATE DATABASE bdresolvetech;

USE bdresolvetech;

CREATE TABLE categoria (
    id          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(50)  NOT NULL UNIQUE,
    descripcion VARCHAR(150) NULL);


CREATE TABLE estado (
    id          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(30)  NOT NULL UNIQUE,
    descripcion VARCHAR(150) NULL);


CREATE TABLE sede (
    id        BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(100) NOT NULL UNIQUE,
    direccion VARCHAR(200) NULL,
    activo    BOOLEAN NOT NULL DEFAULT TRUE);


CREATE TABLE usuario (
    id                   BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre               VARCHAR(80)  NOT NULL,
    password             VARCHAR(100) NOT NULL,
    rol                  VARCHAR(20)  NOT NULL,
    activo               BOOLEAN  NOT NULL DEFAULT TRUE,
    fecha_creacion       DATETIME DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CHECK (rol IN ('ADMIN','SOPORTE','CLIENTE')));

CREATE TABLE tecnico (
    id           BIGINT UNSIGNED PRIMARY KEY,
    especialidad VARCHAR(50) NOT NULL,
    telefono     VARCHAR(20) NULL,
    id_sede      BIGINT UNSIGNED NULL,
    FOREIGN KEY (id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (id_sede) REFERENCES sede(id));

CREATE TABLE ticket (
    id                   BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    numero_ticket        VARCHAR(20) NOT NULL UNIQUE,
    fecha_creacion       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_categoria         BIGINT UNSIGNED NOT NULL,
    id_sede              BIGINT UNSIGNED NOT NULL,
    descripcion          VARCHAR(300) NOT NULL,
    prioridad            VARCHAR(15)  NOT NULL,
    id_estado            BIGINT UNSIGNED NOT NULL,
    observacion_soporte  VARCHAR(300) NULL,
    fecha_atencion       DATETIME NULL,
    id_tecnico           BIGINT UNSIGNED NULL,
    id_usuario_creador   BIGINT UNSIGNED NOT NULL,
    version              BIGINT UNSIGNED NOT NULL DEFAULT 0,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id),
    FOREIGN KEY (id_sede) REFERENCES sede(id),
    FOREIGN KEY (id_estado) REFERENCES estado(id),
    FOREIGN KEY (id_tecnico) REFERENCES tecnico(id),
    FOREIGN KEY (id_usuario_creador) REFERENCES usuario(id),
    CHECK (prioridad IN ('Alta','Media','Baja')));


CREATE TABLE historial_ticket (
    id           BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    ticket_id    BIGINT UNSIGNED NOT NULL,
    id_estado    BIGINT UNSIGNED NOT NULL,
    fecha_cambio DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    observacion  VARCHAR(300) NULL,
    id_usuario   BIGINT UNSIGNED NULL,
    FOREIGN KEY (ticket_id) REFERENCES ticket(id) ON DELETE CASCADE,
    FOREIGN KEY (id_estado) REFERENCES estado(id),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id));


INSERT INTO categoria (nombre, descripcion) VALUES
('Redes',     'Problemas de conectividad e internet'),
('Hardware',  'Fallas en equipos fisicos'),
('Software',  'Errores en aplicaciones y sistemas'),
('Impresion', 'Problemas con impresoras y escaneres'),
('Seguridad', 'Incidencias de acceso y contraseñas');

INSERT INTO estado (nombre, descripcion) VALUES
('Pendiente',   'Ticket registrado, sin atender'),
('En atencion', 'Tecnico trabajando en la incidencia'),
('Evaluado',    'Incidencia evaluada, pendiente de solucion'),
('Resuelto',    'Incidencia solucionada correctamente'),
('Anulado',     'Ticket cancelado o duplicado');

INSERT INTO sede (nombre, direccion) VALUES
('Sede Lima Norte',  'Av. Tupac Amaru 1234'),
('Sede San Isidro',  'Av. Camino Real 456'),
('Sede Miraflores',  'Av. Larco 789'),
('Sede Surco',       'Av. Primavera 321'),
('Sede Arequipa',    'Av. Ejercito 654');

INSERT INTO usuario (nombre, password, rol) VALUES
('admin',   'admin123', 'ADMIN'),
('bodoque','soporte123', 'SOPORTE'),
('Mario',   'soporte456', 'SOPORTE'),
('Anderson', 'soporte789', 'SOPORTE');

INSERT INTO tecnico (id, especialidad, telefono, id_sede) VALUES
(2, 'Redes y Conectividad', '977111222', 1),
(3, 'Hardware y Equipos', '989333444', 2),
(4, 'Software y Sistemas', '939555666', 3);

INSERT INTO ticket
(numero_ticket, fecha_creacion, id_categoria, id_sede, descripcion, prioridad, id_estado, id_tecnico, id_usuario_creador)
VALUES
('RT000001.26','2026-01-15 09:00:00', 1, 1, 'Sin conexion a internet en piso 2', 'Alta',  1, 2, 1),
('RT000002.26','2026-01-16 10:30:00', 2, 2, 'PC de caja no enciende', 'Alta',  2, 3, 1),
('RT000003.26','2026-01-17 08:45:00', 3, 3, 'Error al abrir el sistema de facturacion', 'Media', 4, 4, 1),
('RT000004.26','2026-01-18 11:20:00', 1, 4, 'Router sin señal en sala de reuniones', 'Baja',  1, 2, 1),
('RT000005.26','2026-01-19 07:55:00', 2, 5, 'Impresora no detectada en red', 'Media', 5, 3, 1);

INSERT INTO historial_ticket (ticket_id, id_estado, fecha_cambio, observacion, id_usuario) VALUES
(1, 1, '2026-01-15 09:00:00', 'Ticket registrado', 1),
(2, 1, '2026-01-16 10:30:00', 'Ticket registrado', 1),
(2, 2, '2026-01-16 11:00:00', 'Tecnico asignado al caso', 3),
(3, 1, '2026-01-17 08:45:00', 'Ticket registrado', 1),
(3, 2, '2026-01-17 09:10:00', 'Revisando instalacion', 4),
(3, 4, '2026-01-17 10:30:00', 'Se reinstalaron componentes', 4),
(4, 1, '2026-01-18 11:20:00', 'Ticket registrado', 1),
(5, 1, '2026-01-19 07:55:00', 'Ticket registrado', 1),
(5, 5, '2026-01-19 08:10:00', 'Incidencia resuelta por el area', 1);


SHOW TABLES;

SELECT * FROM categoria;
SELECT * FROM estado;
SELECT * FROM sede;
SELECT * FROM usuario;
SELECT * FROM tecnico;
SELECT * FROM ticket;
SELECT * FROM historial_ticket;

SELECT t.numero_ticket, t.fecha_creacion, c.nombre AS categoria, s.nombre AS sede,
e.nombre AS estado, t.prioridad, u.nombre AS tecnico_asignado
FROM ticket t
INNER JOIN categoria c ON c.id = t.id_categoria
INNER JOIN sede s ON s.id = t.id_sede
INNER JOIN estado e ON e.id = t.id_estado
LEFT JOIN usuario u ON u.id = t.id_tecnico;