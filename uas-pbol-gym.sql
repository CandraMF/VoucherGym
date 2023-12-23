-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for db_gym
DROP DATABASE IF EXISTS `db_gym`;
CREATE DATABASE IF NOT EXISTS `db_gym` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_gym`;

-- Dumping structure for table db_gym.member
DROP TABLE IF EXISTS `member`;
CREATE TABLE IF NOT EXISTS `member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nama` varchar(255) NOT NULL,
  `no_telp` varchar(255) DEFAULT NULL,
  `tanggal_daftar` date NOT NULL,
  `jenis_member` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_gym.member: ~4 rows (approximately)
DELETE FROM `member`;
INSERT INTO `member` (`id`, `nama`, `no_telp`, `tanggal_daftar`, `jenis_member`) VALUES
	(1, 'Candra Miftah F', '082110184699', '2023-10-25', 2),
	(2, 'John Smith', '5551234567', '2023-10-25', 1),
	(3, 'Emily Davis', '5559876543', '2023-10-25', 2),
	(4, 'Sarah Wilson', '5557891234', '2023-10-25', 1);

-- Dumping structure for table db_gym.penggunaan_voucher
DROP TABLE IF EXISTS `penggunaan_voucher`;
CREATE TABLE IF NOT EXISTS `penggunaan_voucher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_voucher_member` int NOT NULL,
  `tanggal_masuk` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_gym.penggunaan_voucher: ~10 rows (approximately)
DELETE FROM `penggunaan_voucher`;
INSERT INTO `penggunaan_voucher` (`id`, `id_voucher_member`, `tanggal_masuk`) VALUES
	(1, 5, '2023-10-25'),
	(2, 4, '2023-10-25'),
	(3, 4, '2023-10-25'),
	(4, 4, '2023-10-25'),
	(5, 4, '2023-10-25'),
	(6, 4, '2023-10-25'),
	(7, 4, '2023-10-25'),
	(8, 4, '2023-10-25'),
	(9, 4, '2023-10-25'),
	(10, 5, '2023-10-25'),
	(11, 7, '2023-12-24'),
	(12, 7, '2023-12-24');

-- Dumping structure for table db_gym.voucher
DROP TABLE IF EXISTS `voucher`;
CREATE TABLE IF NOT EXISTS `voucher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nama` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `harga` int NOT NULL,
  `quota` int NOT NULL,
  `jumlah_jam_per_quota` int NOT NULL,
  `expired` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_gym.voucher: ~5 rows (approximately)
DELETE FROM `voucher`;
INSERT INTO `voucher` (`id`, `nama`, `harga`, `quota`, `jumlah_jam_per_quota`, `expired`) VALUES
	(1, 'HIIT Challenge', 500000, 20, 1, 45),
	(2, 'Yoga Retreat', 300000, 8, 2, 30),
	(3, 'Strength and Stamina', 450000, 12, 2, 90),
	(4, 'Cardio Blast', 250000, 10, 1, 60),
	(5, 'Fit for Life', 350000, 15, 2, 45);

-- Dumping structure for table db_gym.voucher_member
DROP TABLE IF EXISTS `voucher_member`;
CREATE TABLE IF NOT EXISTS `voucher_member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_member` int NOT NULL,
  `id_voucher` int NOT NULL,
  `tanggal_pembelian` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_gym.voucher_member: ~5 rows (approximately)
DELETE FROM `voucher_member`;
INSERT INTO `voucher_member` (`id`, `id_member`, `id_voucher`, `tanggal_pembelian`) VALUES
	(1, 1, 1, '2023-10-25'),
	(2, 2, 4, '2023-10-25'),
	(3, 3, 2, '2023-10-25'),
	(4, 4, 2, '2023-10-25'),
	(5, 4, 5, '2023-10-25'),
	(7, 4, 5, '2023-12-24'),
	(8, 3, 2, '2023-12-24');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
