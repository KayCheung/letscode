select * from PRESENTATION where PRSNTTN_LABEL like 'VisProactive - GTPc';
select * from PRESENTATION where PRSNTTN_LABEL like 'File Upload Status';





select prsn.PRSNTTN_ID, prsn.PRSNTTN_LABEL,rsc.* from resourc rsc, PRESENTATION prsn
where (prsn.PRSNTTN_LABEL in('VisProactive - GTPc') and rsc.PRSNTTN_ID = prsn.PRSNTTN_ID);

select * from PRESENTATION where PRSNTTN_id like 'Subscriber_Menu';
select * from resourc where PRSNTTN_ID = 'Subscriber_Menu';
SELECT * FROM PRESENTATION where PRSNTTN_LABEL like 'Top Level%';

select RSRC_ID1, RSRC_TYP_CD, REL_TYP_CD, RSRC_ID2 from RSRC_TO_RSRC_REL
where RSRC_ID1 in ('VisProactive_GTPC') or RSRC_ID2 in('VisProactive_GTPC');
select 

select * from RSRC_TO_RSRC_REL where RSRC_ID2 ='Subscriber_Menu';
select * from resourc where RSRC_ID in (select RSRC_ID1 from RSRC_TO_RSRC_REL where RSRC_ID2 ='Subscriber_Menu');


select * from PRESENTATION where PRSNTTN_LABEL like 'Subscriber - Premium Customer Services';

select prsn.*,rsc.* from resourc rsc, PRESENTATION prsn
where 
( rsc.rsrc_id in (select RSRC_ID1 from RSRC_TO_RSRC_REL where RSRC_ID2 in('VisProactive_GTPC') )
and rsc.PRSNTTN_ID = prsn.PRSNTTN_ID);


  
  select RSRC_ID1, RSRC_TYP_CD, REL_TYP_CD, RSRC_ID2 from RSRC_TO_RSRC_REL
  where RSRC_ID1 in ('Subscriber_PCS_Group','SUBSCRIBER_Group_Co') or RSRC_ID2 in('SUBSCRIBER_Group_Co','SUBSCRIBER_Group_Co');
  
  
select prsn.PRSNTTN_ID, prsn.PRSNTTN_LABEL,rsc.* from resourc rsc, PRESENTATION prsn
where 
(rsc.RSRC_ID in('PCS_GUI_Co','VisPro_PCS','SUBSCRIBER_PCS_Co')
and rsc.PRSNTTN_ID = prsn.PRSNTTN_ID); 

select * from resourc where RSRC_ID in('PCS_GUI_Co','VisPro_PCS','SUBSCRIBER_PCS_Co');
  