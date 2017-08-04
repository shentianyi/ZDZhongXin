create table T_CHECKSTOCK_MANAGE_PIC(
       csmid integer not null ,
       indexs integer  check(indexs > 0 and indexs < 21),
       picid integer not null
);

comment on table T_CHECKSTOCK_MANAGE_PIC is '日查库照片表';
comment on column T_CHECKSTOCK_MANAGE_PIC.csmid is 'T_CHECKSTOCK_MANAGE主键ID';
comment on column T_CHECKSTOCK_MANAGE_PIC.picid is 'PIC主键ID';
comment on column T_CHECKSTOCK_MANAGE_PIC.indexs is '图片序号';

create index INDEX_csmid on T_CHECKSTOCK_MANAGE_PIC(csmid);

create index INDEX_indexs on T_CHECKSTOCK_MANAGE_PIC(indexs);

create index index_csmid_indexs on T_CHECKSTOCK_MANAGE_PIC(csmid,indexs);