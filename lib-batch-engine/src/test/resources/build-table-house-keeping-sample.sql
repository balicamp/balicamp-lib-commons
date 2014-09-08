drop table tes_house_keeping_detail IF EXISTS;
drop table test_house_keeping IF EXISTS; 



create table test_house_keeping(
pk bigint primary key , 
str_field varchar(32)  ,
date_field date ,  
timestamp_field date 
); 


create table tes_house_keeping_detail ( 
pk bigint IDENTITY primary key , 
parent_id bigint , 
email varchar(256),

);
