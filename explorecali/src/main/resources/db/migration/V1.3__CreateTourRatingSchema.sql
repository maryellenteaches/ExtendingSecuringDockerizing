

CREATE TABLE tour_rating (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tour_id BIGINT,
    customer_id BIGINT,
    score INT,
    comment VARCHAR(100));

ALTER TABLE tour_rating ADD CONSTRAINT FK_tour_id FOREIGN KEY (tour_id) REFERENCES tour(id);
ALTER TABLE tour_rating ADD UNIQUE MyConstraint (tour_id, customer_id);

