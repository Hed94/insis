-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Počítač: 127.0.0.1
-- Vytvořeno: Pát 05. čen 2020, 23:11
-- Verze serveru: 10.4.8-MariaDB
-- Verze PHP: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáze: `insis`
--

-- --------------------------------------------------------

--
-- Struktura tabulky `prihlasenezkousky`
--

CREATE TABLE `prihlasenezkousky` (
  `ID_zkousky` int(11) NOT NULL,
  `ID_uzivatele` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `prihlasenezkousky`
--

INSERT INTO `prihlasenezkousky` (`ID_zkousky`, `ID_uzivatele`) VALUES
(1, 2),
(1, 3);

-- --------------------------------------------------------

--
-- Struktura tabulky `uzivatel`
--

CREATE TABLE `uzivatel` (
  `ID` int(11) NOT NULL,
  `username` varchar(200) COLLATE utf8_czech_ci NOT NULL,
  `heslo` varchar(200) COLLATE utf8_czech_ci NOT NULL,
  `jmeno` varchar(200) COLLATE utf8_czech_ci NOT NULL,
  `prijmeni` varchar(200) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniEmail` varchar(200) COLLATE utf8_czech_ci NOT NULL,
  `datumNarozeni` date NOT NULL,
  `student` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `uzivatel`
--

INSERT INTO `uzivatel` (`ID`, `username`, `heslo`, `jmeno`, `prijmeni`, `kontaktniEmail`, `datumNarozeni`, `student`) VALUES
(1, 'admin', 'admin', 'Admin', 'Adminovič', 'admin@vse.cz', '1990-10-16', 1),
(2, 'student01', 'student', 'Student', 'Studentovič', 'student@vse.cz', '1998-03-22', 0),
(3, 'student02', 'student', 'Petr', 'Novák', 'student02@vse.cz', '2001-03-02', 0);

-- --------------------------------------------------------

--
-- Struktura tabulky `zkouska`
--

CREATE TABLE `zkouska` (
  `ID` int(11) NOT NULL,
  `predmet` varchar(200) COLLATE utf8_czech_ci NOT NULL,
  `datum` date NOT NULL,
  `semestr` varchar(200) COLLATE utf8_czech_ci NOT NULL,
  `kapacita` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `zkouska`
--

INSERT INTO `zkouska` (`ID`, `predmet`, `datum`, `semestr`, `kapacita`) VALUES
(1, '4IT218', '2020-06-14', 'LS2020', 25);

--
-- Klíče pro exportované tabulky
--

--
-- Klíče pro tabulku `prihlasenezkousky`
--
ALTER TABLE `prihlasenezkousky`
  ADD PRIMARY KEY (`ID_zkousky`,`ID_uzivatele`);

--
-- Klíče pro tabulku `uzivatel`
--
ALTER TABLE `uzivatel`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `prihlasovaciJmeno` (`username`);

--
-- Klíče pro tabulku `zkouska`
--
ALTER TABLE `zkouska`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT pro tabulky
--

--
-- AUTO_INCREMENT pro tabulku `uzivatel`
--
ALTER TABLE `uzivatel`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pro tabulku `zkouska`
--
ALTER TABLE `zkouska`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
