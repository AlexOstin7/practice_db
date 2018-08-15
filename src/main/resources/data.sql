INSERT INTO DOC (id, NAME, CODE) VALUES (1, 'Свидетельство о рождении', 3);

INSERT INTO DOC (id, NAME, CODE) VALUES (2, 'Военный билет', 7);

INSERT INTO DOC (id, NAME, CODE) VALUES (3, 'Временное удостоверение, выданное взамен военного билета', 8);

INSERT INTO DOC (id, NAME, CODE) VALUES (4, 'Свидетельство о рассмотрении ходатайства о признании лица
беженцем на территории Российской Федерации', 11);

INSERT INTO DOC (id, NAME, CODE) VALUES (5, 'Вид на жительство в Российской Федерации  ', 12);

INSERT INTO DOC (id, NAME, CODE) VALUES (6, 'Удостоверение беженца', 13);

INSERT INTO DOC (id, NAME, CODE) VALUES (7, 'Разрешение на временное проживание в Российской Федерации', 14);

INSERT INTO DOC (id, NAME, CODE) VALUES (8, 'Свидетельство о предоставлении временного убежища РФ', 15);

INSERT INTO DOC (id, NAME, CODE) VALUES (9, 'Паспорт гражданина Российской Федерации', 21);

INSERT INTO DOC (id, NAME, CODE) VALUES (10, 'Паспорт гражданина Республики Беларусь образца 1996 года', 1);

INSERT INTO DOC (id, NAME, CODE) VALUES (11, 'Паспорт Республики Беларусь образца 1993 года', 2);

INSERT INTO DOC (id, NAME, CODE) VALUES (12, 'Паспорт СССР образца 1974 года', 3);

INSERT INTO DOC (id, NAME, CODE) VALUES (13, 'Национальный паспорт иностранного гражданина', 4);

INSERT INTO DOC (id, NAME, CODE) VALUES (14, 'Свидетельство о рождении (для лиц, не достигших 16-летнего возраста)', 5);

INSERT INTO DOC (id, NAME, CODE) VALUES (15, 'Иной документ, удостоверяющий личность', 6);

INSERT INTO DOC (id, NAME, CODE) VALUES (16, 'Справка об освобождении (для лиц, освободившихся из мест лишения свободы)', 7);

INSERT INTO DOC (id, NAME, CODE) VALUES (17, 'Удостоверение беженца', 8);

INSERT INTO DOC (id, NAME, CODE) VALUES (18, 'Вид на жительство в Республике Беларусь', 9);

INSERT INTO DOC (id, NAME, CODE) VALUES (19, 'Удостоверение лица без гражданства', 10);

INSERT INTO DOC (id, NAME, CODE) VALUES (20, 'Удостоверение беженца', 11);

INSERT INTO DOC (id, NAME, CODE) VALUES (21, 'Паспорт гражданина Республики Казахстан', 5);

INSERT INTO DOC (id, NAME, CODE) VALUES (22, 'Дипломатический паспорт Республики Казахстан', 6);

INSERT INTO COUNTRY (id, NAME, CODE) VALUES (1, 'Российская Федерация', 643);

INSERT INTO COUNTRY (id, NAME, CODE) VALUES (2, 'Респубика Беларусь', 112);

INSERT INTO COUNTRY (id, NAME, CODE) VALUES (3, 'Республика Казахстан', 395);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (1, 1, 1);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (2, 1, 2);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (3, 1, 3);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (4, 1, 4);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (5, 1, 5);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (6, 1, 6);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (7, 1, 7);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (8, 1, 8);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (9, 1, 9);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (10, 2, 10);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (11, 2, 11);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (12, 2, 12);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (13, 2, 13);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (14, 2, 14);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (15, 2, 15);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (16, 2, 16);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (17, 2, 17);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (18, 2, 18);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (19, 2, 19);
INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (20, 2, 20);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (21, 3, 21);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (22, 3, 22);

INSERT INTO ORGANIZATION (id, NAME, FULL_NAME, INN, KPP, ADDRESS, PHONE, IS_ACTIVE) VALUES (1, 'ООО СТАРТ', 'ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕСТВЕННОСТЬЮ СТАРТ',5820001122, 582001001, 'ПЕНЗА УЛ.КРАСНАЯ Д. 1', 541234, TRUE);

INSERT INTO ORGANIZATION (id, NAME, FULL_NAME, INN, KPP, ADDRESS, PHONE, IS_ACTIVE) VALUES (2, 'ЗАО МИР', 'ЗАКРЫТОЕ АКЦИОНЕРНОЕ ОБЩЕСТВО МИР',5820003344, 582001001, 'ПЕНЗА ПР.ПОБЕДЫ Д. 9', 551234, TRUE);

INSERT INTO ORGANIZATION (id, NAME, FULL_NAME, INN, KPP, ADDRESS, PHONE, IS_ACTIVE) VALUES (3, 'ПО ПОЛЁТ', 'ПРОИЗВОДСТВЕННОЕ ОБЪЕДИНЕНИЕ ПОЛЁТ',5820005566, 582001002, 'ПЕНЗА ПР.СТРОИТЕЛЕЙ Д. 11', 654321, TRUE);

INSERT INTO OFFICE (id, NAME, ADDRESS, PHONE, IS_ACTIVE, ORG_ID) VALUES (1, 'АДМИНИСТРАЦИЯ', 'Г.ПЕНЗА УЛ. ЛЕНИНА Д.777', 84125565, TRUE, 1);

INSERT INTO OFFICE (id, NAME, ADDRESS, PHONE, IS_ACTIVE, ORG_ID) VALUES (2, 'БУХГАЛТЕРИЯ', 'Г.ПЕНЗА УЛ. ВОРОШИЛОВА ', 841255667, TRUE, 1);

INSERT INTO OFFICE (id, NAME, ADDRESS, PHONE, IS_ACTIVE, ORG_ID) VALUES (3, 'АДМИНИСТРАЦИЯ', 'Г.САМАРА УЛ. ВИШНЕВАЯ Д.34', 84699001, TRUE, 2);

INSERT INTO OFFICE (id, NAME, ADDRESS, PHONE, IS_ACTIVE, ORG_ID) VALUES (4, 'БУХГАЛТЕРИЯ', 'Г.САМАРА УЛ. ОКРУЖНАЯ Д.35', 84699007, TRUE, 2);

INSERT INTO OFFICE (id, NAME, ADDRESS, PHONE, IS_ACTIVE, ORG_ID) VALUES (5, 'АДМИНИСТРАЦИЯ', 'Г.МОСКВА УЛ. РЕВОЛЮЦИОННАЯ Д.7', 94512345, TRUE, 3);

INSERT INTO USER (id, FIRST_NAME, MIDDLE_NAME, SECOND_NAME, POSSITION, DOC_NUMBER, DOC_DATE, PHONE, IS_IDENTIFIED, OFFICE_ID, DOC_ID) VALUES (1, 'ИВАН', 'ИВАНОВИЧ', 'ИВАНОВ', 'ГЕНЕРАЛЬНЫЙ ДИРЕКТОР', 565999, '2002-01-15', 94512345, TRUE, 1, 1);

INSERT INTO USER (id, FIRST_NAME, MIDDLE_NAME, SECOND_NAME, POSSITION, DOC_NUMBER, DOC_DATE, PHONE, IS_IDENTIFIED, OFFICE_ID, DOC_ID) VALUES (2, 'ПЁТР', 'ПЁТРОВИЧ', 'ПЕТРОВ', 'ДИРЕКТОР', 555999, '2012-10-11', 94512346, TRUE, 1, 11);

INSERT INTO USER (id, FIRST_NAME, MIDDLE_NAME, SECOND_NAME, POSSITION, DOC_NUMBER, DOC_DATE, PHONE, IS_IDENTIFIED, OFFICE_ID, DOC_ID) VALUES (3, 'СЕМЁН', 'СЕМЁНОВИЧ', 'СЕМЁНОВ', 'ЗАМЕСТИТЕЛЬ ДИРЕКТОРА', 555900, '20014-12-31', 94512340, TRUE, 1, 1);

INSERT INTO USER (id, FIRST_NAME, MIDDLE_NAME, SECOND_NAME, POSSITION, DOC_NUMBER, DOC_DATE, PHONE, IS_IDENTIFIED, OFFICE_ID, DOC_ID) VALUES (4, 'КАРЛ', 'ИЛЬИЧ', 'ШАЦ', 'ЗАМЕСТИТЕЛЬ ДИРЕКТОРА', 555910, '2015-06-19', 94512340, TRUE, 2, 4);

INSERT INTO USER (id, FIRST_NAME, MIDDLE_NAME, SECOND_NAME, POSSITION, DOC_NUMBER, DOC_DATE, PHONE, IS_IDENTIFIED, OFFICE_ID, DOC_ID) VALUES (5, 'БОНИТА', 'ДЖОНИТОВНА', 'ПОСПЕГАСЕС', 'ГЛАВНЫЙ БУХГАЛТЕР', 522900, '2017-11-12', 9451842, TRUE, 2, 5);

INSERT INTO USER (id, FIRST_NAME, MIDDLE_NAME, SECOND_NAME, POSSITION, DOC_NUMBER, DOC_DATE, PHONE, IS_IDENTIFIED, OFFICE_ID, DOC_ID) VALUES (6, 'ЮСТАС', 'АЛЕКСЕЕВИЧ', 'ИСАЕВ', 'ГЛАВНЫЙ БУХГАЛТЕР', 522007, '2007-04-19', 8421842, TRUE, 3, 6);

INSERT INTO USER (id, FIRST_NAME, MIDDLE_NAME, SECOND_NAME, POSSITION, DOC_NUMBER, DOC_DATE, PHONE, IS_IDENTIFIED, OFFICE_ID, DOC_ID) VALUES (7, 'НИКОЛАС', 'АДАМОВИЧ', 'КЛАЙН', 'БУХГАЛТЕР', 522008, '2017-01-29', 8421841, TRUE, 3, 6);

INSERT INTO USER (id, FIRST_NAME, MIDDLE_NAME, SECOND_NAME, POSSITION, DOC_NUMBER, DOC_DATE, PHONE, IS_IDENTIFIED, OFFICE_ID, DOC_ID) VALUES (8, 'ДУГЛАС', 'КУСТАНАЙВИЧ', 'САБОЙНЕС', 'РУКОВОДИТЕЛЬ ОТДЕЛА', 522901, '2012-03-03', 9451842, TRUE, 5, 7);

INSERT INTO USER (id, FIRST_NAME, MIDDLE_NAME, SECOND_NAME, POSSITION, DOC_NUMBER, DOC_DATE, PHONE, IS_IDENTIFIED, OFFICE_ID, DOC_ID) VALUES (9, 'АЙСЕДОРА', 'АХМАТОВНА', 'ПУШКИНА', 'ГЛАВНЫЙ БУХГАЛТЕР', 522900, '2017-07-23', 8422842, TRUE, 4, 8);

INSERT INTO USER (id, FIRST_NAME, MIDDLE_NAME, SECOND_NAME, POSSITION, DOC_NUMBER, DOC_DATE, PHONE, IS_IDENTIFIED, OFFICE_ID, DOC_ID) VALUES (10, 'ЖОНС', 'БОРИСОВИЧ', 'НАЗАРОВ', 'ЗАМЕСТИТЕЛЬ РУКОВОДИТЕЛЯ', 533900, '2015-02-28', 9400842, TRUE, 5, 11);

INSERT INTO USER (id, FIRST_NAME, MIDDLE_NAME, SECOND_NAME, POSSITION, DOC_NUMBER, DOC_DATE, PHONE, IS_IDENTIFIED, OFFICE_ID, DOC_ID) VALUES (11, 'МАРАТ', 'КАЙРАТОВИЧ', 'КАРИМОВ', 'БУХГАЛТЕР', 522203, '2016-09-27', 9451842, TRUE, 4, 21);