-- Heat Map Pod
--select * from XROADS_OWNER.rsrc_to_rsrc_rel where RSRC_ID1='VisProactive_Diameter_Heat_Map_Pod' and RSRC_ID2='VisProactive_Diameter_4LeftMemu' and REL_TYP_CD='PresGroup' and RSRC_TYP_CD='4';
delete from XROADS_OWNER.rsrc_to_rsrc_rel where RSRC_ID1='VisProactive_Diameter_Heat_Map_Pod' and RSRC_ID2='VisProactive_Diameter_4LeftMemu' and REL_TYP_CD='PresGroup' and RSRC_TYP_CD='4';

-- Alert Pod
--select * from XROADS_OWNER.rsrc_to_rsrc_rel where RSRC_ID1='VisProactive_Diameter_Alert_Pod' and RSRC_ID2='VisProactive_Diameter_4LeftMemu' and REL_TYP_CD='PresGroup' and RSRC_TYP_CD='4';
delete from XROADS_OWNER.rsrc_to_rsrc_rel where RSRC_ID1='VisProactive_Diameter_Alert_Pod' and RSRC_ID2='VisProactive_Diameter_4LeftMemu' and REL_TYP_CD='PresGroup' and RSRC_TYP_CD='4';

-- Diameter
--select * from XROADS_OWNER.rsrc_to_rsrc_rel where RSRC_ID1='VisProactive_Diameter' and RSRC_ID2='VisSvc' and REL_TYP_CD='PresGroup' and RSRC_TYP_CD='4';
delete from XROADS_OWNER.rsrc_to_rsrc_rel where RSRC_ID1='VisProactive_Diameter' and RSRC_ID2='VisSvc' and REL_TYP_CD='PresGroup' and RSRC_TYP_CD='4';

-- Heat Map
--select * from RESOURC where RSRC_ID='VisProactive_Diameter_Heat_Map_Pod' and RSRC_TYP_CD='4';
delete from RESOURC where RSRC_ID='VisProactive_Diameter_Heat_Map_Pod' and RSRC_TYP_CD='4';

-- Alert Pod
--select * from RESOURC where RSRC_ID='VisProactive_Diameter_Alert_Pod' and RSRC_TYP_CD='4';
delete from RESOURC where RSRC_ID='VisProactive_Diameter_Alert_Pod' and RSRC_TYP_CD='4';

-- Diameter
--select * from RESOURC where RSRC_ID='VisProactive_Diameter' and RSRC_TYP_CD='4';
delete from RESOURC where RSRC_ID='VisProactive_Diameter' and RSRC_TYP_CD='4';


-- Heat Map
--select * from XROADS_OWNER.presentation where PRSNTTN_ID='VisProactive_Diameter_Heat_Map_Pod';
delete from XROADS_OWNER.presentation where PRSNTTN_ID='VisProactive_Diameter_Heat_Map_Pod';

-- Alert Pod
--select * from XROADS_OWNER.presentation where PRSNTTN_ID='VisProactive_Diameter_Alert_Pod';
delete from XROADS_OWNER.presentation where PRSNTTN_ID='VisProactive_Diameter_Alert_Pod';

-- Diameter
--select * from XROADS_OWNER.presentation where PRSNTTN_ID='VisProactive_Diameter_4LeftMemu';
delete from XROADS_OWNER.presentation where PRSNTTN_ID='VisProactive_Diameter_4LeftMemu';
















