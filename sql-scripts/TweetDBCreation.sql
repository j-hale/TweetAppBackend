DROP DATABASE IF EXISTS `tweet_app_db`;
CREATE DATABASE  IF NOT EXISTS `tweet_app_db`;
USE `tweet_app_db`;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`user_id` BigInt NOT NULL AUTO_INCREMENT,
    `login_id` varchar(30) UNIQUE NOT NULL,
    `first_name` varchar(45) DEFAULT NULL,
    `last_name` varchar(45) DEFAULT NULL,
    `email` varchar(45) DEFAULT NULL,
    `password` varchar(45) NOT NULL,
    PRIMARY KEY(`user_id`)
);
DROP TABLE IF EXISTS `tweet`;
CREATE TABLE `tweet` (
	`tweet_id` BigInt NOT NULL AUTO_INCREMENT,
    `body` varchar(144) NOT NULL,
    `tag` varchar(50) DEFAULT NULL,
    `user_id` BigInt NOT NULL,
    PRIMARY KEY(`tweet_id`),
    KEY `FK_USER_idx` (`user_id`),
    CONSTRAINT `FK_USER_TWEET` 
    FOREIGN KEY (`user_id`)
    REFERENCES `user`(`user_id`) 
    ON DELETE NO ACTION ON UPDATE NO ACTION
);
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
	`reply_id` BigInt NOT NULL AUTO_INCREMENT,
    `body` varchar(144) NOT NULL,
    `tag` varchar(50) DEFAULT NULL,
    `tweet_id` BigInt NOT NULL,
    `user_id` BigInt NOT NULL,
    PRIMARY KEY(`reply_id`),
    KEY `FK_USER_idx` (`user_id`),
    CONSTRAINT `FK_USER_REPLY` 
    FOREIGN KEY (`user_id`)
    REFERENCES `user`(`user_id`) 
    ON DELETE NO ACTION ON UPDATE NO ACTION,
    KEY `FK_TWEET_idx` (`tweet_id`),
    CONSTRAINT `FK_TWEET_REPLY` 
    FOREIGN KEY (`tweet_id`)
    REFERENCES `tweet`(`tweet_id`) 
    ON DELETE NO ACTION ON UPDATE NO ACTION
);
DROP TABLE IF EXISTS `user_tweet_like`;
CREATE TABLE `user_tweet_like`(
	`user_id` BigInt NOT NULL,
	`tweet_id` BigInt NOT NULL,
    PRIMARY KEY (`user_id`, `tweet_id`),
    KEY `FK_USER_idx` (`user_id`),
    CONSTRAINT `FK_USER_LIKE` 
    FOREIGN KEY (`user_id`)
    REFERENCES `user`(`user_id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
    KEY `FK_TWEET_idx` (`tweet_id`),
    CONSTRAINT `FK_TWEET_LIKE` 
    FOREIGN KEY (`tweet_id`)
    REFERENCES `tweet`(`tweet_id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
);