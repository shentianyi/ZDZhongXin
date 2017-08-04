create sequence SEQ_T_Message_ssss
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
   'T_SUPERVISOR_MESSAGE',
   'SEQ_T_Message_ssss', 
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');


create sequence t_seqencedict_repa
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
   'T_SUPERVISOR_REPAIRCOST',
   't_seqencedict_repa', 
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');


   
create sequence SEQ_T_Message_costmail
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
   'T_SUPERVISOR_COSTMAIL',
   'SEQ_T_Message_costmail', 
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');

   
   
   
create sequence SEQ_T_Message_overtime
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
   'T_SUPERVISOR_OVERTIME',
   'SEQ_T_Message_overtime',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');



create sequence SEQ_T_Message_outstock
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
   'T_SUPERVISOR_OUTSTOCK',
   'SEQ_T_Message_outstock',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');

   
   
create sequence SEQ_T_Message_movestock
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
   'T_SUPERVISOR_MOVESTOCK',
   'SEQ_T_Message_movestock',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');



create sequence SEQ_T_Message_ins
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
   'T_SUPERVISOR_INSPECTION',
   'SEQ_T_Message_ins',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');



create sequence SEQ_T_Message_uins
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
   'T_SUPERVISOR_UNINSPECTION_PLAN',
   'SEQ_T_Message_uins',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');


create sequence SEQ_T_Message_uninspect
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
   'T_SUPERVISOR_UNINSPECT',
   'SEQ_T_Message_uninspect',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');

   
   

create sequence SEQ_T_Message_inspectremind
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
   'T_SUPERVISOR_INSPECT',
   'SEQ_T_Message_inspectremind',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
