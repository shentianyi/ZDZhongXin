create table t_video_report_question(
   id integer not null,
   type integer not null,
   question_classify integer not null,
   question_desc     varchar2(1000) not null,
   depart            integer not null
);


create index index_id on t_video_report_question(id);

comment on table t_video_report_question is '视频报告问题表';
comment on column t_video_report_question.id is '视频报告id';
comment on column t_video_report_question.type is '分类 1:电脑、保险柜、打印机、传真机、网络…2设备接收确认书、作息时间表3委任书、授权书、经销商告知函' ;
comment on column t_video_report_question.question_classify is '问题分类';
comment on column t_video_report_question.question_desc is '问题描述';
comment on column t_video_report_question.depart is '所属部门';
