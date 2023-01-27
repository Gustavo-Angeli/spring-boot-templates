CREATE TABLE IF NOT EXISTS person (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    money DOUBLE NOT NULL,
    activated BIT(1) DEFAULT 1,
    info_id bigint
);