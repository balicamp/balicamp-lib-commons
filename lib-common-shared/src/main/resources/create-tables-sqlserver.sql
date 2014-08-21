-- database versi sql server
CREATE TABLE sec_password_policy
(
  password_policy_id bigint NOT NULL,
  
  disabled_limit smallint,
  inactive_limit smallint,
  maximum_login_attempts smallint,
  minimum_alphabets smallint,
  minimum_length smallint,
  minimum_numerics smallint,
  old_password_age smallint,
  old_password_base varchar(255),
  password_age smallint,
  regex_desc varchar(255),
  regular_expression varchar(255),
  
  created_by varchar(255),
  created_on datetime ,
  modified_by varchar(255),
  modified_on datetime null default null  ,
  
  CONSTRAINT sec_password_policy_pkey PRIMARY KEY (password_policy_id )
) ; 

-- sec_application. ini daftar aplikasi yang ada
CREATE TABLE sec_application
(
  application_id bigint NOT NULL,
  
  application_code varchar(255),
  application_name varchar(255),
  application_url varchar(255),
  aut_login_url varchar(255),
  kick_prev_concurent_login varchar(255),
  lang varchar(255),
  notify_succed_url varchar(255),
  session_timeout integer,
  status varchar(1),
  
  
  created_by varchar(255),
  created_on datetime,
  modified_by varchar(255),
  modified_on datetime,
  CONSTRAINT sec_application_pkey PRIMARY KEY (application_id )
)
  
  -- ini sec_user. user + password
-- kalau dalam 1 database ada lebih dari 1 app, pls di check constraint : 
-- 1. fk_user_to_default_application
-- 2. sec_user_pkey
CREATE TABLE sec_user
(
  user_id bigint NOT NULL,
  user_code varchar(50) NOT NULL,
  expired_date date,
  birthdate date,
  datetime_pattern varchar(20) DEFAULT 'dd MMM yyyy',
  timezone varchar(20) DEFAULT 'Asia/Jakarta',
  decimal_separator character(1) DEFAULT '.',
  numeric_scale smallint DEFAULT 4,
  email varchar(50) DEFAULT ' ',
  currency varchar(3) DEFAULT 'IDR',
  max_rows_per_page smallint DEFAULT 20,
  failed_login_attempts smallint DEFAULT 0,
  real_name varchar(100) DEFAULT NULL,
  status character(1) DEFAULT 'A',
  locale varchar(10) DEFAULT 'en_US',
  default_application_id bigint,
  active_status character(1) DEFAULT 'A',
  login_status varchar(1) DEFAULT '0',
  chipper_text varchar(128) DEFAULT NULL,
  is_ntlm_user character(1) NOT NULL DEFAULT 'Y', -- Flag, user NTLM atau user atau bukan
  is_super_admin character(1) NOT NULL DEFAULT 'N', -- flag, super user
  created_by varchar(30) DEFAULT NULL,
  created_on datetime,
  modified_by varchar(50) DEFAULT NULL,
  modified_on datetime,
  CONSTRAINT sec_user_pkey PRIMARY KEY (user_id ) 
)

ALTER TABLE  sec_user ADD CONSTRAINT fk_user_to_default_application FOREIGN KEY (default_application_id) REFERENCES sec_application (application_id);

-- ini table password history. ini kalau di terapkan kebijakan password tidak boleh sama dengan n password tertentu etc
CREATE TABLE sec_password_hs
(
  password_id NUMERIC(19,0) NOT NULL,
  user_id BIGINT,
  cipher_text VARCHAR(255),
  effective_date DATETIME NULL ,
  
  created_by VARCHAR(255),
  created_on datetime,
  CONSTRAINT sec_password_hs_pkey PRIMARY KEY (password_id )
)

-- mapping user vs aplikasi. ini unutk logical view user dari app
CREATE TABLE sec_application_user
(
  application_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  CONSTRAINT sec_application_user_pkey PRIMARY KEY (application_id , user_id )
  
)

-- constraint-constraint di table : sec_application_user
ALTER TABLE  sec_application_user ADD CONSTRAINT fk_application_user_to_app FOREIGN KEY (application_id) REFERENCES sec_application (application_id);
ALTER TABLE  sec_application_user ADD CONSTRAINT fk_application_user_to_usr FOREIGN KEY (user_id) REFERENCES sec_user (user_id);

CREATE TABLE sec_branch
(
  branch_id bigint NOT NULL,
  branch_address varchar(255),
  branch_code varchar(255),
  branch_name varchar(255),
  branch_id_parent bigint,
  description varchar(255),
  status varchar(1),
  
 created_by varchar(32),
  created_on datetime,
  	modified_by varchar(255),
  	modified_on datetime,
  
  CONSTRAINT sec_branch_pkey PRIMARY KEY (branch_id )
)

-- user berada di branch mana
CREATE TABLE sec_branch_assignment
(
  branch_assignment_id bigint NOT NULL,
  user_id bigint,
  branch_id bigint,
  
  created_by varchar(32),
  created_on datetime,
  
  CONSTRAINT sec_branch_assignment_pkey PRIMARY KEY (branch_assignment_id ) , 
  CONSTRAINT sec_branch_assignment_unq UNIQUE (user_id  ,branch_id ) 
  
)

alter table sec_branch_assignment ADD CONSTRAINT  fk_branch_assgn_to_user  FOREIGN KEY (user_id)
  	REFERENCES sec_user (user_id) ; 
	
