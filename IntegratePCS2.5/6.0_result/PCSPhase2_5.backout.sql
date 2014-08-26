-- This is the first line
-- ######################################
-- ./delObj permissionid=Implied_PCS_GUI_userP ou=PermissionCode,o=Crossroads 

-- ######################################
delete from RSRC_TO_RSRC_REL where RSRC_ID1='Implied_PCS_GUI_userP'  and 
		RSRC_TYP_CD='2';
delete from RSRC_TO_COMPANY_REL where RSRC_ID='Implied_PCS_GUI_userP'  and 
		RSRC_TYPE_CODE='2';
delete from presentation where PRSNTTN_ID=(select PRSNTTN_ID from resourc where 
		RSRC_ID='Implied_PCS_GUI_userP' and RSRC_TYP_CD='2');
delete from resourc where RSRC_ID='Implied_PCS_GUI_userP' and RSRC_TYP_CD='2';

-- ######################################
-- ./multiAttrDel permissionid=PCS_GUI ou=PermissionCode,o=Crossroads

-- ######################################
delete from RSRC_TO_RSRC_REL where RSRC_ID1='PCS_GUI' and RSRC_ID2='Implied_PCS_GUI_userP' and REL_TYP_CD='Implied' and RSRC_TYP_CD='2';

-- ######################################
-- ./modLDAP permissionid=PCS_GUI ou=PermissionCode,o=Crossroads

update presentation set PRSNTTN_ORDER = '0010' where PRSNTTN_ORDER='0020' and 
		PRSNTTN_ID = (Select PRSNTTN_ID from resourc where rsrc_id = 'PCS_GUI' and rsrc_typ_cd='2');

-- ######################################
-- ./delObj permissionid=PCS_Analyzer_PCS_Reporting_userP ou=PermissionCode,o=Crossroads 

-- ######################################
delete from RSRC_TO_RSRC_REL where RSRC_ID1='PCS_Analyzer_PCS_Reporting_userP'  and 
		RSRC_TYP_CD='2';
delete from RSRC_TO_COMPANY_REL where RSRC_ID='PCS_Analyzer_PCS_Reporting_userP'  and 
		RSRC_TYPE_CODE='2';
delete from presentation where PRSNTTN_ID=(select PRSNTTN_ID from resourc where 
		RSRC_ID='PCS_Analyzer_PCS_Reporting_userP' and RSRC_TYP_CD='2');
delete from resourc where RSRC_ID='PCS_Analyzer_PCS_Reporting_userP' and RSRC_TYP_CD='2';

-- ######################################
-- ./delObj permissionid=PCS_Service_Level_View_userP ou=PermissionCode,o=Crossroads 

-- ######################################
delete from RSRC_TO_RSRC_REL where RSRC_ID1='PCS_Service_Level_View_userP'  and 
		RSRC_TYP_CD='2';
delete from RSRC_TO_COMPANY_REL where RSRC_ID='PCS_Service_Level_View_userP'  and 
		RSRC_TYPE_CODE='2';
delete from presentation where PRSNTTN_ID=(select PRSNTTN_ID from resourc where 
		RSRC_ID='PCS_Service_Level_View_userP' and RSRC_TYP_CD='2');
delete from resourc where RSRC_ID='PCS_Service_Level_View_userP' and RSRC_TYP_CD='2';

-- ######################################
-- ./modLDAP uid=PCS_GUI_Monitor_Subscribers_Menu ou=PresentationObjects,o=Crossroads 

update presentation set PRSNTTN_PERMISSIONID = 'PCS_GUI' where PRSNTTN_PERMISSIONID = 'Implied_PCS_GUI_userP' and  
		PRSNTTN_ID ='PCS_GUI_Monitor_Subscribers_Menu';

-- ######################################
-- ./delObj uid=PCS_Analyzer_PCS_Reporting_menuitem_prsn ou=PresentationObjects,o=Crossroads 

-- ######################################
delete from RSRC_TO_RSRC_REL where RSRC_ID1=(select RSRC_ID from resourc where 
		PRSNTTN_ID='PCS_Analyzer_PCS_Reporting_menuitem_prsn' and RSRC_TYP_CD='4') and  RSRC_TYP_CD='4';
delete from RSRC_TO_COMPANY_REL where RSRC_ID=(select RSRC_ID from resourc where 
		PRSNTTN_ID='PCS_Analyzer_PCS_Reporting_menuitem_prsn' and RSRC_TYP_CD='4') and  RSRC_TYPE_CODE='4';
delete from resourc where RSRC_ID=(select RSRC_ID from resourc where 
		PRSNTTN_ID='PCS_Analyzer_PCS_Reporting_menuitem_prsn' and RSRC_TYP_CD='4') and  RSRC_TYP_CD='4';
delete from presentation where PRSNTTN_ID='PCS_Analyzer_PCS_Reporting_menuitem_prsn';

