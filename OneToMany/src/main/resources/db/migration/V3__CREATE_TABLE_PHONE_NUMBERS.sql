CREATE TABLE IF NOT EXISTS phone_numbers (
	id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
	phone_number VARCHAR(50),
	person_id bigint
);