-- table : sec_group , master group of user

CREATE TABLE sec_group
(
  group_id bigint NOT NULL,
  application_id bigint,
  group_code varchar(255),
  group_name varchar(255),
  status varchar(1),
  is_super_group character (1),
  
  created_by varchar(32),
  created_on datetime,
  modified_by varchar(255),
  modified_on datetime,
  CONSTRAINT sec_group_pkey PRIMARY KEY (group_id ) 
)

alter table sec_group add  CONSTRAINT fk_group_to_app_table  FOREIGN KEY (application_id)
  	REFERENCES sec_application (application_id)  ; 
	
-- table : sec_group_assignment
-- ini map antara user + group mana dia berada
CREATE TABLE sec_group_assignment
(
  group_assignment_id bigint NOT NULL,
  group_id bigint,
  user_id bigint,
  
  created_by varchar(32),
  created_on datetime,
  CONSTRAINT sec_group_assignment_pkey PRIMARY KEY (group_assignment_id ) ,
  CONSTRAINT sec_group_assignment_usr_grp_unq UNIQUE (group_id,user_id  ) 
      	
      	
)

alter table sec_group_assignment add  CONSTRAINT fk_grp_assg_to_user  FOREIGN KEY (user_id)
  		REFERENCES sec_user(user_id)  ; 
alter table sec_group_assignment add    CONSTRAINT fk_grp_assg_to_group  FOREIGN KEY (group_id)
  		REFERENCES sec_group(group_id) ;
		
-- table : sec_page_definition
-- ini definisi page / panel
CREATE TABLE sec_page_definition
(
  page_id bigint NOT NULL,
  application_id bigint,
  page_code varchar(255),
  page_url varchar(255),
  additional_data varchar(255),
  page_remark varchar(255) , 
  created_by varchar(32),
created_on datetime,
modified_by varchar(255),
modified_on datetime,
  
  
  CONSTRAINT sec_page_definition_pkey PRIMARY KEY (page_id ) 
)

alter table sec_page_definition add    CONSTRAINT fk_page_def_to_app  FOREIGN KEY (application_id)
  	REFERENCES sec_application(application_id) ; 
	
-- table : sec_function
-- Master menu yang tersedua dalam app
CREATE TABLE sec_function
(
  function_id bigint NOT NULL,
  application_id bigint,
  function_code varchar(255),
  function_id_parent bigint,
  function_label varchar(255),
  menu_tree_code varchar(255),
  page_id bigint,
  sibling_order smallint ,
  status varchar(1),
  tree_level_position integer,
  
  created_by varchar(32),
  created_on datetime,
  modified_by varchar(255),
  modified_on datetime,
  
  CONSTRAINT sec_function_pkey PRIMARY KEY (function_id ), 
  CONSTRAINT sec_func_app_n_code UNIQUE (application_id , function_code) 
  
)

alter table sec_function add    CONSTRAINT fk_function_to_application  FOREIGN KEY (application_id)
  	REFERENCES sec_application(application_id)  ; 
    
alter table sec_function add    CONSTRAINT fk_function_to_page_def  FOREIGN KEY (page_id)
  	REFERENCES sec_page_definition(page_id) ; 
	
-- table : sec_function_assignment
-- ini function vs group. group x dpt menu Y
CREATE TABLE sec_function_assignment
(
  function_assignment_id bigint NOT NULL,
  function_id numeric(19,2),
  group_id numeric(19,2),
  
  created_by varchar(32),
created_on datetime,
  CONSTRAINT sec_function_assignment_pkey PRIMARY KEY (function_assignment_id )
)

CREATE TABLE sec_signon
(
  signon_id bigint NOT NULL ,
  application_id bigint,
  description varchar(255),
  logon_time datetime,
  logoff_time datetime,
  terminal varchar(255),
  user_browser varchar(255),
  user_id bigint,
  uuid varchar(255),

  created_by varchar(32),
  created_on datetime,
  modified_by varchar(255),
  modified_on datetime,

  CONSTRAINT sec_signon_pkey PRIMARY KEY (signon_id ) , 
  CONSTRAINT fk_sec_signon_to_app  FOREIGN KEY (application_id)
  	REFERENCES sec_application(application_id) , 
  CONSTRAINT fk_sec_signon_to_user  FOREIGN KEY (application_id)
  	REFERENCES sec_user(user_id)   
)

CREATE TABLE sec_signon_hs
(
  signon_id bigint NOT NULL,
  application_id bigint,
  description varchar(255),
  logon_time datetime  ,
  logoff_time datetime ,
  terminal varchar(255),
  user_browser varchar(255),
  user_id bigint,
  uuid varchar(255),
  created_by varchar(32),
  created_on datetime,
  modified_by varchar(255),
  modified_on datetime,


  CONSTRAINT sec_signon_hs_pkey PRIMARY KEY (signon_id )
)

-- table :  sec_id_generator
-- ini untuk generate PK table di modul security
CREATE TABLE sec_id_generator
(
  id_gen varchar(255) NOT NULL,
  nama_tabel varchar(255),
  gen_val bigint,
  CONSTRAINT pk_security_tbl_id_generator PRIMARY KEY (id_gen )
)

CREATE TABLE hibernate_sequences
(
  sequence_name character varying(255) NOT NULL,
  sequence_next_hi_value bigint,
  CONSTRAINT pk_11 PRIMARY KEY (sequence_name)
)