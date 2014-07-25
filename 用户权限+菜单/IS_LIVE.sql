--1. VisPro_Diameter_userP,3(permissiongroup)
update resourc set IS_LIVE='1' where RSRC_ID='VisPro_Diameter_userP' and RSRC_TYP_CD='3';

--2. VisPro_Diameter_cmpyP,2(permission)
update resourc set IS_LIVE='1' where RSRC_ID='VisPro_Diameter_cmpyP' and RSRC_TYP_CD='2';

--3. VisPro_Diameter_Alert_Pod_View_userP,2(permission)
update resourc set IS_LIVE='1' where RSRC_ID='VisPro_Diameter_Alert_Pod_View_userP' and RSRC_TYP_CD='2';

--4. VisPro_Diameter_Heat_Map_Pod_View_userP,2(permission)
update resourc set IS_LIVE='1' where RSRC_ID='VisPro_Diameter_Heat_Map_Pod_View_userP' and RSRC_TYP_CD='2';

--5. VisPro_Diameter_Menu,4(presentationgroup)
update resourc set IS_LIVE='1' where RSRC_ID='VisPro_Diameter_Menu' and RSRC_TYP_CD='4';

--6. VisPro_Diameter_Alert_Pod_menuitem,4(presentationgroup)
update resourc set IS_LIVE='1' where RSRC_ID='VisPro_Diameter_Alert_Pod_menuitem' and RSRC_TYP_CD='4';

--7. VisPro_Diameter_Heat_Map_Pod_menuitem,4(presentationgroup)
update resourc set IS_LIVE='1' where RSRC_ID='VisPro_Diameter_Heat_Map_Pod_menuitem' and RSRC_TYP_CD='4';

