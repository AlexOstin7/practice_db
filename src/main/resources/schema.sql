CREATE TABLE IF NOT EXISTS Organization (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(50) NOT NULL,
    full_Name   VARCHAR(250) NOT NULL,
    inn        BIGINT  NOT NULL,
    kpp        INTEGER  NOT NULL,
    address    VARCHAR(250) NOT NULL,
    phone      INTEGER  NOT NULL,
    is_Active   BOOLEAN  NOT NULL
);

CREATE TABLE IF NOT EXISTS Office (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(50) NOT NULL,
    address    VARCHAR(250) NOT NULL,
    pnone      INTEGER NOT NULL,
    isActive   BOOLEAN NOT NULL,
    orgId      INTEGER NOT NULL,
CONSTRAINT Organization_FKEY FOREIGN KEY(orgId) REFERENCES PUBLIC.Organization (id)
);

CREATE TABLE IF NOT EXISTS doc (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    code        INTEGER  NOT NULL,
    name        VARCHAR(250)  NOT NULL
);

CREATE TABLE IF NOT EXISTS country (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    code        INTEGER  NOT NULL,
    name        VARCHAR(50)  NOT NULL
);

CREATE TABLE IF NOT EXISTS User (
    id              INTEGER  PRIMARY KEY AUTO_INCREMENT,
    firstName       VARCHAR(50) NOT NULL,
    lastName        VARCHAR(50) NOT NULL,
    middleName      VARCHAR(50) NOT NULL,
    possition       VARCHAR(50) NOT NULL,
    officeId        INTEGER     NOT NULL,
    docId           INTEGER     NOT NULL,
    docNubmer       INTEGER     NOT NULL,
    docDate         INTEGER     NOT NULL,
    countryId         INTEGER     NOT NULL,
    isIdentified    BOOLEAN     NOT NULL,
    CONSTRAINT Office_FKEY FOREIGN KEY(officeId) REFERENCES PUBLIC.Office (id),
    CONSTRAINT doc_FKEY FOREIGN KEY(docId) REFERENCES PUBLIC.doc (id),
    CONSTRAINT country_FKEY FOREIGN KEY(countryId) REFERENCES PUBLIC.country (id)
);


