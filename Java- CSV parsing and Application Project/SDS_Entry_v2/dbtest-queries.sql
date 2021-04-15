-- Question1-- 
select status, SUM(claimed_charge) from document where status='EXPORTED';

-- Question2--
create table test_batch (
	id int ,
	customer_id int ,
	type varchar(15) ,
	received_date date ,
	status varchar(15) ,
	completed_date date ,
	PRIMARY KEY (id)
);


insert into test_batch
select * from batch where customer_id=1  or customer_id=2 ;

create table test_document (
	id int ,
	batch_id int ,
	status varchar(15) ,
	insured_name varchar(30) ,
	insured_address varchar(30) ,
	claimed_charge double ,
	PRIMARY KEY (id),
 	KEY batch_id (batch_id),
  	CONSTRAINT document_ibfk_2 FOREIGN KEY (batch_id) REFERENCES test_batch (id)
);

insert into test_document
select *
from document
where status='TO_REPRICE';

select insured_name,insured_address,claimed_charge
from test_batch,test_document
where test_document.batch_id=test_batch.id;