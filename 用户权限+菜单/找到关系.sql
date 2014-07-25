select * from PRESENTATION where PRSNTTN_LABEL like 'VisProactive - GTPc';
select * from PRESENTATION where PRSNTTN_LABEL like 'File Upload Status';





select prsn.PRSNTTN_ID, prsn.PRSNTTN_LABEL,rsc.* from resourc rsc, PRESENTATION prsn
where (prsn.PRSNTTN_LABEL in('VisProactive - GTPc') and rsc.PRSNTTN_ID = prsn.PRSNTTN_ID);

select * from PRESENTATION where PRSNTTN_id like 'Subscriber_Menu';
select * from resourc where PRSNTTN_ID = 'Subscriber_Menu';
SELECT * FROM PRESENTATION where PRSNTTN_LABEL like 'Top Level%';

select RSRC_ID1, RSRC_TYP_CD, REL_TYP_CD, RSRC_ID2 from RSRC_TO_RSRC_REL
where RSRC_ID1 in ('VisProactive_GTPC') or RSRC_ID2 in('VisProactive_GTPC');


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


-- User Permission，所有的VisProactive - xxx
select * from presentation where prsnttn_label in 
('VisProactive - GTPc', 'VisProactive - Mobile Data Roaming', 'Premium Customer Services', 'VisProactive - Rev. C',
'VisProactive - SS7 CDMA', 'VisProactive - SS7 GSM', 'VisProactive - UniRoam Data Roaming')
order by PRSNTTN_ORDER;

-- User Permission, VisProactive - xxx中的各种 prsnttn_order
select prsn.*, res.* from presentation prsn, resourc res where prsn.prsnttn_id=res.prsnttn_id
and prsn.prsnttn_label in (
    select prsnttn_label from presentation where prsnttn_label in 
    ('VisProactive - GTPc', 'VisProactive - Mobile Data Roaming', 'Premium Customer Services', 'VisProactive - Rev. C',
    'VisProactive - SS7 CDMA', 'VisProactive - SS7 GSM', 'VisProactive - UniRoam Data Roaming',
    'Visibility Services - Visitor/Subscriber Reports')
);

-- VisPro和VisSvc。。。。。找出这两个RSRC_ID
select prsn.*, res.* from presentation prsn, resourc res where
prsn.prsnttn_id=res.prsnttn_id and res.rsrc_typ_cd='1'
and prsn.prsnttn_label in (
        select prsnttn_label from presentation where prsnttn_label like 'Vis%'
);

select prsn.*, res.* from presentation prsn, resourc res where prsn.prsnttn_id=res.prsnttn_id
and rsrc_id in('VisPro','VisPro_Vis');




-- User Permission "Visibility Services - Visitor/Subscriber Reports"(permissiongroup) 的所有下属 permission
select prsn.*, res.* from presentation prsn, resourc res where prsn.prsnttn_id=res.prsnttn_id
and rsrc_id in (
      select rsrc_id1 from rsrc_to_rsrc_rel where rsrc_id2 in (
            select rsrc_id from presentation prsn, resourc res where
            prsn.prsnttn_id=res.prsnttn_id and prsn.prsnttn_label in ('Visibility Services - Visitor/Subscriber Reports')
            )
) order by prsn.prsnttn_order;

-- User Permission "VisProactive - GTPc"(permissiongroup) 的所有下属 permission
select prsn.*, res.* from presentation prsn, resourc res where prsn.prsnttn_id=res.prsnttn_id
and rsrc_id in (
      select rsrc_id1 from rsrc_to_rsrc_rel where rsrc_id2 in (
            select rsrc_id from presentation prsn, resourc res where
            prsn.prsnttn_id=res.prsnttn_id and prsn.prsnttn_label in ('VisProactive - GTPc')
            )
) order by prsn.prsnttn_order;

-- User Permission。。。"VisProactive - GTPc"的父级菜单是
      select * from rsrc_to_rsrc_rel where rsrc_id2 in (
            select rsrc_id from presentation prsn, resourc res where
            prsn.prsnttn_id=res.prsnttn_id and prsn.prsnttn_label in ('VisProactive - GTPc')
            );
            




select prsn.PRSNTTN_ID, prsn.PRSNTTN_LABEL,rsc.* from resourc rsc, PRESENTATION prsn
where (prsn.PRSNTTN_LABEL in('Visibility Services - Visitor/Subscriber Reports') and rsc.PRSNTTN_ID = prsn.PRSNTTN_ID);

--1. 菜单：Common Subscriber Profile
--2. prsn_id: Subscriber_Common_Profile_Menu
--3. rsrc_id: 5379
select prsn.PRSNTTN_ID, prsn.PRSNTTN_LABEL,rsc.* from resourc rsc, PRESENTATION prsn
where (prsn.PRSNTTN_LABEL in('Common Subscriber Profile') and rsc.PRSNTTN_ID = prsn.PRSNTTN_ID);

