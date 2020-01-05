

CREATE TABLE tour_package(
  code CHAR(2) NOT NULL UNIQUE,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE tour (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  tour_package_code CHAR(2) NOT NULL,
  title VARCHAR(100) NOT NULL,
  description VARCHAR(2000) NOT NULL,
  blurb VARCHAR(2000) NOT NULL,
  bullets VARCHAR(2000) NOT NULL,
  price VARCHAR(10) not null,
  duration VARCHAR(32) NOT NULL,
  difficulty VARCHAR(16) NOT NULL,
  region VARCHAR(20) NOT NULL,
  keywords VARCHAR(100)
);
ALTER TABLE tour ADD CONSTRAINT FK_TOUR_PACKAGE_CODE FOREIGN KEY (tour_package_code) REFERENCES tour_package(code);


insert into tour_package (code, name) values
('BC', 'Backpack Cal'),
('CC', 'California Calm'),
('CH', 'California Hot springs'),
('CY', 'Cycle California'),
('DS', 'From Desert to Sea'),
('KC', 'Kids California'),
('NW', 'Nature Watch'),
('SC', 'Snowboard Cali'),
('TC', 'Taste of California');
