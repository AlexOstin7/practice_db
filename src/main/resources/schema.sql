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
CONSTRAINT Office_FKEY FOREIGN KEY(orgId) REFERENCES PUBLIC.Organization (id)
);

CREATE TABLE IF NOT EXISTS User (
    id              INTEGER  PRIMARY KEY AUTO_INCREMENT,
    firstName       VARCHAR(50) NOT NULL,
    lastName        VARCHAR(50) NOT NULL,
    middleName      VARCHAR(50) NOT NULL,
    possition       VARCHAR(50) NOT NULL,
    officeId        INTEGER     NOT NULL,
    docCode         INTEGER     NOT NULL,
    citizenshipCode INTEGER     NOT NULL,
    CONSTRAINT docs_FKEY FOREIGN KEY(officeId) REFERENCES PUBLIC.Office (id),
    CONSTRAINT docs_FKEY FOREIGN KEY(docCode) REFERENCES PUBLIC.docs (code),
    CONSTRAINT countries_FKEY FOREIGN KEY(citizenshipCode) REFERENCES PUBLIC.countries (code)
);

CREATE TABLE IF NOT EXISTS docs (
    name        VARCHAR(50)  NOT NULL,
    code        INTEGER  NOT NULL
);

CREATE TABLE IF NOT EXISTS countries (
    name        VARCHAR(50)  NOT NULL,
    code        INTEGER  NOT NULL
);


