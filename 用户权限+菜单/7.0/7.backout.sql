-- This is the first line
-- ######################################
-- ./delObj permissiongroupid=VisPro_Diameter_userP ou=PermissionGroups,o=Crossroads 

-- ######################################
delete from RSRC_TO_RSRC_REL where RSRC_ID1='VisPro_Diameter_userP'  and 
		RSRC_TYP_CD='3';
delete from RSRC_TO_COMPANY_REL where RSRC_ID='VisPro_Diameter_userP'  
		and RSRC_TYPE_CODE='3';
delete from presentation where PRSNTTN_ID=(select PRSNTTN_ID from resourc where 
		RSRC_ID='VisPro_Diameter_userP' and RSRC_TYP_CD='3');
delete from resourc where RSRC_ID='VisPro_Diameter_userP' and RSRC_TYP_CD='3';

-- ######################################
-- ./delObj permissionid=VisPro_Diameter_cmpyP ou=PermissionCode,o=Crossroads 

-- ######################################
delete from RSRC_TO_RSRC_REL where RSRC_ID1='VisPro_Diameter_cmpyP'  and 
		RSRC_TYP_CD='2';
delete from RSRC_TO_COMPANY_REL where RSRC_ID='VisPro_Diameter_cmpyP'  and 
		RSRC_TYPE_CODE='2';
delete from presentation where PRSNTTN_ID=(select PRSNTTN_ID from resourc where 
		RSRC_ID='VisPro_Diameter_cmpyP' and RSRC_TYP_CD='2');
delete from resourc where RSRC_ID='VisPro_Diameter_cmpyP' and RSRC_TYP_CD='2';

-- ######################################
-- ./delObj permissionid=VisPro_Diameter_Alarm_Pod_View_userP ou=PermissionCode,o=Crossroads 

-- ######################################
delete from RSRC_TO_RSRC_REL where RSRC_ID1='VisPro_Diameter_Alarm_Pod_View_userP'  and 
		RSRC_TYP_CD='2';
delete from RSRC_TO_COMPANY_REL where RSRC_ID='VisPro_Diameter_Alarm_Pod_View_userP'  and 
		RSRC_TYPE_CODE='2';
delete from presentation where PRSNTTN_ID=(select PRSNTTN_ID from resourc where 
		RSRC_ID='VisPro_Diameter_Alarm_Pod_View_userP' and RSRC_TYP_CD='2');
delete from resourc where RSRC_ID='VisPro_Diameter_Alarm_Pod_View_userP' and RSRC_TYP_CD='2';

-- ######################################
-- ./delObj permissionid=VisPro_Diameter_Heat_Map_Pod_View_userP ou=PermissionCode,o=Crossroads 

-- ######################################
delete from RSRC_TO_RSRC_REL where RSRC_ID1='VisPro_Diameter_Heat_Map_Pod_View_userP'  and 
		RSRC_TYP_CD='2';
delete from RSRC_TO_COMPANY_REL where RSRC_ID='VisPro_Diameter_Heat_Map_Pod_View_userP'  and 
		RSRC_TYPE_CODE='2';
delete from presentation where PRSNTTN_ID=(select PRSNTTN_ID from resourc where 
		RSRC_ID='VisPro_Diameter_Heat_Map_Pod_View_userP' and RSRC_TYP_CD='2');
delete from resourc where RSRC_ID='VisPro_Diameter_Heat_Map_Pod_View_userP' and RSRC_TYP_CD='2';

-- ######################################
-- ./delObj uid=VisPro_Diameter_Menu_prsn ou=PresentationObjects,o=Crossroads 

-- ######################################
delete from RSRC_TO_RSRC_REL where RSRC_ID1=(select RSRC_ID from resourc where 
		PRSNTTN_ID='VisPro_Diameter_Menu_prsn' and RSRC_TYP_CD='4') and  RSRC_TYP_CD='4';
delete from RSRC_TO_COMPANY_REL where RSRC_ID=(select RSRC_ID from resourc where 
		PRSNTTN_ID='VisPro_Diameter_Menu_prsn' and RSRC_TYP_CD='4') and  RSRC_TYPE_CODE='4';
delete from resourc where RSRC_ID=(select RSRC_ID from resourc where 
		PRSNTTN_ID='VisPro_Diameter_Menu_prsn' and RSRC_TYP_CD='4') and  RSRC_TYP_CD='4';
delete from presentation where PRSNTTN_ID='VisPro_Diameter_Menu_prsn';

-- ######################################
-- ./delObj uid=VisPro_Diameter_Alarm_Pod_menuitem_prsn ou=PresentationObjects,o=Crossroads 

-- ######################################
delete from RSRC_TO_RSRC_REL where RSRC_ID1=(select RSRC_ID from resourc where 
		PRSNTTN_ID='VisPro_Diameter_Alarm_Pod_menuitem_prsn' and RSRC_TYP_CD='4') and  RSRC_TYP_CD='4';
delete from RSRC_TO_COMPANY_REL where RSRC_ID=(select RSRC_ID from resourc where 
		PRSNTTN_ID='VisPro_Diameter_Alarm_Pod_menuitem_prsn' and RSRC_TYP_CD='4') and  RSRC_TYPE_CODE='4';
delete from resourc where RSRC_ID=(select RSRC_ID from resourc where 
		PRSNTTN_ID='VisPro_Diameter_Alarm_Pod_menuitem_prsn' and RSRC_TYP_CD='4') and  RSRC_TYP_CD='4';
delete from presentation where PRSNTTN_ID='VisPro_Diameter_Alarm_Pod_menuitem_prsn';

-- ######################################
-- ./delObj uid=VisPro_Diameter_Heat_Map_Pod_menuitem_prsn ou=PresentationObjects,o=Crossroads 

-- ######################################
delete from RSRC_TO_RSRC_REL where RSRC_ID1=(select RSRC_ID from resourc where 
		PRSNTTN_ID='VisPro_Diameter_Heat_Map_Pod_menuitem_prsn' and RSRC_TYP_CD='4') and  RSRC_TYP_CD='4';
delete from RSRC_TO_COMPANY_REL where RSRC_ID=(select RSRC_ID from resourc where 
		PRSNTTN_ID='VisPro_Diameter_Heat_Map_Pod_menuitem_prsn' and RSRC_TYP_CD='4') and  RSRC_TYPE_CODE='4';
delete from resourc where RSRC_ID=(select RSRC_ID from resourc where 
		PRSNTTN_ID='VisPro_Diameter_Heat_Map_Pod_menuitem_prsn' and RSRC_TYP_CD='4') and  RSRC_TYP_CD='4';
delete from presentation where PRSNTTN_ID='VisPro_Diameter_Heat_Map_Pod_menuitem_prsn';

-- ######################################
-- ./multiAttrDel carrieruid=57 ou=Carriers,o=Crossroads permissionid VisPro_Diameter_cmpyP^VisSvc

-- ######################################
delete from COMPANY_RESOURCE where CMPNY_ID='57' and RSRC_ID='VisPro_Diameter_cmpyP'
	and RSRC_TYP_CD='2';
