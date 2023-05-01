CREATE TABLE if not exists users
(
    id           SERIAL PRIMARY KEY,
    password VARCHAR,
    roles VARCHAR,
    user_data_id BIGINT,
    username VARCHAR
);


CREATE TABLE if not exists users_data
(
    id            SERIAL PRIMARY KEY,
    balance      DOUBLE PRECISION,
    number     VARCHAR,
    tariff_id          VARCHAR
);

CREATE TABLE IF NOT EXISTS tariff
(
    id  VARCHAR                NOT NULL PRIMARY KEY,
    name VARCHAR
);


CREATE TABLE IF NOT EXISTS conditions
(
    id   SERIAL PRIMARY KEY,
    fix_price      DOUBLE PRECISION,
    max_minutes BIGINT,
    per_minute DOUBLE PRECISION,
    tariff_id VARCHAR,
    type VARCHAR
);


CREATE TABLE IF NOT EXISTS calls
(
    id SERIAL PRIMARY KEY,
    beginning TIMESTAMP,
    ending TIMESTAMP,
    call_type VARCHAR,
    cost DOUBLE PRECISION,
    duration DOUBLE PRECISION,
    number VARCHAR
);


INSERT INTO public.conditions(    id, fix_price, max_minutes, per_minute, tariff_id, type)
VALUES (1, 100, 300, 0, '06', '12');
INSERT INTO public.conditions(    id, fix_price, max_minutes, per_minute, tariff_id, type)
VALUES (2, 0, 0, 1, '06', '12');
INSERT INTO public.conditions(    id, fix_price, max_minutes, per_minute, tariff_id, type)
VALUES (3, 0, 0, 1.5, '03', '12');
INSERT INTO public.conditions(    id, fix_price, max_minutes, per_minute, tariff_id, type)
VALUES (4, 0, 0, 0, '11', '02');
INSERT INTO public.conditions(
    id, fix_price, max_minutes, per_minute, tariff_id, type)
VALUES (5, 0, 100, 0.5, '11', '01');
INSERT INTO public.conditions(    id, fix_price, max_minutes, per_minute, tariff_id, type)
VALUES (6, 0, 0, 1.5, '11', '01');
INSERT INTO public.tariff (id, name)
VALUES ('03', 'PERMINUTE');
INSERT INTO public.tariff (id, name)
VALUES ('06', 'UNLIMMITED');
INSERT INTO public.tariff (id, name)
VALUES ('11', 'BASIC');
INSERT INTO users (password, roles, user_data_id, username)
VALUES ('$2a$10$g3AB/mpu5ilVDcMCaACqM.cJJ9XiC59/TQ7y/AEJ8hzlbkVuwqLI6', 'ROLE_MANAGER', null, 'manager');
INSERT INTO users (password, roles, user_data_id, username)
VALUES ('$2a$10$HvliC.sGw12U/XCU85tr6ep6mv.2rRKuqlfAf0pr.4qNxb1NYbRBm', 'ROLE_ABONENT', null, '79876543221');
INSERT INTO users_data(balance, number, tariff_id)
VALUES (2000, '79876543221', '06');
INSERT INTO users (password, roles, user_data_id, username)
VALUES ('$2a$10$v1l0A6P3yhqfdhpQYMLATuZU/2sZ1cOqQpxQ0bWO9PP3fIN5erPUm', 'ROLE_ABONENT', null, '79876543222');
INSERT INTO users_data(balance, number, tariff_id)
VALUES (2000, '79876543222', '06');
INSERT INTO users (password, roles, user_data_id, username)
VALUES ('$2a$10$HXzeaYKwQ11B8KBTF.8xdehuXL.4YAyGQgdYQgl73.tCNC2IydXyS', 'ROLE_ABONENT', null, '79876543223');
INSERT INTO users_data(balance, number, tariff_id)
VALUES (2000, '79876543223', '06');
INSERT INTO users (password, roles, user_data_id, username)
VALUES ('$2a$10$pUBlXZmUXNkITiHEmTYMuuAbrenPVeBU/lwKWVwFjBDpt7Yn.Y4HO', 'ROLE_ABONENT', null, '79876543224');
INSERT INTO users_data(balance, number, tariff_id)
VALUES (2000, '79876543224', '06');
INSERT INTO users (password, roles, user_data_id, username)
VALUES ('$2a$10$LYxOl84sOToyBAdSVGz4ne7rHhcz04ho5.rwdauryX7oq3wpS6a1C', 'ROLE_ABONENT', null, '79876543225');
INSERT INTO users_data(balance, number, tariff_id)
VALUES (2000, '79876543225', '06');
INSERT INTO users (password, roles, user_data_id, username)
VALUES ('$2a$10$daxCdg4FGeyjzOys1HgDH.tQUIiPrnZm0K7qgs7lo7AOazifIAvAG', 'ROLE_ABONENT', null, '79876543226');
INSERT INTO users_data(balance, number, tariff_id)
VALUES (1000, '79876543226', '06');
INSERT INTO users (password, roles, user_data_id, username)
VALUES ('$2a$10$Otw9HC/SGlzqgg2fg6UGfOCI2qNriHYnJJf2ijuQyWu./rreaMv7i', 'ROLE_ABONENT', null, '79876543227');
INSERT INTO users_data(balance, number, tariff_id)
VALUES (1000, '79876543227', '06');
INSERT INTO users (password, roles, user_data_id, username)
VALUES ('$2a$10$fr.Flv2yVDnNSlY/KABBEep57Tb1ldj5/UBGgSp.ka1mOsmv79DLq', 'ROLE_ABONENT', null, '79876543228');
INSERT INTO users_data(balance, number, tariff_id)
VALUES (1000, '79876543228', '06');
INSERT INTO users (password, roles, user_data_id, username)
VALUES ('$2a$10$TeRvcMyNSTnb.msUyBn4H.bRAXnoQLgtSj8rlBRy1jcBwttCjpsfC', 'ROLE_ABONENT', null, '79876543229');
INSERT INTO users_data(balance, number, tariff_id)
VALUES (1000, '79876543229', '06');
INSERT INTO users (password, roles, user_data_id, username)
VALUES ('$2a$10$KcWldNAdTPcsoVUUrKyLk.NaxaKl9WQfPAVtiPjTLAtGF2lpC4VQC', 'ROLE_ABONENT', null, '79876543230');
INSERT INTO users_data(balance, number, tariff_id)
VALUES (1000, '79876543230', '06');
INSERT INTO users (password, roles, user_data_id, username)
VALUES ('$2a$10$NhfkTe.wzTpL/AIeLr8xkO3Ss.iwJ8IFOrTEfhnwH2OqeUty8mt8K', 'ROLE_ABONENT', null, '79998887766');
INSERT INTO users_data(balance, number, tariff_id)
VALUES (1000, '79998887766', '03');
INSERT INTO users (password, roles, user_data_id, username)
VALUES ('$2a$10$0cM9TIL.G1Q0qkJ3o8sIAekEDMa8fGVw0qeugfJNdFEUAdaUOvtOC', 'ROLE_ABONENT', null, '79997776655');
INSERT INTO users_data(balance, number, tariff_id)
VALUES (1000, '79997776655', '03');
INSERT INTO users (password, roles, user_data_id, username)
VALUES ('$2a$10$3ME/UGglch0y1xkw1VAcM.vwxLvcMIVmMtxZlhRkRaTUGvBjx.UrG', 'ROLE_ABONENT', null, '79998887767');
INSERT INTO users_data(balance, number, tariff_id)
VALUES (1000, '79998887767', '11');