-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-01-2023 a las 07:16:24
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestioncursos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE `administrador` (
  `id_administrador` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE `alumno` (
  `id` int(11) NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`id`, `apellidos`, `email`, `nombre`, `password`, `id_usuario`) VALUES
(14, 'Mateo', 'davidmateo@gmail.com', 'David ', '$2a$10$82zb2KJLg4F9F1QJSs5O.usWm.r8SZF3u7gjQjo20I.aH.Eh5Vb8e', 13),
(20, 'Rondan', 'martarondan@gmail.com', 'Marta', '$2a$10$gRIRuZXvFaVjtornmla5gOuGotLQbRHfJMHLHW5CbHzkqfqV/..Ya', 19),
(22, 'Rojas', 'rubenrojas@gmail.com', 'Ruben', '$2a$10$1BjoNbJVS7d5uzcV7B9K..vIKUDmyxW./KEA5J5u3iEA3MymMGuoK', 21);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentario`
--

CREATE TABLE `comentario` (
  `id` int(11) NOT NULL,
  `comentario` varchar(255) DEFAULT NULL,
  `alumno_id` int(11) DEFAULT NULL,
  `curso_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso`
--

CREATE TABLE `curso` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_fin` varchar(255) DEFAULT NULL,
  `fecha_inicio` varchar(255) DEFAULT NULL,
  `nivel` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `id_profesor` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `curso`
--

INSERT INTO `curso` (`id`, `descripcion`, `fecha_fin`, `fecha_inicio`, `nivel`, `nombre`, `id_profesor`) VALUES
(7, 'Asignatura 2º Dam', '2023-03-03', '2022-09-16', 8, 'Acceso a datos', 4),
(8, 'Asignatura 2º Dam', '2023-03-03', '2022-09-16', 6, 'Desarrollo de interfaces', 6),
(16, 'Asignatura 1º Dam', '2022-12-30', '2022-10-03', 4, 'Base de datos', 6),
(17, 'Asignatura 1º Dam', '2023-01-09', '2022-09-05', 9, 'Programación', 4),
(18, 'Curso de programación con Django', '2023-09-21', '2023-04-12', 10, 'Programación Django ', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(24);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matricula`
--

CREATE TABLE `matricula` (
  `id` int(11) NOT NULL,
  `valoracion` int(11) NOT NULL,
  `alumno_id` int(11) DEFAULT NULL,
  `curso_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `matricula`
--

INSERT INTO `matricula` (`id`, `valoracion`, `alumno_id`, `curso_id`) VALUES
(15, 0, 14, 7),
(23, 0, 20, 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `noticia`
--

CREATE TABLE `noticia` (
  `id` int(11) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `noticia`
--

INSERT INTO `noticia` (`id`, `descripcion`, `imagen`, `titulo`, `usuario_id`) VALUES
(12, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras consectetur nunc sed dui dignissim, eu pretium nisl pulvinar. Sed semper diam purus. Vestibulum luctus purus in odio euismod, non euismod massa iaculis. Donec vel urna vitae turpis tincidunt mollis tincidunt ornare mauris. Vestibulum posuere, orci rutrum ornare dapibus, metus diam fermentum leo, non luctus quam velit eu orci. Integer eu lorem sit amet ex malesuada elementum. Aliquam a lorem vitae metus rhoncus ullamcorper. ', 'Noticia 2.jpg', 'Noticia 2', 9),
(11, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras consectetur nunc sed dui dignissim, eu pretium nisl pulvinar. Sed semper diam purus. Vestibulum luctus purus in odio euismod, non euismod massa iaculis. Donec vel urna vitae turpis tincidunt mollis tincidunt ornare mauris. Vestibulum posuere, orci rutrum ornare dapibus, metus diam fermentum leo, non luctus quam velit eu orci. Integer eu lorem sit amet ex malesuada elementum. Aliquam a lorem vitae metus rhoncus ullamcorper. ', 'Noticia 1.jpg', 'Noticia 1', 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor`
--

CREATE TABLE `profesor` (
  `id` int(11) NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `profesor`
--

INSERT INTO `profesor` (`id`, `apellidos`, `email`, `nombre`, `password`, `id_usuario`) VALUES
(4, 'Reyes', 'felixreyes@gmail.com', 'Felix', '$2a$10$o9SA9BeNVRbcbDDTolWXHuK2AdtP3SmXiWASNKrIehImd8KFc.a1y', 3),
(6, 'Reyes', 'raulreyes@gmail.com', 'Raul', '$2a$10$VRaUZX2n56sObFtoSF7BH.7ugq2mlY/qaQwVcx3Tf3kbrSKuhPPMq', 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `enabled`, `password`, `role`, `username`) VALUES
(9, b'0', '$2a$10$Mh1WmsWR98sQp2RUe2zW6ewyvz73d2X948pihNtYbz.hUOEC6N1xi', 'ROL_ADMIN', 'admin@admin.com'),
(3, b'0', '$2a$10$EVKnINi9VPgn8Alwj3wE4egwxo7sW5EPdQrWkZsCMN08e6Fi7Bg/a', 'ROL_PROFESOR', 'felixreyes@gmail.com'),
(5, b'0', '$2a$10$ziKB5JgR7e/zLqwKq.TeheNzEBOBMRILHkMscMdCZxcrScj25/e5S', 'ROL_PROFESOR', 'raulreyes@gmail.com'),
(13, b'0', '$2a$10$.gBfPRs7aPmr5qLNqUh4/OZOi30dlMaE5ZqUVVrer95mnYp/ktxky', 'ROL_ALUMNO', 'davidmateo@gmail.com'),
(19, b'0', '$2a$10$jO49QSRIYVWZSvf36plCe.cpVMzVZbuJe8AdO6F.0MDemsry03LM2', 'ROL_ALUMNO', 'martarondan@gmail.com'),
(21, b'0', '$2a$10$rAW.zXRFMhCq59lH1A932.Lz0HdZvKV0MgkuqjL5kShx79xNS25rO', 'ROL_ALUMNO', 'rubenrojas@gmail.com');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`id_administrador`);

--
-- Indices de la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3j6mgfljwrkb96hnya4lc1fhd` (`id_usuario`);

--
-- Indices de la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKncf6pqstxlk45tijdf0x8hvkn` (`alumno_id`),
  ADD KEY `FKt2gwugpetxkblw1wjcannhuvu` (`curso_id`);

--
-- Indices de la tabla `curso`
--
ALTER TABLE `curso`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKenvwioi79avb9nl1xnw8n3hao` (`id_profesor`);

--
-- Indices de la tabla `matricula`
--
ALTER TABLE `matricula`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKha7atjkqv0i3imutwn8mwfnei` (`alumno_id`),
  ADD KEY `FK133qjgbs681xntmnvxvg2g08w` (`curso_id`);

--
-- Indices de la tabla `noticia`
--
ALTER TABLE `noticia`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ims38crwfl7ma6ruyot9nrpts` (`titulo`) USING HASH,
  ADD KEY `FK51s0hrvyltinu4gignfdoen34` (`usuario_id`);

--
-- Indices de la tabla `profesor`
--
ALTER TABLE `profesor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbyhq45hsgy32alfydfpypqctp` (`id_usuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_863n1y3x0jalatoir4325ehal` (`username`) USING HASH;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
