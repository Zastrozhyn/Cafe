-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3307
-- Время создания: Фев 03 2022 г., 21:46
-- Версия сервера: 5.7.33
-- Версия PHP: 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `epam-cafe`
--

-- --------------------------------------------------------

--
-- Структура таблицы `accounts`
--

CREATE TABLE `accounts` (
  `id` int(11) NOT NULL,
  `status` enum('common','silver','gold','platinum') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'common',
  `balance` decimal(10,2) NOT NULL DEFAULT '0.00',
  `active` enum('TRUE','FALSE') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'TRUE',
  `order_history` decimal(5,2) DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `accounts`
--

INSERT INTO `accounts` (`id`, `status`, `balance`, `active`, `order_history`) VALUES
(6, 'common', '19.76', 'TRUE', '97.60'),
(13, 'common', '79.10', 'TRUE', '10.50'),
(17, 'silver', '57.06', 'TRUE', '183.90'),
(19, 'silver', '23.33', 'TRUE', '233.30'),
(20, 'common', '4.03', 'FALSE', '40.30'),
(21, 'common', '0.00', 'TRUE', '0.00');

-- --------------------------------------------------------

--
-- Структура таблицы `menu`
--

CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL,
  `type` enum('drink','snack','desert') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'drink',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` decimal(7,2) NOT NULL,
  `weight` smallint(4) NOT NULL,
  `archive` enum('true','false') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'false'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `menu`
--

INSERT INTO `menu` (`menu_id`, `type`, `name`, `description`, `price`, `weight`, `archive`) VALUES
(1, 'drink', 'Cappuccino', '-', '4.30', 200, 'false'),
(2, 'drink', 'Espresso', '-', '3.00', 120, 'false'),
(3, 'drink', 'Americano', '-', '4.10', 200, 'false'),
(4, 'drink', 'Latte', '-', '6.00', 300, 'false'),
(5, 'drink', 'Irish Coffee', '-', '6.30', 250, 'false'),
(6, 'drink', 'Cappuccino XL', '-', '5.50', 300, 'false'),
(7, 'drink', 'Black tea', '-', '2.50', 300, 'false'),
(8, 'drink', 'Tea drink', 'melisa, rose hip, lemon', '4.00', 300, 'false'),
(9, 'snack', 'Hamburger', '-', '3.00', 200, 'false'),
(10, 'snack', 'Cheeseburger', '-', '3.50', 200, 'false'),
(11, 'snack', 'Double cheeseburger', '-', '4.00', 250, 'false'),
(12, 'snack', 'Mini pizzas', 'cheese, bekon, mashroom', '4.00', 250, 'false'),
(13, 'snack', 'Egg Salad Sandwich', '-', '5.00', 200, 'false'),
(14, 'snack', 'Turkey Sandwich', '-', '6.00', 250, 'false'),
(15, 'snack', 'Green Salad', '-', '5.00', 200, 'false'),
(16, 'snack', 'Hot Dog', '', '4.20', 250, 'false'),
(17, 'snack', 'Cheese&becon Roll', '-', '8.00', 350, 'false'),
(18, 'desert', 'Chocolate Muffin', '-', '2.50', 150, 'false'),
(19, 'desert', 'Cheese cake', '-', '5.00', 200, 'false'),
(20, 'desert', 'Chocolate brownie', 'warm chocolate brownie,\r\npistachio crumb, clotted cream ice cream', '7.50', 200, 'false'),
(21, 'desert', 'Bread and butter puddinG', 'apricot and sultana bread and butter pudding\r\nwith ice cream or custard', '8.00', 200, 'false'),
(22, 'snack', 'Cheese\r\n', 'stilton, brie and cheddar, celery and grapes,\r\nchutney and cheese biscuits', '9.50', 150, 'false'),
(23, 'desert', 'Donuts', '-', '4.00', 200, 'false'),
(24, 'snack', 'Omelette', '-', '5.00', 250, 'false'),
(25, 'snack', 'Bacon omelet', '', '8.00', 350, 'false'),
(27, 'drink', 'Pineapple juice', '', '2.20', 250, 'false'),
(28, 'snack', 'Chips', '', '4.00', 100, 'false'),
(29, 'drink', 'Green tea', '-', '2.00', 250, 'false'),
(31, 'drink', 'Apple juice', '-', '3.00', 250, 'false'),
(32, 'desert', 'Muffin', '-', '2.20', 150, 'false');

-- --------------------------------------------------------

--
-- Структура таблицы `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `user_id` varchar(11) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '...',
  `comment` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `payment_type` enum('cash','credit_card','account') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'cash',
  `paid` enum('true','false') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'false'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `orders`
--

INSERT INTO `orders` (`order_id`, `user_id`, `description`, `comment`, `date`, `time`, `payment_type`, `paid`) VALUES
(16, 'byrak', '-', NULL, '2022-01-25', '17:53:00', 'cash', 'true'),
(17, 'byrak', '-', 'very good and tasty', '2022-01-25', '15:52:00', 'cash', 'true'),
(18, 'byrak', '-', NULL, '2022-01-25', '17:57:00', 'cash', 'true'),
(19, 'byrak', 'good', NULL, '2022-01-25', '16:00:00', 'account', 'true'),
(20, 'byrasan', '-', NULL, '2022-01-29', '19:01:00', 'credit_card', 'true'),
(21, 'Homik', '-', 'very good', '2022-01-30', '12:50:00', 'cash', 'true'),
(22, 'Homik', '-', NULL, '2022-01-30', '12:50:00', 'credit_card', 'true'),
(23, 'Admin', '-', NULL, '2022-01-30', '15:57:00', 'account', 'true'),
(24, 'byrasan', 'good', NULL, '2022-02-01', '18:12:00', 'cash', 'true'),
(25, 'byrak', '-', NULL, '2022-02-02', '22:35:00', 'cash', 'true'),
(26, 'pupkin', 'good', NULL, '2022-02-03', '15:20:00', 'cash', 'true'),
(27, 'pupkin', '-', NULL, '2022-02-03', '16:23:00', 'credit_card', 'true'),
(28, 'pupkin', '-', NULL, '2022-02-03', '15:22:00', 'cash', 'true');

-- --------------------------------------------------------

--
-- Структура таблицы `order_dishes`
--

CREATE TABLE `order_dishes` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `order_dishes`
--

INSERT INTO `order_dishes` (`id`, `order_id`, `menu_id`) VALUES
(21, 16, 4),
(22, 16, 8),
(23, 16, 13),
(24, 16, 14),
(25, 16, 25),
(26, 16, 17),
(27, 16, 20),
(28, 16, 21),
(29, 16, 4),
(30, 17, 23),
(31, 17, 8),
(32, 17, 4),
(33, 17, 14),
(34, 18, 8),
(35, 18, 14),
(36, 18, 15),
(37, 18, 22),
(38, 18, 17),
(39, 18, 25),
(40, 18, 20),
(41, 18, 21),
(42, 18, 4),
(43, 18, 3),
(44, 18, 4),
(45, 19, 1),
(46, 19, 4),
(47, 20, 9),
(48, 20, 24),
(49, 20, 23),
(50, 20, 4),
(51, 21, 13),
(52, 21, 23),
(53, 21, 1),
(54, 21, 21),
(55, 21, 25),
(56, 21, 27),
(57, 22, 14),
(58, 22, 22),
(59, 22, 28),
(60, 23, 1),
(61, 23, 27),
(62, 23, 23),
(63, 24, 19),
(64, 24, 20),
(65, 24, 2),
(66, 25, 19),
(67, 25, 20),
(68, 25, 16),
(69, 25, 25),
(70, 26, 5),
(71, 26, 4),
(72, 26, 6),
(73, 26, 7),
(74, 26, 4),
(75, 26, 11),
(76, 26, 12),
(77, 26, 13),
(78, 26, 14),
(79, 26, 15),
(80, 26, 14),
(81, 26, 12),
(82, 26, 20),
(83, 26, 21),
(84, 26, 23),
(85, 26, 32),
(86, 26, 20),
(87, 26, 19),
(88, 26, 18),
(89, 26, 21),
(90, 26, 23),
(91, 27, 1),
(92, 27, 2),
(93, 27, 4),
(94, 27, 5),
(95, 27, 7),
(96, 27, 5),
(97, 27, 3),
(98, 27, 3),
(99, 27, 3),
(100, 27, 5),
(101, 27, 6),
(102, 27, 3),
(103, 27, 2),
(104, 27, 7),
(105, 27, 15),
(106, 27, 12),
(107, 27, 10),
(108, 27, 13),
(109, 27, 14),
(110, 27, 15),
(111, 27, 12),
(112, 27, 18),
(113, 27, 21),
(114, 27, 21),
(115, 27, 21),
(116, 27, 23),
(117, 27, 23),
(118, 27, 32),
(119, 27, 32),
(120, 27, 18),
(121, 27, 18),
(122, 27, 20),
(123, 27, 21),
(124, 27, 23),
(125, 27, 23),
(126, 27, 23),
(127, 27, 21),
(128, 27, 21),
(129, 27, 19),
(130, 27, 19),
(131, 28, 2),
(132, 28, 3),
(133, 28, 5),
(134, 28, 7),
(135, 28, 6),
(136, 28, 4),
(137, 28, 1),
(138, 28, 4),
(139, 28, 5),
(140, 28, 6),
(141, 28, 7),
(142, 28, 3),
(143, 28, 1),
(144, 28, 1),
(145, 28, 4),
(146, 28, 12),
(147, 28, 9),
(148, 28, 14),
(149, 28, 15),
(150, 28, 15),
(151, 28, 12),
(152, 28, 11),
(153, 28, 2),
(154, 28, 6),
(155, 28, 19),
(156, 28, 21),
(157, 28, 23),
(158, 28, 23),
(159, 28, 21),
(160, 28, 19),
(161, 28, 19),
(162, 28, 21),
(163, 28, 23),
(164, 28, 32),
(165, 28, 32),
(166, 28, 23),
(167, 28, 21),
(168, 28, 18),
(169, 28, 21),
(170, 28, 21),
(171, 28, 32),
(172, 28, 32),
(173, 28, 32),
(174, 28, 23),
(175, 28, 21),
(176, 28, 21),
(177, 28, 21),
(178, 28, 21),
(179, 28, 21),
(180, 28, 21),
(181, 28, 21),
(182, 28, 21),
(183, 28, 21),
(184, 28, 21),
(185, 28, 21),
(186, 28, 21),
(187, 28, 21),
(188, 28, 21),
(189, 28, 21),
(190, 28, 21),
(191, 28, 21),
(192, 28, 21),
(193, 28, 21),
(194, 28, 21),
(195, 28, 21),
(196, 28, 21),
(197, 28, 21),
(198, 28, 21),
(199, 28, 21),
(200, 28, 21),
(201, 28, 21),
(202, 28, 21),
(203, 28, 21),
(204, 28, 21),
(205, 28, 21);

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `name` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_name` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `login` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '-',
  `account_id` int(11) NOT NULL,
  `role` enum('client','admin') COLLATE utf8mb4_unicode_ci DEFAULT 'client',
  `archive` enum('true','false') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'false'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`user_id`, `name`, `last_name`, `phone`, `email`, `login`, `password`, `account_id`, `role`, `archive`) VALUES
(6, 'Svetlana', 'Pozika', '+375292223331', 'hom@tut.by', 'Homik', '$2a$10$V3nQDaUJkaolH.Fu6Lj/o.y/uD26ZSK3nTchXm4Zu4U3D.um2Z2gq', 6, 'client', 'false'),
(11, 'Alexandr', 'Zastrozhyn', '80291234567', 'zastr@tut.by', 'Admin', '$2a$10$NWxqzi1OasMzXDk1wyJEE.xWZjIegddZHfqLxMdklfiBw35qoqro6', 13, 'admin', 'false'),
(13, 'Alexey', 'Byrakov', '7789821', 'byr@tut.by', 'byrak', '$2a$10$Bl1YhIYsQy8ld3cfIWYmd..Kxsko8EBuzJ6ql1C9pUn6/lT9QraKS', 17, 'client', 'false'),
(15, 'Vasya', 'Pupkin', '2223331', 'pupkin@tut.by', 'pupkin', '$2a$10$diCxWqq8UnaA7uxSgYLItePP3SbJZ1J6u4J0BFvHgpwp8B6F/8qe2', 19, 'client', 'false'),
(16, 'Andrey', 'Byrakov', '1114215', 'vishki1@mail.ru', 'byrasan', '$2a$10$dlILQ6EsrDCCCGPievsze.HQVRJrJVL2.NdOh1HAJLiTClP/m279O', 20, 'client', 'false');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`menu_id`);

--
-- Индексы таблицы `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Индексы таблицы `order_dishes`
--
ALTER TABLE `order_dishes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `menu_article` (`menu_id`),
  ADD KEY `order_id` (`order_id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `login` (`login`),
  ADD UNIQUE KEY `phone` (`phone`),
  ADD UNIQUE KEY `account_id` (`account_id`) USING BTREE;

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `accounts`
--
ALTER TABLE `accounts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT для таблицы `menu`
--
ALTER TABLE `menu`
  MODIFY `menu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT для таблицы `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT для таблицы `order_dishes`
--
ALTER TABLE `order_dishes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=206;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`login`);

--
-- Ограничения внешнего ключа таблицы `order_dishes`
--
ALTER TABLE `order_dishes`
  ADD CONSTRAINT `order_dishes_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `order_dishes_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`);

--
-- Ограничения внешнего ключа таблицы `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
