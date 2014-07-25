RSRC_NAME
PRSNTTN_LABEL
哪里写出去国际化字符



--Company permission--Root（先找到 presentation_id）
select * from PRESENTATION where PRSNTTN_LABEL like 'VisProactive - GTPc';

-- 在到resourc中，找到 rsrc_id
select prsn.PRSNTTN_ID, prsn.PRSNTTN_LABEL,rsc.* from resourc rsc, PRESENTATION prsn
where (prsn.PRSNTTN_LABEL in('VisProactive - GTPc') and rsc.PRSNTTN_ID = prsn.PRSNTTN_ID);

-- 在 rsrc_to_rsrc_rel中。看 Company permission--Root 下面关联这啥
-- RSRC_ID2 才是父（我也不知道为什么啊）
-- 得到 rsrc_id1就是 直接子节点
select RSRC_ID1, RSRC_TYP_CD, REL_TYP_CD, RSRC_ID2 from RSRC_TO_RSRC_REL
where RSRC_ID1 in ('VisProactive_GTPC') or RSRC_ID2 in('VisProactive_GTPC');


--看看 子 对应 resourc和presentation都得到了
select prsn.*,rsc.* from resourc rsc, PRESENTATION prsn
where ( rsc.rsrc_id in (select RSRC_ID1 from RSRC_TO_RSRC_REL where RSRC_ID2 in('VisProactive_GTPC') )
and rsc.PRSNTTN_ID = prsn.PRSNTTN_ID);

--先加 presentation，再加 resourc，再加 rel表

--所有的 presentation
select * from PRESENTATION where PRSNTTN_ID 
in
  (
    select PRSNTTN_ID from resourc where rsrc_id in ( select RSRC_ID1 from RSRC_TO_RSRC_REL where RSRC_ID2 in('VisProactive_GTPC')  
    UNION
    select 'VisProactive_GTPC' from dual)
  ) 
order by PRSNTTN_ORDER;

  --所有的 resourc
select * from resourc where rsrc_id in ( select RSRC_ID1 from RSRC_TO_RSRC_REL where RSRC_ID2 in('VisProactive_GTPC')  
    UNION
    select 'VisProactive_GTPC' from dual);

--所有的 rel
select * from RSRC_TO_RSRC_REL where RSRC_ID2 in('VisProactive_GTPC');




select * from PRESENTATION where PRSNTTN_LABEL like 'Options';

-- Options的 PRSNTTN_ID='Vis_Options_Menu', rsrc_id='2282'
select prsn.PRSNTTN_ID, prsn.PRSNTTN_LABEL,rsc.* from resourc rsc, PRESENTATION prsn
where 
( prsn.PRSNTTN_ID in (select PRSNTTN_ID from PRESENTATION where PRSNTTN_LABEL like 'Options' )
and rsc.PRSNTTN_ID = prsn.PRSNTTN_ID);

select * from PRESENTATION where PRSNTTN_ID 
in
  (
    select PRSNTTN_ID from resourc where rsrc_id in ( select RSRC_ID1 from RSRC_TO_RSRC_REL where RSRC_ID2 in('Vis_Options_Menu')  
                                                      UNION
                                                      select '2282' from dual)
  ) 
order by PRSNTTN_ORDER;



  --所有的 resourc
select * from resourc where rsrc_id in ( select RSRC_ID1 from RSRC_TO_RSRC_REL where RSRC_ID2 in('Vis_Options_Menu')  
                                                      UNION
                                                      select '2282' from dual);
													  													  
--所有的 rel
select * from RSRC_TO_RSRC_REL where RSRC_ID2 in('Vis_Options_Menu');
													  







