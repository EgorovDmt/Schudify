SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";



INSERT INTO `subject` (`id_`, `title`, `timeStart_`, `timeEnd_`, `weekDay_`, `teacher_`, `type_`, `classRoom_`, `university_id_`, `faculty_id_`, `group_id_`) VALUES
(1, 'Программирование', '9:45', '11:20', 1, 'Иванов А.А.', 'Лекция', '404п', 1, 1, 1),
(2, 'ООАиП', '13:25', '15:00', 2, 'Ивановский А.А.', 'Практика', '505', 2, 2, 2);



ALTER TABLE `Subject`
  ADD PRIMARY KEY (`id_`);



