CREATE TABLE IF NOT  EXISTS Itinerary(
  reference_num VARCHAR(45),
  customer_id VARCHAR(45),
  hotel_name VARCHAR(45),
  address VARCHAR(45),
  phone VARCHAR(45),
  price FLOAT,
  room_type VARCHAR(45),
  checkin_date DATE,
  checkout_date DATE
  );

CREATE TABLE IF NOT  EXISTS Customer(
  customer_id VARCHAR(45),
  reference_num VARCHAR(45),
  first_name VARCHAR(45),
  last_name VARCHAR(45),
  phone VARCHAR(45),
  date_of_birth DATE,
  sex VARCHAR(45),
  email_address VARCHAR(45)
  );
