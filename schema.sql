CREATE DATABASE IF NOT EXISTS `java_tba`;

USE `java_tba`;

DROP TABLE IF EXISTS `room_transition`;
DROP TABLE IF EXISTS `room`;
DROP TABLE IF EXISTS `direction`;

CREATE TABLE `room` (
    `id` INT(11) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255),
    `description` VARCHAR(255)
);

CREATE TABLE `direction` (
    `id` INT(11) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255),
    `command` VARCHAR(100) UNIQUE
);

CREATE TABLE `room_transition` (
    `id` INT(11) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `from_room_id` INT(11) UNSIGNED NOT NULL,
    `to_room_id` INT(11) UNSIGNED NOT NULL,
    `direction_id` INT(11) UNSIGNED NOT NULL,

    FOREIGN KEY (`from_room_id`) REFERENCES `room`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`to_room_id`) REFERENCES `room`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`direction_id`) REFERENCES `direction`(`id`) ON DELETE CASCADE
);
