-- Heat Map Pod View
--select * from XROADS_OWNER.rsrc_to_rsrc_rel where RSRC_ID1='VisProactive_Diameter_Heat_Map_Pod_View' and RSRC_ID2='VisProactive_Diameter' and REL_TYP_CD='PermissionGroup' and RSRC_TYP_CD='2';
delete from XROADS_OWNER.rsrc_to_rsrc_rel where RSRC_ID1='VisProactive_Diameter_Heat_Map_Pod_View' and RSRC_ID2='VisProactive_Diameter' and REL_TYP_CD='PermissionGroup' and RSRC_TYP_CD='2';

-- Alert Pod View
--select * from XROADS_OWNER.rsrc_to_rsrc_rel where RSRC_ID1='VisProactive_Diameter_Alert_Pod_View' and RSRC_ID2='VisProactive_Diameter' and REL_TYP_CD='PermissionGroup' and RSRC_TYP_CD='2';
delete from XROADS_OWNER.rsrc_to_rsrc_rel where RSRC_ID1='VisProactive_Diameter_Alert_Pod_View' and RSRC_ID2='VisProactive_Diameter' and REL_TYP_CD='PermissionGroup' and RSRC_TYP_CD='2';

-- Heat Map View
--select * from RESOURC where RSRC_ID='VisProactive_Diameter_Heat_Map_Pod_View' and RSRC_TYP_CD='2';
delete from RESOURC where RSRC_ID='VisProactive_Diameter_Heat_Map_Pod_View' and RSRC_TYP_CD='2';

-- Alert Pod View
--select * from RESOURC where RSRC_ID='VisProactive_Diameter_Alert_Pod_View' and RSRC_TYP_CD='2';
delete from RESOURC where RSRC_ID='VisProactive_Diameter_Alert_Pod_View' and RSRC_TYP_CD='2';

-- Diameter View
--select * from RESOURC where RSRC_ID='VisProactive_Diameter' and RSRC_TYP_CD='3';
delete from RESOURC where RSRC_ID='VisProactive_Diameter' and RSRC_TYP_CD='3';


-- Heat Map View
--select * from XROADS_OWNER.presentation where PRSNTTN_ID='VisProactive_Diameter_Heat_Map_Pod_View';
delete from XROADS_OWNER.presentation where PRSNTTN_ID='VisProactive_Diameter_Heat_Map_Pod_View';

-- Alert Pod View
--select * from XROADS_OWNER.presentation where PRSNTTN_ID='VisProactive_Diameter_Alert_Pod_View';
delete from XROADS_OWNER.presentation where PRSNTTN_ID='VisProactive_Diameter_Alert_Pod_View';

-- Diameter View
--select * from XROADS_OWNER.presentation where PRSNTTN_ID='VisProactive_Diameter';
delete from XROADS_OWNER.presentation where PRSNTTN_ID='VisProactive_Diameter';
















