alter table sec_group add  approval_status varchar(32) comment 'status approval, applied' ; 
alter table sec_group add  	curr_dual_ctr_id bigint comment 'reference ke table dual control(m_dual_control_table)' ; 
alter table sec_group add  	active_status char(1) default 'A' comment 'flag active atau tidak'  ; 