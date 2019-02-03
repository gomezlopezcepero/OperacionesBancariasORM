-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-03-2018 a las 02:25:29
-- Versión del servidor: 10.1.28-MariaDB
-- Versión de PHP: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `banco`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas_bancarias`
--

CREATE TABLE `cuentas_bancarias` (
  `ID_Cuenta_Bancaria` int(11) NOT NULL,
  `Numero_Cuenta` varchar(50) NOT NULL,
  `Propietario` varchar(50) NOT NULL,
  `Saldo` int(50) NOT NULL,
  `DNI` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cuentas_bancarias`
--

INSERT INTO `cuentas_bancarias` (`ID_Cuenta_Bancaria`, `Numero_Cuenta`, `Propietario`, `Saldo`, `DNI`) VALUES
(1, '1111-2222-3333-4444', 'Paco', -100, '32077430C'),
(2, '2222-3333-4444-5555', 'Pasqkpo', 100, '32077430A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial`
--

CREATE TABLE `historial` (
  `ID_Historial` int(11) NOT NULL,
  `Tipo_Evento` varchar(10) NOT NULL,
  `Fecha_Hora` varchar(50) NOT NULL,
  `Numero_Cuenta` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `historial`
--

INSERT INTO `historial` (`ID_Historial`, `Tipo_Evento`, `Fecha_Hora`, `Numero_Cuenta`) VALUES
(95, 'L', '2018/03/14 02:24:01', '1111-2222-3333-4444'),
(96, 'I', '2018/03/14 02:24:08', '1111-2222-3333-4444'),
(97, 'E', '2018/03/14 02:24:16', '1111-2222-3333-4444'),
(98, 'R', '2018/03/14 02:24:16', '1111-2222-3333-4444');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `operaciones`
--

CREATE TABLE `operaciones` (
  `ID_Operacion` int(11) NOT NULL,
  `Fecha_hora` varchar(200) NOT NULL,
  `Num_Cuenta` varchar(50) NOT NULL,
  `Tipo_Operacion` varchar(50) NOT NULL,
  `Cantidad` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `operaciones`
--

INSERT INTO `operaciones` (`ID_Operacion`, `Fecha_hora`, `Num_Cuenta`, `Tipo_Operacion`, `Cantidad`) VALUES
(59, '2018/03/14 02:24:08', '1111-2222-3333-4444', 'i', 100),
(60, '2018/03/14 02:24:16', '1111-2222-3333-4444', 'e', 400);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `propietarios`
--

CREATE TABLE `propietarios` (
  `ID_Propietario` int(11) NOT NULL,
  `DNI` varchar(10) NOT NULL,
  `Usuario` varchar(50) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Primer_Apellido` varchar(50) NOT NULL,
  `Segundo_Apellido` varchar(50) NOT NULL,
  `Numero_Secreto` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `propietarios`
--

INSERT INTO `propietarios` (`ID_Propietario`, `DNI`, `Usuario`, `Nombre`, `Primer_Apellido`, `Segundo_Apellido`, `Numero_Secreto`) VALUES
(1, '32077430C', 'Paco', 'roberto', 'duenas', 'daniro', '9999'),
(2, '32077430A', 'Pasqkpo', 'pascu', 'capo', 'capero', '1234');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cuentas_bancarias`
--
ALTER TABLE `cuentas_bancarias`
  ADD PRIMARY KEY (`ID_Cuenta_Bancaria`),
  ADD UNIQUE KEY `DNI` (`DNI`),
  ADD UNIQUE KEY `Numero_Cuenta` (`Numero_Cuenta`);

--
-- Indices de la tabla `historial`
--
ALTER TABLE `historial`
  ADD PRIMARY KEY (`ID_Historial`);

--
-- Indices de la tabla `operaciones`
--
ALTER TABLE `operaciones`
  ADD PRIMARY KEY (`ID_Operacion`);

--
-- Indices de la tabla `propietarios`
--
ALTER TABLE `propietarios`
  ADD PRIMARY KEY (`ID_Propietario`),
  ADD UNIQUE KEY `DNI` (`DNI`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cuentas_bancarias`
--
ALTER TABLE `cuentas_bancarias`
  MODIFY `ID_Cuenta_Bancaria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `historial`
--
ALTER TABLE `historial`
  MODIFY `ID_Historial` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;

--
-- AUTO_INCREMENT de la tabla `operaciones`
--
ALTER TABLE `operaciones`
  MODIFY `ID_Operacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT de la tabla `propietarios`
--
ALTER TABLE `propietarios`
  MODIFY `ID_Propietario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
