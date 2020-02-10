CREATE TABLE IF NOT EXISTS estoque.produto (
  `id` int NOT NULL AUTO_INCREMENT,
  `file_import` varchar(255) DEFAULT NULL,
  `industry` varchar(255) DEFAULT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `product` varchar(255) DEFAULT NULL,
  `quantity` decimal(19,2) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `volume` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;