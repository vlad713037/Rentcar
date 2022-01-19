CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	login VARCHAR (30) NOT NULL UNIQUE,
	pass VARCHAR (50) NOT NULL,
	user_role VARCHAR (30) NOT NULL DEFAULT 'USER',
	first_name VARCHAR (30) NOT NULL,
	last_name VARCHAR (30) NOT NULL
	);

INSERT INTO users (login, pass, user_role, first_name, last_name)
 VALUES ('ivan123','202CB962AC59075B964B07152D234B70','USER','Ivan','Ivanov'),
  ('petr007', '9E94B15ED312FA42232FD87A55DB0D39','USER','Petr','Petrov'),
  ('admin','21232F297A57A5A743894A0E4A801FC3','ADMIN','admin','admin'),
('venom321', 'CAF1A3DFB505FFED0D024130F58C5CFA','USER','Makar','Skib'),
('sa21', '3C59DC048E8850243BE8079A5C74D079','USER','John','Konor');



CREATE TABLE passport (
   	 id SERIAL PRIMARY KEY,
   	 pass_number  VARCHAR(15),
   	 issuer_rovd  VARCHAR(25),
users_id  INTEGER NOT NULL,
FOREIGN KEY (users_id) REFERENCES users (id)
);

INSERT INTO passport (pass_number, issuer_rovd, users_id)
VALUES ('KH2111365', 'Oktiabrski ROVD',1),
('KH7536112', 'Oktiabrski ROVD',2),
('KH9937090', 'Leninski ROVD',3),
('KH4429321', 'Leninski ROVD',4),
 ('KH1234567', 'Oktiabrski ROVD',5);


CREATE TABLE car  (
	id SERIAL PRIMARY KEY,
	brand_name VARCHAR (20) NOT NULL,
	model_name VARCHAR (20) NOT NULL,
	price_onday REAL NOT NULL,
	reg_number VARCHAR (20) NOT NULL UNIQUE,
	status_car  VARCHAR (20) NOT NULL DEFAULT 'FREE'
	);


INSERT INTO car (brand_name, model_name, price_onday, reg_number, status_car)
 VALUES ('Audi','A4',30,'0001AA4', 'FREE'),
 ('Audi','A4',25,'0002AA4','FREE'),
 ('Ford','Focus',10,'0003AA4','FREE'),
 ('Ford','Focus',20,'0004AA4', 'FREE');


CREATE TABLE orders  (
	id SERIAL PRIMARY KEY,
	car_id  INTEGER,
	users_id  INTEGER NOT NULL,
	status_orders VARCHAR (30) NOT NULL DEFAULT 'OPEN',
	rent_startdate  DATE NOT NULL,
	rent_enddate  DATE NOT NULL,
FOREIGN KEY (car_id) REFERENCES car (id) ON DELETE SET NULL,
FOREIGN KEY (users_id) REFERENCES users (id)
);

INSERT INTO orders  (car_id, users_id, status_orders, rent_startdate, rent_enddate )
 VALUES (2,2,'OPEN','2022-01-22','2022-01-23'),
        (4,4,'OPEN','2022-01-20','2022-01-21');



CREATE TABLE invoice (
	id SERIAL PRIMARY KEY,
	orders_id  INTEGER NOT NULL,
	total_price REAL NOT NULL,
	status_invoice VARCHAR (30) NOT NULL DEFAULT 'OPEN',
FOREIGN KEY (orders_id) REFERENCES orders (id)
	);

CREATE TABLE car_damage (
	id SERIAL PRIMARY KEY,
	orders_id  INTEGER NOT NULL,
	description VARCHAR (60) NOT NULL,
	repair_cost  REAL NOT NULL,
	status_damage VARCHAR (20) NOT NULL DEFAULT 'NOT_FIXED',
	FOREIGN KEY (orders_id) REFERENCES orders (id)
	);

INSERT INTO car_damage (orders_id , description, repair_cost, status_damage)
 VALUES (2,'scratched right_car fender','100','NOT_FIXED');
