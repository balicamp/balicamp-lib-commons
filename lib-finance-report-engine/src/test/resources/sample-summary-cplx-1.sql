select 'Pendapatan 1' label , m.segment2 data_code,    sum(bal.end_balance) ttl
from 
	t_jrn_ledger_balance bal inner join 
	m_main_account m on bal.main_account_id = m.main_account_id 
where 
	1=1
	AND m.segment2 = '1.01'
	AND financial_year =2013 
	AND branch_id = 1 
	and financial_period = 2
group by 	m.segment2 