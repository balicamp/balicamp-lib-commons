-- postgre sql



-- regex based password validation
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
  old_password_base character varying(255),
  password_age smallint,
  regex_desc character varying(255),
  regular_expression character varying(255),
  
  created_by character varying(255),
  created_on timestamp without time zone,
  modified_by character varying(255),
  modified_on timestamp without time zone,
  
  CONSTRAINT sec_password_policy_pkey PRIMARY KEY (password_policy_id )
)
WITH (
  OIDS=FALSE
);





-- sec_application. ini daftar aplikasi yang ada
CREATE TABLE sec_application
(
  application_id bigint NOT NULL,
  
  application_code character varying(255),
  application_name character varying(255),
  application_url character varying(255),
  aut_login_url character varying(255),
  kick_prev_concurent_login character varying(255),
  lang character varying(255),
  notify_succed_url character varying(255),
  session_timeout integer,
  status character varying(1),
  
  
  created_by character varying(255),
  created_on timestamp without time zone,
  modified_by character varying(255),
  modified_on timestamp without time zone,
  CONSTRAINT sec_application_pkey PRIMARY KEY (application_id )
)WITH (
  OIDS=FALSE
); 



-- ini sec_user. user + password
-- kalau dalam 1 database ada lebih dari 1 app, pls di check constraint : 
-- 1. fk_user_to_default_application
-- 2. sec_user_pkey
CREATE TABLE sec_user
(
  user_id bigint NOT NULL,
  user_code character varying(50) NOT NULL,
  expired_date date,
  birthdate date,
  datetime_pattern character varying(20) DEFAULT 'dd MMM yyyy'::character varying,
  timezone character varying(20) DEFAULT 'Asia/Jakarta'::character varying,
  decimal_separator character(1) DEFAULT '.'::bpchar,
  numeric_scale smallint DEFAULT 4::smallint,
  email character varying(50) DEFAULT ' '::character varying,
  currency character varying(3) DEFAULT 'IDR'::character varying,
  max_rows_per_page smallint DEFAULT 20::smallint,
  failed_login_attempts smallint DEFAULT 0::smallint,
  real_name character varying(100) DEFAULT NULL::character varying,
  status character(1) DEFAULT 'A'::bpchar,
  locale character varying(10) DEFAULT 'en_US'::character varying,
  default_application_id bigint,
  active_status character(1) DEFAULT 'A'::bpchar,
  login_status character varying(1) DEFAULT '0'::character varying,
  chipper_text character varying(128) DEFAULT NULL::character varying,
  is_ntlm_user character(1) NOT NULL DEFAULT 'Y'::bpchar, -- Flag, user NTLM atau user atau bukan
  is_super_admin character(1) NOT NULL DEFAULT 'N'::bpchar, -- flag, super user
  
  created_by character varying(30) DEFAULT NULL::character varying,
  created_on timestamp without time zone NOT NULL DEFAULT now(),
  modified_by character varying(50) DEFAULT NULL::character varying,
  modified_on timestamp without time zone,
  CONSTRAINT sec_user_pkey PRIMARY KEY (user_id ),
  CONSTRAINT fk_user_to_default_application FOREIGN KEY (default_application_id)
      REFERENCES sec_application (application_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

COMMENT ON COLUMN sec_user.is_ntlm_user IS 'Flag, user NTLM atau user atau bukan';
COMMENT ON COLUMN sec_user.is_super_admin IS 'flag, super user';




-- ini table password history. ini kalau di terapkan kebijakan password tidak boleh sama dengan n password tertentu etc
CREATE TABLE sec_password_hs
(
  password_id numeric(19,0) NOT NULL,
  user_id bigint,
  cipher_text character varying(255),
  effective_date timestamp without time zone,
  
  created_by character varying(255),
  created_on timestamp without time zone,
  CONSTRAINT sec_password_hs_pkey PRIMARY KEY (password_id )
)
WITH (
  OIDS=FALSE
);


COMMENT ON COLUMN sec_password_hs.password_id is 'ini primary key dari password';  





-- mapping user vs aplikasi. ini unutk logical view user dari app
CREATE TABLE sec_application_user
(
  application_id bigint NOT NULL,
  user_id bigint NOT NULL,
  CONSTRAINT sec_application_user_pkey PRIMARY KEY (application_id , user_id ), 
  CONSTRAINT fk_app_user_to_user FOREIGN KEY (user_id)
   		REFERENCES sec_user (user_id) MATCH SIMPLE
      	ON UPDATE NO ACTION ON DELETE NO ACTION ,
  CONSTRAINT fk_app_user_to_app  FOREIGN KEY (application_id)
  	REFERENCES sec_application (application_id) MATCH SIMPLE
      	ON UPDATE NO ACTION ON DELETE NO ACTION      	
)
WITH (
  OIDS=FALSE
);


-- kode cabang user
CREATE TABLE sec_branch
(
  branch_id bigint NOT NULL,
  branch_address character varying(255),
  branch_code character varying(255),
  branch_name character varying(255),
  branch_id_parent bigint,
  description character varying(255),
  status character varying(1),
  
  created_by character varying(255),
  created_on timestamp without time zone,
  modified_by character varying(255),
  modified_on timestamp without time zone,
  
  CONSTRAINT sec_branch_pkey PRIMARY KEY (branch_id )
)
WITH (
  OIDS=FALSE
);


-- user berada di branch mana
CREATE TABLE sec_branch_assignment
(
  branch_assignment_id bigint NOT NULL,
  user_id bigint,
  branch_id bigint,
  
  created_by character varying(255),
  created_on timestamp without time zone,
  
  CONSTRAINT sec_branch_assignment_pkey PRIMARY KEY (branch_assignment_id ) , 
  CONSTRAINT fk_branch_assgn_to_user  FOREIGN KEY (user_id)
  	REFERENCES sec_user (user_id) MATCH SIMPLE
      	ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


-- table : sec_group , master group of user

CREATE TABLE sec_group
(
  group_id bigint NOT NULL,
  application_id bigint,
  group_code character varying(255),
  group_name character varying(255),
  status character varying(1),
  is_super_group character (1),
  
  created_by character varying(255),
  created_on timestamp without time zone,
  modified_by character varying(255),
  modified_on timestamp without time zone,
  
  CONSTRAINT sec_group_pkey PRIMARY KEY (group_id ) , 
  CONSTRAINT fk_group_to_app_table  FOREIGN KEY (application_id)
  	REFERENCES sec_application (application_id) MATCH SIMPLE
      	ON UPDATE NO ACTION ON DELETE NO ACTION
  
)
WITH (
  OIDS=FALSE
);

COMMENT ON COLUMN sec_group.group_id is 'primary key dari table sec_group'; 
COMMENT ON COLUMN sec_group.application_id is 'reference ke application';
COMMENT ON COLUMN sec_group.group_code is 'Kode group. ini apa yang visible bagi user';
COMMENT ON COLUMN sec_group.group_name is 'nama group';
COMMENT ON COLUMN sec_group.status is 'flag status, A, non active'; 
COMMENT ON COLUMN sec_group.is_super_group is 'super group atau bukan dalam app'; 

-- table : sec_group_assignment
-- ini map antara user + group mana dia berada
CREATE TABLE sec_group_assignment
(
  group_assignment_id bigint NOT NULL,
  group_id bigint,
  user_id bigint,
  
  created_by character varying(255),
  created_on timestamp without time zone,
  
  CONSTRAINT sec_group_assignment_pkey PRIMARY KEY (group_assignment_id ) , 
  CONSTRAINT fk_grp_assg_to_user  FOREIGN KEY (user_id)
  		REFERENCES sec_user(user_id) MATCH SIMPLE
      	ON UPDATE NO ACTION ON DELETE NO ACTION , 
  CONSTRAINT fk_grp_assg_to_group  FOREIGN KEY (group_id)
  		REFERENCES sec_group(group_id) MATCH SIMPLE
      	ON UPDATE NO ACTION ON DELETE NO ACTION 
      	
      	
)
WITH (
  OIDS=FALSE
);


-- table : sec_page_definition
-- ini definisi page / panel
CREATE TABLE sec_page_definition
(
  page_id bigint NOT NULL,
  application_id bigint,
  page_code character varying(255),
  page_url character varying(255),
  additional_data character varying(255),
  page_remark character varying(255),
  created_by character varying(255),
  created_on timestamp without time zone,
  
  
  CONSTRAINT sec_page_definition_pkey PRIMARY KEY (page_id ) ,
  CONSTRAINT fk_page_def_to_app  FOREIGN KEY (application_id)
  	REFERENCES sec_application(application_id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION 
)
WITH (
  OIDS=FALSE
);

COMMENT ON COLUMN sec_page_definition.page_remark is 'catatan halaman, ini untuk editor'; 

-- table : sec_function
-- Master menu yang tersedua dalam app
CREATE TABLE sec_function
(
  function_id bigint NOT NULL,
  application_id bigint,
  function_code character varying(255),
  function_id_parent bigint,
  function_label character varying(255),
  menu_tree_code character varying(255),
  page_id bigint,
  sibling_order numeric(19,2),
  status character varying(1),
  tree_level_position integer,
  
  created_by character varying(255),
  created_on timestamp without time zone,
  modified_by character varying(255),
  modified_on timestamp without time zone,
  
  CONSTRAINT sec_function_pkey PRIMARY KEY (function_id ), 
  CONSTRAINT fk_function_to_application  FOREIGN KEY (application_id)
  	REFERENCES sec_application(application_id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION ,
    
  CONSTRAINT fk_function_to_page_def  FOREIGN KEY (page_id)
  	REFERENCES sec_page_definition(page_id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

-- table : sec_function_assignment
-- ini function vs group. group x dpt menu Y
CREATE TABLE sec_function_assignment
(
  function_assignment_id bigint NOT NULL,
  function_id numeric(19,2),
  group_id numeric(19,2),
  
  created_by character varying(255),
  created_on timestamp without time zone,
  CONSTRAINT sec_function_assignment_pkey PRIMARY KEY (function_assignment_id )
)
WITH (
  OIDS=FALSE
);


CREATE TABLE sec_signon
(
  signon_id bigint NOT NULL ,
  application_id bigint,
  description character varying(255),
  logon_time timestamp without time zone,
  logoff_time timestamp without time zone,
  terminal character varying(255),
  user_browser character varying(255),
  user_id bigint ,
  uuid character varying(255),
  
   created_by character varying(255),
  created_on timestamp without time zone,
  modified_by character varying(255),
  modified_on timestamp without time zone,
  
  
  CONSTRAINT sec_signon_pkey PRIMARY KEY (signon_id ) , 
  CONSTRAINT fk_sec_signon_to_app  FOREIGN KEY (application_id)
  	REFERENCES sec_application(application_id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION , 
  CONSTRAINT fk_sec_signon_to_user  FOREIGN KEY (application_id)
  	REFERENCES sec_user(user_id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION 
)
WITH (
  OIDS=FALSE
);

CREATE TABLE sec_signon_hs
(
  signon_id bigint NOT NULL,
  application_id bigint,
  description character varying(255),
  logon_time timestamp without time zone,
  logoff_time timestamp without time zone,
  terminal character varying(255),
  user_browser character varying(255),
  user_id bigint,
  uuid character varying(255),
  
   created_by character varying(255),
  created_on timestamp without time zone,
  modified_by character varying(255),
  modified_on timestamp without time zone,
  
  
  CONSTRAINT sec_signon_hs_pkey PRIMARY KEY (signon_id )
)
WITH (
  OIDS=FALSE
);


-- table :  sec_id_generator
-- ini untuk generate PK table di modul security
CREATE TABLE sec_id_generator
(
  id_gen character varying(255) NOT NULL,
  nama_tabel character varying(255),
  gen_val bigint,
  CONSTRAINT pk_security_tbl_id_generator PRIMARY KEY (id_gen )
)
WITH (
  OIDS=FALSE
);


