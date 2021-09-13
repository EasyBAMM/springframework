-- member table
create table member (
  mid varchar(20) primary key,
  mname varchar(20) not null,
  mpassword varchar(200) not null,
  menabled number(1) not null, /*spring security에서 필요*/
  mrole varchar(50) not null /*spring security에서 필요*/
);

insert into member values ('admin', '총관리자', '12345', 1, 'ROLE_ADMIN');
insert into member values ('manager', '관리자', '$2a$10$vI7tC2h4pDre.YqStwOl5uiT.H2bE/T5IkiZ0bDsWGw9wTgcDdUOa', 1, 'ROLE_MANAGER');
insert into member values ('user', '사용자', '64c8cb002682d8136b5aca3e780651023a669c79046616f27659297bb0346027e70ee4ecf0670d21', 1, 'ROLE_USER');
commit;

-- board table
create table board (
  bno number primary key,
  btitle varchar(200) not null,
  bcontent varchar(4000) not null,
  bdate timestamp not null,
  mid varchar(20) references member(mid)
);

create sequence seq_bno 
   minvalue 0 
   start with 0;

insert into board (bno, btitle, bcontent, bdate, mid) 
values (seq_bno.nextval, 'Spring', 'Spring을 이용한 MVC 웹 애플리케이션', sysdate, 'user');
commit;

-- account table
create table account (
  ano number primary key,
  owner varchar(20) not null,
  balance number not null
);

create sequence seq_ano 
   minvalue 0 
   start with 0;

insert into account (ano, owner, balance) values (seq_ano.nextval, '홍길동', 1000000);
insert into account (ano, owner, balance) values (seq_ano.nextval, '스프링', 0);
commit;