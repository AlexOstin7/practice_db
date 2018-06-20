CREATE TABLE IF NOT EXISTS organization (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(50) NOT NULL,
    full_Name   VARCHAR(250) NOT NULL,
    inn        BIGINT  NOT NULL,
    kpp        INTEGER  NOT NULL,
    address    VARCHAR(250) NOT NULL,
    phone      INTEGER  NOT NULL,
    is_Active   BOOLEAN  NOT NULL
);

CREATE TABLE IF NOT EXISTS office (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(50) NOT NULL,
    address    VARCHAR(250) NOT NULL,
    phone      INTEGER NOT NULL,
    is_Active   BOOLEAN NOT NULL,
    org_Id      INTEGER NOT NULL,
CONSTRAINT Organization_FKEY FOREIGN KEY(org_Id) REFERENCES PUBLIC.Organization (id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS doc (
    id     INTEGER  PRIMARY KEY AUTO_INCREMENT,
    code        INTEGER  NOT NULL,
    name        VARCHAR(250)  NOT NULL,
    CONSTRAINT PK_DOC_ID PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS country (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    code        INTEGER  NOT NULL,
    name        VARCHAR(50)  NOT NULL,
    CONSTRAINT PK_COUNTRY_ID PRIMARY KEY (id)
);

create table country_doc (
    id          INTEGER  PRIMARY KEY AUTO_INCREMENT,
    country_id  INTEGER  NOT NULL,
    doc_id      INTEGER  NOT NULL,
    CONSTRAINT FK_COUNTRY_ID FOREIGN KEY (country_id)
    REFERENCES country (id),
    CONSTRAINT FK_DOC_ID FOREIGN KEY (doc_id)
    REFERENCES doc (id)
);


CREATE TABLE IF NOT EXISTS user (
  id              INTEGER  PRIMARY KEY AUTO_INCREMENT,
  first_Name       VARCHAR(50) NOT NULL,
  last_Name        VARCHAR(50) NOT NULL,
  middle_Name      VARCHAR(50) NOT NULL,
  possition       VARCHAR(50) NOT NULL,
  doc_Number       INTEGER     NOT NULL,
  doc_Date         DATE        NOT NULL,
  phone            INTEGER     NOT NULL,
  is_Identified    BOOLEAN     NOT NULL,
  office_Id        INTEGER     NOT NULL,
  country_Id       INTEGER     NOT NULL,
  CONSTRAINT office_FKEY FOREIGN KEY(office_Id) REFERENCES PUBLIC.office (id),
  CONSTRAINT country_FKEY FOREIGN KEY(country_Id) REFERENCES PUBLIC.country (id)
);

