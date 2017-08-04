-- Create table
create table t_brand
(
  id            number(19) not null,
  name    varchar2(100)
);
-- Add comments to the columns 
comment on table t_brand is
'品牌表';
comment on column t_brand.name
  is '品牌';
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_brand
  add constraint pk_t_brand primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_BRAND
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

  
  
insert into t_seqencedict
  (id,
   dbname,
   tablename,
   seqname,
   seqinitvalue,
   addseqdate,
   createtime,
   addsequser,
   memo)
values
  ((select max(id) + 1 from t_seqencedict),
   'zddb',
   't_brand',
   'SEQ_T_BRAND',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
