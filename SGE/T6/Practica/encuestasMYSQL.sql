-- phpMyAdmin SQL Dump
-- version 2.11.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 04-11-2007 a las 17:58:47
-- Versión del servidor: 5.0.45
-- Versión de PHP: 5.2.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Base de datos: `encuestas`
--
CREATE DATABASE `encuestas` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci;
USE `encuestas`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `encuesta`
--

CREATE TABLE IF NOT EXISTS `encuesta` (
  `idEncuesta` bigint(10) unsigned NOT NULL auto_increment,
  `textoPregunta` varchar(255) collate latin1_spanish_ci NOT NULL,
  PRIMARY KEY  (`idEncuesta`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=4 ;

--
-- Volcar la base de datos para la tabla `encuesta`
--

INSERT INTO `encuesta` (`idEncuesta`, `textoPregunta`) VALUES
(1, '¿Cuál es el sistema operativo usado en los ordenadores Macintosh?'),
(2, '¿Cuál es el SGBD usado en un sistema WAMP?'),
(3, '¿Cuál es la herramienta con interfaz web que nos permite gestionar MySQL?');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `respuesta`
--

CREATE TABLE IF NOT EXISTS `respuesta` (
  `idRespuesta` bigint(10) unsigned NOT NULL auto_increment,
  `idEncuesta` bigint(10) unsigned NOT NULL,
  `textoRespuesta` varchar(255) collate latin1_spanish_ci NOT NULL,
  `numeroRespuestas` bigint(10) unsigned default '0',
  PRIMARY KEY  (`idRespuesta`),
  KEY `idEncuesta` (`idEncuesta`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=13 ;

--
-- Volcar la base de datos para la tabla `respuesta`
--

INSERT INTO `respuesta` (`idRespuesta`, `idEncuesta`, `textoRespuesta`, `numeroRespuestas`) VALUES
(1, 1, 'Mac OS X', 14),
(2, 1, 'Linux', 6),
(3, 1, 'Windows XP', 5),
(4, 1, 'MS-DOS', 1),
(5, 2, 'Oracle', 4),
(6, 2, 'MySQL', 20),
(7, 2, 'Informix', 3),
(8, 2, 'Access', 1),
(9, 3, 'Maguma', 3),
(10, 3, 'Web Developer', 3),
(11, 3, 'phpMyAdmin', 18),
(12, 3, 'MySQLAdmin', 7);

-- --------------------------------------------------------

--
-- Filtros para las tablas descargadas (dump)
--

--
-- Filtros para la tabla `respuesta`
--
ALTER TABLE `respuesta`
  ADD CONSTRAINT `respuesta_ibfk_1` FOREIGN KEY (`idEncuesta`) REFERENCES `encuesta` (`idEncuesta`) ON DELETE CASCADE ON UPDATE CASCADE;