--1. 顶级Subscriber tab 菜单：Common Subscriber Profile 的 父级菜单
--2. prsn_id: Subscriber_Menu
--3. rsrc_id: 2982
select prsn.*,rsc.* from resourc rsc, PRESENTATION prsn 
where rsc.PRSNTTN_ID = prsn.PRSNTTN_ID
      and prsn.PRSNTTN_id in (select rsrc_id2 from rsrc_to_rsrc_rel where RSRC_ID1 in ('5379'));

--1. 顶级Subscriber tab 的下头
--2. 这个没啥用。就是看看 顶级Subscriber tab 下面的顶级菜单
--3. 顶级Subscriber tab 菜单 下面 有什么？
select prsn.*, res.* from presentation prsn, resourc res where prsn.prsnttn_id=res.prsnttn_id
and rsrc_id in (
      select rsrc_id1 from rsrc_to_rsrc_rel where rsrc_id2 in ('Subscriber_Menu')
) order by prsn.prsnttn_order;

--1. 顶级Subscriber tab，它的上头
--2. “顶级Subscriber tab 的上头”。 看上头有人吗？
--3. “顶级Subscriber tab”，挂靠在：
-- prsn_id: 9001
-- rsrc_id: SUBSCRIBER, rsrc_typ_cd==1(product), prod_menu_category==Services
select prsn.*, res.* from presentation prsn, resourc res where prsn.prsnttn_id=res.prsnttn_id
and rsrc_id in (
      select rsrc_id2 from rsrc_to_rsrc_rel where rsrc_id1 in ('2982')
) order by prsn.prsnttn_order;






--1. Company Permission：Common Subscriber Profile
--2. prsn_id: 15083
--3. rsrc_id: Common_Subscriber_Profile_Co
select prsn.PRSNTTN_ID, prsn.PRSNTTN_LABEL,rsc.* from resourc rsc, PRESENTATION prsn
where (prsn.PRSNTTN_LABEL in('Common Subscriber Profile') and rsc.PRSNTTN_ID = prsn.PRSNTTN_ID);


--1. "Common_Subscriber_Profile_Co"的上级 ： Company Permission：Common Subscriber Profile
--2. prsn_id: 14923, prsn_label: Subscriber - Configuration
--3. rsrc_id: Subscriber_Config_Group_Co
select prsn.*,rsc.* from resourc rsc, PRESENTATION prsn 
where rsc.PRSNTTN_ID = prsn.PRSNTTN_ID
      and rsc.rsrc_id in (select rsrc_id2 from rsrc_to_rsrc_rel where RSRC_ID1 in ('Common_Subscriber_Profile_Co'));

--1. Company Permission, "Subscriber - Configuration"的上级。。。。。。没找到
--Marvin: 难道说，company permission的顶级，是不需要关联谁的？
select prsn.*,rsc.* from resourc rsc, PRESENTATION prsn 
where rsc.PRSNTTN_ID = prsn.PRSNTTN_ID
      and rsc.rsrc_id in (select rsrc_id2 from rsrc_to_rsrc_rel where RSRC_ID1 in ('Subscriber_Config_Group_Co'));




--1. User Permission：Subscriber - Common Profile
--2. prsn_id: 15082, prsn_label: Subscriber - Common Profile
--3. rsrc_id: Subscriber_Common_Profile, rsrc_typ_cd==3(permissiongroup)
select prsn.PRSNTTN_ID, prsn.PRSNTTN_LABEL,rsc.* from resourc rsc, PRESENTATION prsn
where (prsn.PRSNTTN_LABEL in('Subscriber - Common Profile') and rsc.PRSNTTN_ID = prsn.PRSNTTN_ID);

--1. User Permission: "Subscriber - Common Profile的孩子们"
--2. Admin, Modify, View
select prsn.*,rsc.* from resourc rsc, PRESENTATION prsn 
where rsc.PRSNTTN_ID = prsn.PRSNTTN_ID
      and rsc.rsrc_id in (select rsrc_id1 from rsrc_to_rsrc_rel where RSRC_ID2 in ('Subscriber_Common_Profile'));

--1. User Permission: "Subscriber - Common Profile的父"
--2. Admin, Modify, View
select prsn.*,rsc.* from resourc rsc, PRESENTATION prsn 
where rsc.PRSNTTN_ID = prsn.PRSNTTN_ID
      and rsc.rsrc_id in (select rsrc_id2 from rsrc_to_rsrc_rel where RSRC_ID1 in ('Subscriber_Common_Profile'));

select prsn.PRSNTTN_ID, prsn.PRSNTTN_LABEL,rsc.* from resourc rsc, PRESENTATION prsn
where (prsn.PRSNTTN_LABEL in('Subscriber - Common Profile') and rsc.PRSNTTN_ID = prsn.PRSNTTN_ID);


select * from rsrc_to_rsrc_rel where rsrc_id2 in ('Subscriber_Menu');

select prsn.*, res.* from presentation prsn, resourc res where prsn.prsnttn_id=res.prsnttn_id
and rsrc_id in (
      select rsrc_id1 from rsrc_to_rsrc_rel where rsrc_id2 in ('Subscriber_Menu')
) order by prsn.prsnttn_order;

select * from rsrc_to_rsrc_rel where rsrc_id1 in ('Common_Subscriber_Profile_Co');























  