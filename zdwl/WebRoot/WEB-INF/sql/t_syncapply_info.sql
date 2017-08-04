-- Create table
create table t_syncapply_info(serialNo varchar2(64) not null);

-- Add comments to the columns 
comment on table t_syncapply_info is
'标识申请信息是否同步表';
comment on column t_syncapply_info.serialNo 
  is '交易流水号';
-- Create/Recreate primary, unique and foreign key constraints 