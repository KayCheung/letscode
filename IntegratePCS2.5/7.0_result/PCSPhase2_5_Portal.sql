-- This is the first line
-- ######################################
-- dn: permissionid=Implied_PCS_GUI_userP, ou=PermissionCode,o=Crossroads
-- ######################################
INSERT INTO presentation (PRSNTTN_ID,FRAME_ID,URL,ONCLICK,PRSNTTN_LABEL,PRSNTTN_LABEL2,
        PRSNTTN_LABEL_TAG,PRSNTTN_LABEL_POS,PRSNTTN_ORDER,PRSNTTN_RESTRICT,PRSNTTN_CHK_BX_GRP, PRSNTTN_POS,
        PRSNTTN_COL_HEADING,PRSNTTN_COL_WIDTH,PRSNTTN_ACC_TAG,PRSNTTN_HTML_ATTR,PRSNTTN_RADIO_GRP,PRSNTTN_RADIO_BUTTON,
        TIM_ZN_CD,CREATED_DT,MODIFY_DT, CREATED_BY,MODIFY_BY,PRSNTTN_READ_ONLY,PRSNTTN_TYPE,PRSNTTN_LAUNCHTYPE,
        PRSNTTN_PERMISSIONID,PRSNTTN_PRODUCTUID,PRSNTTN_COPERMISSIONID,PRSNTTN_DENYUSERCLASS, PRSNTTN_COMPANY_TYPE,
        PRSNTTTN_ALLOW_USERCLASS,PRSNTTN_GROUP_ID,EXCLUDED_COPERMISSIONS) 
        VALUES  
        ('Implied_PCS_GUI_userP_prsn',null,null,null,'Implied Premium Customer Services GUI',null,
        null,null,'0000',null,null,
        null,null,null,null,null,
        null,null,null,systimestamp,systimestamp,
        'cn=directory manager','uid=xroads,o=crossroads',null,null,null,
        null, null,null,null,
        null,null,null,null );
