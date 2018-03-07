CREATE TABLE IF NOT EXISTS Organization (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(50) NOT NULL,
    fullName   VARCHAR(250) NOT NULL,
    inn        INTEGER  NOT NULL,
    kpp        INTEGER  NOT NULL,
    address    VARCHAR(250) NOT NULL,
    phone      INTEGER  NOT NULL,
    isActive   BOOLEAN  NOT NULL
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

CREATE TABLE IF NOT EXISTS docs (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    code        INTEGER  NOT NULL,
    name        VARCHAR(250)  NOT NULL
);

CREATE TABLE IF NOT EXISTS countries (
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
    CONSTRAINT docs_FKEY FOREIGN KEY(docId) REFERENCES PUBLIC.docs (id),
    CONSTRAINT countries_FKEY FOREIGN KEY(countryId) REFERENCES PUBLIC.countries (id)
);


