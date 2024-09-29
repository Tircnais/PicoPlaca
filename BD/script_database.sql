CREATE DATABASE PicoPlaca;
USE PicoPlaca;

-- Tabla: Vehículos
CREATE TABLE Vehículos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(10) NOT NULL UNIQUE
);

-- Tabla: Restricciones
CREATE TABLE Restricciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dia VARCHAR(20) NOT NULL,
    ultimo_digito CHAR(1) NOT NULL
);

-- Tabla: HorariosRestriccion
CREATE TABLE HorariosRestriccion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(20) NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL
);

-- Insertar restricciones de Pico y Placa
INSERT INTO Restricciones (dia, ultimo_digito) VALUES
('lunes', '1'),
('lunes', '2'),
('martes', '3'),
('martes', '4'),
('miércoles', '5'),
('miércoles', '6'),
('jueves', '7'),
('jueves', '8'),
('viernes', '9'),
('viernes', '0');

-- Insertar horarios de restricción
INSERT INTO HorariosRestriccion (tipo, hora_inicio, hora_fin) VALUES
('mañana', '07:00:00', '09:30:00'),
('tarde', '16:00:00', '21:00:00');

INSERT INTO Vehículos (placa) VALUES ('ABC1234'), ('DEF5678'), ('GHI9012'), ('JKL3456'), ('MNO7890'), ('PQR2345'), ('STU6789'), ('VWX0123'), ('YZA4567'), ('BCD8901');

SELECT * FROM HorariosRestriccion;
