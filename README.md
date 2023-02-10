# spring-boot-socialweb-demo


1.Chapter one user module:

````
CREATE TABLE `user` (
`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
`user_name` varchar(60) NOT NULL DEFAULT '',
`user_password` varchar(60) NOT NULL DEFAULT '',
`user_tel` varchar(60) NOT NULL DEFAULT '',
`create_time` datetime DEFAULT NULL,
`update_time` datetime DEFAULT NULL,
`version` int(11) DEFAULT '0',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```


