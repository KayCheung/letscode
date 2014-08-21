
select rsrc_id1 from rsrc_to_rsrc_rel where rsrc_id2='Subscriber_PCS_Group';


select rsrc_id1 from rsrc_to_rsrc_rel where rsrc_id2='PCS_GUI_Co';

select * from resourc R, presentation P where R.PRSNTTN_ID=P.PRSNTTN_ID 
and rsrc_id like 'Acc%' and P.prsnttn_label like 'Impli%';

-- Implied permission always have prsnttn_order as 0000
select prsnttn_label, prsnttn_id, prsnttn_order from presentation 
where prsnttn_label like 'Impli%' and prsnttn_order !='0000'; 


select * from presentation where prsnttn_id = 'PCS_GUI_Monitor_Subscribers_Menu';

select * from resourc R, presentation P where R.PRSNTTN_ID=P.PRSNTTN_ID 
and P.prsnttn_id='PCS_GUI_Monitor_Subscribers_Menu';

select * from rsrc_to_rsrc_rel where rsrc_id1='5472';

select * from rsrc_to_rsrc_rel where rsrc_id2='Premium_Customer_Service_Menu';

--new link "Analyzer PCS Reporting"'s prsnttn_order
select * from resourc R, presentation P where R.PRSNTTN_ID=P.PRSNTTN_ID 
and R.rsrc_id in (select rsrc_id1 from rsrc_to_rsrc_rel where rsrc_id2='Premium_Customer_Service_Menu');


--new user permission "Analyzer PCS Reporting"'s prsnttn_order
select * from resourc R, presentation P where R.PRSNTTN_ID=P.PRSNTTN_ID 
and R.rsrc_id in (select rsrc_id1 from rsrc_to_rsrc_rel where rsrc_id2='Subscriber_PCS_Group');



--所有的 Implied permission，且 prsnttn_label是 Impli开头的
select rsrc_id, p.prsnttn_id, prsnttn_label from resourc R, presentation P where R.PRSNTTN_ID=P.PRSNTTN_ID 
and R.rsrc_id in (select rsrc_id2 from rsrc_to_rsrc_rel where REL_TYP_CD='Implied')
and P.PRSNTTN_LABEL  like 'Impl%';

--Implied permission，且，prsnttn_label不是 Impli开头
select rsrc_id, p.prsnttn_id, prsnttn_label from resourc R, presentation P where R.PRSNTTN_ID=P.PRSNTTN_ID 
and R.rsrc_id in (select rsrc_id2 from rsrc_to_rsrc_rel where REL_TYP_CD='Implied')
and P.PRSNTTN_LABEL not like 'Impl%';


--new user permission "Analyzer PCS Reporting"'s prsnttn_order
select * from resourc R, presentation P where R.PRSNTTN_ID=P.PRSNTTN_ID 
and R.rsrc_id in ('SUBSCRIBER', 'A-1');





