SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";



CREATE TABLE `subject` (
  `id_` int(11) NOT NULL,
  `title_` varchar(256) NOT NULL,
  `timeStart_` int(11) NOT NULL,
  `timeEnd_` int(11) NOT NULL,
  `weekDay_` int(11) NOT NULL,
  `teacher_` varchar(256),
  `type_` varchar(256),
  `classRoom_` varchar(256),
  `university_id_` int(11) NOT NULL,
  `faculty_id_` int(11) NOT NULL,
  `group_id_` int(11) NOT NULL,
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `University` (
  `Univ_ID` int(11) NOT NULL,
  `Univ_Name` text NOT NULL,
  `Rating` int(11) NOT NULL,
  `City` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `subject` (`id_`, `title`, `timeStart_`, `timeEnd_`, `weekDay_`, `teacher_`, `type_`, `classRoom_`, `university_id_`, `faculty_id_`, `group_id_`) VALUES
(1, 'Программирование', '9:45', '11:20', 1, 'Иванов А.А.', 'Лекция', '404п', 1, 1, 1),
(2, 'ООАиП', '13:25', '15:00', 2, 'Ивановский А.А.', 'Практика', '505', 2, 2, 2);



ALTER TABLE `Subject`
  ADD PRIMARY KEY (`id_`);



