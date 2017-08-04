-- Create table
create table t_inspection_planinfo
(
  id                         NUMBER(19) not null,
  
  planCode             		 VARCHAR2(128),
  inControlUserId              NUMBER(19),
  mkTableUserId              NUMBER(19),

  provinceAmount             NUMBER(19),
  cityAmount                 NUMBER(19),
  stores                	 NUMBER(19),
  bankAmount                 NUMBER(19),
  brandAmount                NUMBER(19),
  stockAmount                NUMBER(19),
  predictCheckdays           NUMBER(10,1),
  
  planBeginTime              VARCHAR2(128),
  planSubmitTime             VARCHAR2(128),
  predictFinishDate          DATE,
  status					 NUMBER(19),
  unCheckPassCause           VARCHAR2(1024),
  createDate				 DATE
)
-- Add comments to the table 
comment on table t_inspection_planinfo
  is '巡检计划列表';

-- Add comments to the columns 
comment on column t_inspection_planinfo.id
  is '主键ID';
 
comment on column t_inspection_planinfo.planCode
  is '巡检编号'
comment on column t_inspection_planinfo.inControlUserId
  is '内控专员ID';
comment on column t_inspection_planinfo.mkTableUserId
  is '制表人ID';
comment on column t_inspection_planinfo.provinceAmount
  is '省数';
comment on column t_inspection_planinfo.cityAmount
  is '市数' ;
comment on column t_inspection_planinfo.stores
  is '涉及店数' ;
comment on column t_inspection_planinfo.bankAmount
  is '银行数' ;
comment on column t_inspection_planinfo.brandAmount
  is '品牌数' ;
comment on column t_inspection_planinfo.stockAmount
  is '库存合计' ;
comment on column t_inspection_planinfo.predictCheckdays
  is '预计检查天数(最小单位0.5天)' ;
comment on column t_inspection_planinfo.planBeginTime
  is '计划开始时间' ;
comment on column t_inspection_planinfo.planSubmitTime
  is '计划提交时间' ;
comment on column t_inspection_planinfo.predictFinishDate
  is '预计完成日期' ;
comment on column t_inspection_planinfo.status
  is '状态' ;
comment on column t_inspection_planinfo.unCheckPassCause
  is '审批不通过原因';
comment on column t_inspection_planinfo.createDate
  is '创建时间';
  
  
  
-- Create sequence 
create sequence SEQ_T_INSPECTIONPLANINFO
minvalue 1
maxvalue 999999999999999999999999999
start with 123
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
   't_inspection_planinfo',
   'SEQ_T_INSPECTIONPLANINFO',
   1,
   sysdate,
   sysdate,
   'wdc',
   '手动添加');