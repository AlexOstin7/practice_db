INSERT INTO DOC (id, NAME, CODE) VALUES (1, 'Свидетельство о рождении', 3);

INSERT INTO DOC (id, NAME, CODE) VALUES (2, 'Военный билет', 7);

INSERT INTO DOC (id, NAME, CODE) VALUES (3, 'Временное удостоверение, выданное взамен военного билета', 8);

INSERT INTO DOC (id, NAME, CODE) VALUES (4, 'Свидетельство о рассмотрении ходатайства о признании лица
беженцем на территории Российской Федерации по существу ', 11);

INSERT INTO DOC (id, NAME, CODE) VALUES (5, 'Вид на жительство в Российской Федерации  ', 12);

INSERT INTO DOC (id, NAME, CODE) VALUES (6, 'Удостоверение беженца', 13);

INSERT INTO DOC (id, NAME, CODE) VALUES (7, 'Разрешение на временное проживание в Российской Федерации', 14);

INSERT INTO DOC (id, NAME, CODE) VALUES (8, 'Свидетельство о предоставлении временного убежища на территории
Российской Федерации', 15);

INSERT INTO DOC (id, NAME, CODE) VALUES (9, 'Паспорт гражданина Российской Федерации', 21);

INSERT INTO DOC (id, NAME, CODE) VALUES (10, 'Паспорт гражданина Республики Беларусь образца 1996 года', 1);

INSERT INTO DOC (id, NAME, CODE) VALUES (11, 'Паспорт Республики Беларусь образца 1993 года', 1);

INSERT INTO DOC (id, NAME, CODE) VALUES (12, 'Паспорт СССР образца 1974 года', 2);

INSERT INTO DOC (id, NAME, CODE) VALUES (13, 'Национальный паспорт иностранного гражданина', 3);

INSERT INTO DOC (id, NAME, CODE) VALUES (14, 'Свидетельство о рождении (для лиц, не достигших 16-летнего возраста)', 4);

INSERT INTO DOC (id, NAME, CODE) VALUES (15, 'Иной документ, удостоверяющий личность', 5);

INSERT INTO DOC (id, NAME, CODE) VALUES (16, 'Справка об освобождении (для лиц, освободившихся из мест лишения свободы)', 7);

INSERT INTO DOC (id, NAME, CODE) VALUES (17, 'Удостоверение беженца', 8);

INSERT INTO DOC (id, NAME, CODE) VALUES (18, 'Вид на жительство в Республике Беларусь', 9);

INSERT INTO DOC (id, NAME, CODE) VALUES (19, 'Удостоверение лица без гражданства', 1);

INSERT INTO DOC (id, NAME, CODE) VALUES (20, 'Удостоверение беженца', 2);

INSERT INTO DOC (id, NAME, CODE) VALUES (21, 'Паспорт гражданина Республики Казахстан', 5);

INSERT INTO DOC (id, NAME, CODE) VALUES (22, 'дипломатический паспорт Республики Казахстан', 5);

INSERT INTO COUNTRY (id, NAME, CODE) VALUES (1, 'Российская Федерация', 643);

INSERT INTO COUNTRY (id, NAME, CODE) VALUES (2, 'Респубика Беларусь', 112);

INSERT INTO COUNTRY (id, NAME, CODE) VALUES (3, 'Республика Казахстан', 395);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (1, 1, 1);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (2, 1, 2);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (3, 2, 10);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (4, 2, 18);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (5, 3, 21);

INSERT INTO COUNTRY_DOC (id, COUNTRY_ID, DOC_ID) VALUES (6, 1, 22);

INSERT INTO ORGANIZATION (id, NAME, FULL_NAME, INN, KPP, ADDRESS, PHONE, IS_ACTIVE) VALUES (1, 'ООО СТАРТ', 'ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕСТВЕННОСТЬЮ СТАРТ',5820001122, 582001001, 'ПЕНЗА УЛ.КРАСНАЯ Д. 1', 541234, TRUE);

INSERT INTO ORGANIZATION (id, NAME, FULL_NAME, INN, KPP, ADDRESS, PHONE, IS_ACTIVE) VALUES (2, 'ЗАО МИР', 'ЗАКРЫТОЕ АКЦИОНЕРНОЕ ОБЩЕСТВО МИР',5820003344, 582001001, 'ПЕНЗА ПР.ПОБЕДЫ Д. 9', 551234, TRUE);

INSERT INTO ORGANIZATION (id, NAME, FULL_NAME, INN, KPP, ADDRESS, PHONE, IS_ACTIVE) VALUES (3, 'ПО ПОЛЁТ', 'ПРОИЗВОДСТВЕННОЕ ОБЪЕДИНЕНИЕ ПОЛЁТ',5820005566, 582001002, 'ПЕНЗА ПР.СТРОИТЕЛЕЙ Д. 11', 654321, TRUE);

INSERT INTO OFFICE (id, NAME, ADDRESS, PHONE, IS_ACTIVE, ORG_ID) VALUES (1, 'АДМИНИСТРАЦИЯ', 'Г.ПЕНЗА УЛ. ЛЕНИНА Д.777', 84125565, TRUE, 1);

INSERT INTO OFFICE (id, NAME, ADDRESS, PHONE, IS_ACTIVE, ORG_ID) VALUES (2, 'БУХГАЛТЕРИЯ', 'Г.ПЕНЗА УЛ. ВОРОШИЛОВА ', 841255667, TRUE, 1);

INSERT INTO OFFICE (id, NAME, ADDRESS, PHONE, IS_ACTIVE, ORG_ID) VALUES (3, 'АДМИНИСТРАЦИЯ', 'Г.САМАРА УЛ. ВИШНЕВАЯ Д.34', 84699001, TRUE, 2);

INSERT INTO OFFICE (id, NAME, ADDRESS, PHONE, IS_ACTIVE, ORG_ID) VALUES (4, 'БУХГАЛТЕРИЯ', 'Г.САМАРА УЛ. ОКРУЖНАЯ Д.35', 84699007, TRUE, 2);

INSERT INTO OFFICE (id, NAME, ADDRESS, PHONE, IS_ACTIVE, ORG_ID) VALUES (5, 'АДМИНИСТРАЦИЯ', 'Г.МОСКВА УЛ. РЕВОЛЮЦИОННАЯ Д.7', 94512345, TRUE, 3);

INSERT INTO USER (id, FIRST_NAME, LAST_NAME, MIDDLE_NAME, POSSITION, DOC_NUMBER, DOC_DATE, PHONE, IS_IDENTIFIED, OFFICE_ID, COUNTRY_ID) VALUES (1, 'ИВАН', 'ИВАНОВИЧ', 'ИВАНОВ', 'ДИРЕКТОР', 565999, '2002-01-01', 94512345, TRUE, 3, 1);
