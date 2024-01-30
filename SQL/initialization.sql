CREATE TABLE
    `Comment` (
                  `id` varchar(32) NOT NULL,
                  `post_id` varchar(32) NOT NULL,
                  `content` varchar(255) NOT NULL,
                  `image_url` varchar(255) NOT NULL,
                  `is_anonymous` tinyint(1) NOT NULL DEFAULT '0',
                  `create_time` datetime NOT NULL,
                  `update_time` datetime NOT NULL,
                  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE
    `Message` (
                  `id` varchar(32) NOT NULL,
                  `sender_id` varchar(32) NOT NULL,
                  `receiver_id` varchar(32) NOT NULL,
                  `content` varchar(255) NOT NULL,
                  `create_time` datetime NOT NULL,
                  `update_time` datetime NOT NULL,
                  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE
    `Post` (
               `id` varchar(32) NOT NULL,
               `title` varchar(64) NOT NULL,
               `content` varchar(255) DEFAULT NULL,
               `creator_id` varchar(32) NOT NULL,
               `create_time` datetime NOT NULL,
               `update_time` datetime NOT NULL,
               `likes` int NOT NULL,
               `is_anonymous` tinyint(1) NOT NULL DEFAULT '0',
               PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE
    `User` (
               `id` varchar(32) NOT NULL,
               `name` varchar(255) NOT NULL,
               `password` varchar(255) NOT NULL,
               `email` varchar(255) NOT NULL,
               `expire_time` datetime DEFAULT NULL,
               `is_banned` tinyint(1) DEFAULT NULL,
               `create_time` datetime NOT NULL,
               `update_time` datetime NOT NULL,
               PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;