INSERT INTO resourc (RSRC_ID,RSRC_NAME,RSRC_DESC,RSRC_TYP_CD,CMPNY_TYP_CD,RSRC_REQ_HRCHY_CD,
	PRSNTTN_ID,RSRC_OWNER,RSRC_UNRESTRICTED_ACC,RSRC_RESTRICTED,RSRC_UNRESTRICTED, RSRC_HELP_URL,RSRC_HOST,
	RSRC_HOME_PAGE,RSRC_MAIL,RSRC_FULL_PATH,RSRC_PORTAL,RSRC_SUBJECT_AREA,CREATED_DT,MODIFY_DT,CREATED_BY,MODIFY_BY,
	PROD_MENU_CATEGORY, PRESENTATION_ORDER,RSRC_GROUPTYPE,RESTRICT_GRANT,ACC_ENV_SUFFIX,CO_PERM_CHECKED_FOR_CO,IS_LIVE) 
	values 
	('Implied_PCS_GUI_userP', 'Implied_PCS_GUI_userP', null, '2', 'Unassigned', 
	null, 'Implied_PCS_GUI_userP_prsn', null, null, 
	null, null, null, null, 
	null, null, null, null, null, 
	systimestamp, systimestamp, 'cn=directory manager', 'uid=xroads,o=crossroads', null, 
	null, null, null, null, 
	'n',1);
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('Implied_PCS_GUI_userP','SUBSCRIBER','Product',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
-- ######################################
-- dn: permissionid=PCS_GUI, ou=PermissionCode,o=Crossroads
-- ######################################
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('PCS_GUI','Implied_PCS_GUI_userP','Implied',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
-- ######################################
-- dn: permissionid=PCS_GUI, ou=PermissionCode,o=Crossroads
-- ######################################
update presentation set PRSNTTN_ORDER = '0020' where PRSNTTN_ORDER='0010' and 
		PRSNTTN_ID = (Select PRSNTTN_ID from resourc where rsrc_id = 'PCS_GUI' and rsrc_typ_cd='2');
-- ######################################
-- dn: permissionid=PCS_Analyzer_PCS_Reporting_userP, ou=PermissionCode,o=Crossroads
-- ######################################
INSERT INTO presentation (PRSNTTN_ID,FRAME_ID,URL,ONCLICK,PRSNTTN_LABEL,PRSNTTN_LABEL2,
        PRSNTTN_LABEL_TAG,PRSNTTN_LABEL_POS,PRSNTTN_ORDER,PRSNTTN_RESTRICT,PRSNTTN_CHK_BX_GRP, PRSNTTN_POS,
        PRSNTTN_COL_HEADING,PRSNTTN_COL_WIDTH,PRSNTTN_ACC_TAG,PRSNTTN_HTML_ATTR,PRSNTTN_RADIO_GRP,PRSNTTN_RADIO_BUTTON,
        TIM_ZN_CD,CREATED_DT,MODIFY_DT, CREATED_BY,MODIFY_BY,PRSNTTN_READ_ONLY,PRSNTTN_TYPE,PRSNTTN_LAUNCHTYPE,
        PRSNTTN_PERMISSIONID,PRSNTTN_PRODUCTUID,PRSNTTN_COPERMISSIONID,PRSNTTN_DENYUSERCLASS, PRSNTTN_COMPANY_TYPE,
        PRSNTTTN_ALLOW_USERCLASS,PRSNTTN_GROUP_ID,EXCLUDED_COPERMISSIONS) 
        VALUES  
        ('PCS_Analyzer_PCS_Reporting_userP_prsn',null,null,null,'Analyzer PCS Reporting',null,
        upper(regexp_replace('Analyzer PCS Reporting', '[^a-zA-Z0-9\s]', '_')),null,'0010',null,null,
        null,null,null,null,null,
        null,null,null,systimestamp,systimestamp,
        'cn=directory manager','uid=xroads,o=crossroads',null,null,null,
        null, null,null,null,
        null,null,null,null );
INSERT INTO resourc (RSRC_ID,RSRC_NAME,RSRC_DESC,RSRC_TYP_CD,CMPNY_TYP_CD,RSRC_REQ_HRCHY_CD,
	PRSNTTN_ID,RSRC_OWNER,RSRC_UNRESTRICTED_ACC,RSRC_RESTRICTED,RSRC_UNRESTRICTED, RSRC_HELP_URL,RSRC_HOST,
	RSRC_HOME_PAGE,RSRC_MAIL,RSRC_FULL_PATH,RSRC_PORTAL,RSRC_SUBJECT_AREA,CREATED_DT,MODIFY_DT,CREATED_BY,MODIFY_BY,
	PROD_MENU_CATEGORY, PRESENTATION_ORDER,RSRC_GROUPTYPE,RESTRICT_GRANT,ACC_ENV_SUFFIX,CO_PERM_CHECKED_FOR_CO,IS_LIVE) 
	values 
	('PCS_Analyzer_PCS_Reporting_userP', 'PCS_Analyzer_PCS_Reporting_userP', null, '2', 'Unassigned', 
	null, 'PCS_Analyzer_PCS_Reporting_userP_prsn', null, null, 
	null, null, null, null, 
	null, null, null, null, null, 
	systimestamp, systimestamp, 'cn=directory manager', 'uid=xroads,o=crossroads', null, 
	null, null, null, null, 
	'n',1);
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('PCS_Analyzer_PCS_Reporting_userP','PCS_GUI_Co','Copermission',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('PCS_Analyzer_PCS_Reporting_userP','SUBSCRIBER','Product',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('PCS_Analyzer_PCS_Reporting_userP','Implied_PCS_GUI_userP','Implied',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('PCS_Analyzer_PCS_Reporting_userP','Subscriber_PCS_Group','PermissionGroup',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
-- ######################################
-- dn: permissionid=PCS_Service_Level_View_userP, ou=PermissionCode,o=Crossroads
-- ######################################
INSERT INTO presentation (PRSNTTN_ID,FRAME_ID,URL,ONCLICK,PRSNTTN_LABEL,PRSNTTN_LABEL2,
        PRSNTTN_LABEL_TAG,PRSNTTN_LABEL_POS,PRSNTTN_ORDER,PRSNTTN_RESTRICT,PRSNTTN_CHK_BX_GRP, PRSNTTN_POS,
        PRSNTTN_COL_HEADING,PRSNTTN_COL_WIDTH,PRSNTTN_ACC_TAG,PRSNTTN_HTML_ATTR,PRSNTTN_RADIO_GRP,PRSNTTN_RADIO_BUTTON,
        TIM_ZN_CD,CREATED_DT,MODIFY_DT, CREATED_BY,MODIFY_BY,PRSNTTN_READ_ONLY,PRSNTTN_TYPE,PRSNTTN_LAUNCHTYPE,
        PRSNTTN_PERMISSIONID,PRSNTTN_PRODUCTUID,PRSNTTN_COPERMISSIONID,PRSNTTN_DENYUSERCLASS, PRSNTTN_COMPANY_TYPE,
        PRSNTTTN_ALLOW_USERCLASS,PRSNTTN_GROUP_ID,EXCLUDED_COPERMISSIONS) 
        VALUES  
        ('PCS_Service_Level_View_userP_prsn',null,null,null,'Service Level View',null,
        upper(regexp_replace('Service Level View', '[^a-zA-Z0-9\s]', '_')),null,'0025',null,null,
        null,null,null,null,null,
        null,null,null,systimestamp,systimestamp,
        'cn=directory manager','uid=xroads,o=crossroads',null,null,null,
        null, null,null,null,
        null,null,null,null );
INSERT INTO resourc (RSRC_ID,RSRC_NAME,RSRC_DESC,RSRC_TYP_CD,CMPNY_TYP_CD,RSRC_REQ_HRCHY_CD,
	PRSNTTN_ID,RSRC_OWNER,RSRC_UNRESTRICTED_ACC,RSRC_RESTRICTED,RSRC_UNRESTRICTED, RSRC_HELP_URL,RSRC_HOST,
	RSRC_HOME_PAGE,RSRC_MAIL,RSRC_FULL_PATH,RSRC_PORTAL,RSRC_SUBJECT_AREA,CREATED_DT,MODIFY_DT,CREATED_BY,MODIFY_BY,
	PROD_MENU_CATEGORY, PRESENTATION_ORDER,RSRC_GROUPTYPE,RESTRICT_GRANT,ACC_ENV_SUFFIX,CO_PERM_CHECKED_FOR_CO,IS_LIVE) 
	values 
	('PCS_Service_Level_View_userP', 'PCS_Service_Level_View_userP', null, '2', 'Unassigned', 
	null, 'PCS_Service_Level_View_userP_prsn', null, null, 
	null, null, null, null, 
	null, null, null, null, null, 
	systimestamp, systimestamp, 'cn=directory manager', 'uid=xroads,o=crossroads', null, 
	null, null, null, null, 
	'n',1);
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('PCS_Service_Level_View_userP','PCS_GUI_Co','Copermission',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('PCS_Service_Level_View_userP','SUBSCRIBER','Product',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('PCS_Service_Level_View_userP','Implied_PCS_GUI_userP','Implied',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('PCS_Service_Level_View_userP','Subscriber_PCS_Group','PermissionGroup',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
-- ######################################
-- dn: uid=PCS_GUI_Monitor_Subscribers_Menu, ou=PresentationObjects,o=Crossroads
-- ######################################
update presentation set PRSNTTN_PERMISSIONID = 'Implied_PCS_GUI_userP' where PRSNTTN_PERMISSIONID = 'PCS_GUI' and 
		PRSNTTN_ID = 'PCS_GUI_Monitor_Subscribers_Menu';
-- ######################################
-- dn: uid=PCS_Analyzer_PCS_Reporting_menuitem_prsn, ou=PresentationObjects,o=Crossroads
-- ######################################
INSERT INTO presentation (PRSNTTN_ID,FRAME_ID,URL,ONCLICK,PRSNTTN_LABEL,PRSNTTN_LABEL2,
	PRSNTTN_LABEL_TAG,PRSNTTN_LABEL_POS,PRSNTTN_ORDER,PRSNTTN_RESTRICT,PRSNTTN_CHK_BX_GRP, PRSNTTN_POS,
	PRSNTTN_COL_HEADING,PRSNTTN_COL_WIDTH,PRSNTTN_ACC_TAG,PRSNTTN_HTML_ATTR,PRSNTTN_RADIO_GRP,PRSNTTN_RADIO_BUTTON,
	TIM_ZN_CD,CREATED_DT,MODIFY_DT, CREATED_BY,MODIFY_BY,PRSNTTN_READ_ONLY,PRSNTTN_TYPE,PRSNTTN_LAUNCHTYPE,
	PRSNTTN_PERMISSIONID,PRSNTTN_PRODUCTUID,PRSNTTN_COPERMISSIONID,PRSNTTN_DENYUSERCLASS, PRSNTTN_COMPANY_TYPE,
	PRSNTTTN_ALLOW_USERCLASS,PRSNTTN_GROUP_ID,EXCLUDED_COPERMISSIONS) 
	VALUES  
	('PCS_Analyzer_PCS_Reporting_menuitem_prsn','parent.ContentFrame','https://pcs.syniverse.com/pcs/faces/subscribers', null,'Analyzer PCS Reporting',null,
	upper(regexp_replace('Analyzer PCS Reporting', '[^a-zA-Z0-9\s]', '_')),null,'0037',null,null,
	null,null,null,null,null,
	null,null,null,systimestamp,systimestamp,
	'cn=directory manager','uid=xroads,o=crossroads',null,'menuitem',null,
	'PCS_Analyzer_PCS_Reporting_userP', null,null,null,
	null,null,null,null );
INSERT INTO resourc (RSRC_ID,RSRC_NAME,RSRC_DESC,RSRC_TYP_CD,CMPNY_TYP_CD,RSRC_REQ_HRCHY_CD,PRSNTTN_ID,
	RSRC_OWNER,RSRC_UNRESTRICTED_ACC,RSRC_RESTRICTED,RSRC_UNRESTRICTED, RSRC_HELP_URL,RSRC_HOST,RSRC_HOME_PAGE,RSRC_MAIL,
	RSRC_FULL_PATH,RSRC_PORTAL,RSRC_SUBJECT_AREA,CREATED_DT,MODIFY_DT,CREATED_BY,MODIFY_BY,PROD_MENU_CATEGORY, PRESENTATION_ORDER,
	RSRC_GROUPTYPE,RESTRICT_GRANT,ACC_ENV_SUFFIX,CO_PERM_CHECKED_FOR_CO,IS_LIVE) 
	values 
	('PCS_Analyzer_PCS_Reporting_menuitem', null, null, '4', null, 
	null, 'PCS_Analyzer_PCS_Reporting_menuitem_prsn', null, null, 
	null, null, null, null, 
	null, null, null, null, null, 
	systimestamp, systimestamp, 'cn=directory manager', 'uid=xroads,o=crossroads', null, 
	null, null, null, null, 
	'n',1);
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY,RSRC_TYP_CD) 
	values 
	('PCS_Analyzer_PCS_Reporting_menuitem','Premium_Customer_Service_Menu','PresGroup',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','4');
-- ######################################
-- dn: carrieruid=57, ou=Carriers,o=Crossroads
-- ######################################
INSERT INTO COMPANY_RESOURCE (CMPNY_RSRC_ID,CMPNY_ID,RSRC_ID,CREATED_DT,MODIFY_DT,CREATED_BY,
	MODIFY_BY,RSRC_TYP_CD) 
	select COMP_RSRC_ID_SEQ.nextval,'57','SUBSCRIBER',systimestamp,systimestamp,'cn=directory manager',
	'uid=xroads,o=crossroads','1' from dual where not exists (select * from COMPANY_RESOURCE where CMPNY_ID='57' and RSRC_ID='SUBSCRIBER' and RSRC_TYP_CD='1');
