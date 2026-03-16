-- Script de configuration MySQL pour BankEntityWebApplication
-- Exécuter avec: mysql -u root -p < setup-mysql.sql

CREATE DATABASE IF NOT EXISTS bankdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
DROP USER IF EXISTS 'bankuser'@'localhost';
CREATE USER 'bankuser'@'localhost' IDENTIFIED BY 'bankpass';
GRANT ALL PRIVILEGES ON bankdb.* TO 'bankuser'@'localhost';
FLUSH PRIVILEGES;
