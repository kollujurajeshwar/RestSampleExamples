DROP TABLE IF EXISTS TBL_DEPT;
 
CREATE TABLE TBL_DEPT (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  dept VARCHAR(250) NOT NULL
);