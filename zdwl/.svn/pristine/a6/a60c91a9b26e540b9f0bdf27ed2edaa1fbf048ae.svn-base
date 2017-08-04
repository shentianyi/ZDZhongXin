-- Create table
create table t_draft_lnci(
draft_num varchar2(64) not null,
lnciNo varchar2(64),
lnciId varchar2(64));

-- Add comments to the columns 
comment on table t_draft_lnci is
'票据与借据关联表';
comment on column t_draft_lnci.draft_num 
  is '票号';
comment on column t_draft_lnci.lnciNo 
  is '借据号';
comment on column t_draft_lnci.lnciId 
  is '借据ID';
  
-- Create/Recreate primary, unique and foreign key constraints 