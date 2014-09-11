select distinct rsrc_id2 from rsrc_to_rsrc_rel where REL_TYP_CD='nqproduct';

select * from resourc where rsrc_id in
(select distinct rsrc_id2 from rsrc_to_rsrc_rel where REL_TYP_CD='nqproduct');

select * from presentation where prsnttn_id in 
(select distinct rsrc_id2 from rsrc_to_rsrc_rel where REL_TYP_CD='nqproduct');

select * from presentation where prsnttn_id in ('Implied_SA_205', 'SLS');

select distinct rsrc_id2 from rsrc_to_rsrc_rel where REL_TYP_CD='nqproduct';


select P.*, R.* from resourc R, presentation P where R.rsrc_id in 
  (select distinct rsrc_id1 from rsrc_to_rsrc_rel where REL_TYP_CD='nqproduct')
  and R.prsnttn_id=P.prsnttn_id;

select * from relship_typ_code;