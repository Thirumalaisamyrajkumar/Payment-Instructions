DROP TABLE IF EXISTS ACCOUNT CASCADE ;

CREATE TABLE ACCOUNT
(
    account_Id VARCHAR,
    balance double,
    status  varchar
);

insert into ACCOUNT values ( '12345',543.00,'ACTIVE' );
insert into ACCOUNT values ( '12346',543.00,'CLOSED' );