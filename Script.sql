CREATE TABLE IF NOT EXISTS Organiztion (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(50) NOT NULL,
    fullName   VARCHAR(100) NOT NULL,
    inn        INTEGER  NOT NULL,
    kpp        INTEGER  NOT NULL,
    address    VARCHAR  NOT NULL,
    phone      INTEGER  NOT NULL,
    is_Active   BOOLEAN  NOT NULL
) ;
CREATE TABLE IF NOT EXISTS Office (
    ordId     INTEGER   PRIMARY KEY AUTO_INCREMENT,
    name      VARCHAR(50) NOT NULL,
    phone     INTEGER   NOT NULL,
    is_Active  BOOLEAN   NOT NULL
) ;

CREATE TABLE IF NOT EXISTS User (
    officeId   INTEGER  PRIMARY KEY AUTO_INCREMENT,
    firstName  VARCHAR(50) NOT NULL,
    lastName   VARCHAR(100) NOT NULL,
    middleName INTEGER  NOT NULL,
    position   INTEGER  NOT NULL,
    docCode    VARCHAR  NOT NULL,
    citizenShipCode INTEGER  NOT NULL,
    is_Active   BOOLEAN  NOT NULL
)