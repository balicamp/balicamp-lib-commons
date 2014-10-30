DROP TABLE t_jrn_ledger_balance IF EXISTS ; 
CREATE TABLE t_jrn_ledger_balance (
  branch_id bigint NOT NULL ,
  financial_year int NOT NULL ,
  financial_period smallint NOT NULL ,
  main_account_id varchar(25) NOT NULL ,
  start_balance decimal(18,2) DEFAULT NULL ,
  total_debit decimal(18,2) DEFAULT NULL ,
  total_credit decimal(18,2) DEFAULT NULL,
  end_balance decimal(18,2) DEFAULT NULL ,
  PRIMARY KEY (branch_id,financial_year,financial_period,main_account_id)
); 


DROP TABLE m_main_account IF EXISTS ; 
CREATE TABLE m_main_account (
  main_account_id varchar(32) ,
  name varchar(150) NOT NULL,
  type varchar(15) DEFAULT NULL,
  level varchar(8) NOT NULL,
  parent_main_account_id varchar(32) DEFAULT NULL,
  segment1 varchar(32) DEFAULT NULL,
  segment2 varchar(32) DEFAULT NULL,
  segment3 varchar(32) DEFAULT NULL,
  segment4 varchar(32) DEFAULT NULL,
  segment5 varchar(32) DEFAULT NULL,
  segment6 varchar(32) DEFAULT NULL,
  segment7 varchar(32) DEFAULT NULL,
  segment8 varchar(32) DEFAULT NULL,
  donot_allow_manual_entry smallint DEFAULT '0',
  is_suspended smallint DEFAULT '0',
  is_curr_revaluation smallint DEFAULT '0',
  currency_code varchar(3) DEFAULT NULL,
  report_label varchar(64) DEFAULT NULL,
  PRIMARY KEY (main_account_id)
) 