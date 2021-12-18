CREATE TABLE IF NOT EXISTS accidenttype (
  id serial primary key,
  name varchar(200)
);

CREATE TABLE IF NOT EXISTS accidentrule (
  id serial primary key,
  name varchar(200)
);

CREATE TABLE IF NOT EXISTS accident (
  id serial primary key,
  name varchar(2000) NOT NULL,
  text varchar(2000),
  address varchar(2000),
  type_id int references accidenttype(id)
);