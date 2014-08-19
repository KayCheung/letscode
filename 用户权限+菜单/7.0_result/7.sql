-- This is the first line
-- ######################################
-- dn: permissiongroupid=VisPro_Diameter_userP, ou=PermissionGroups,o=Crossroads
-- ######################################
INSERT INTO presentation (PRSNTTN_ID,FRAME_ID,URL,ONCLICK,PRSNTTN_LABEL,PRSNTTN_LABEL2,
        PRSNTTN_LABEL_TAG,PRSNTTN_LABEL_POS,PRSNTTN_ORDER,PRSNTTN_RESTRICT,PRSNTTN_CHK_BX_GRP, PRSNTTN_POS,
        PRSNTTN_COL_HEADING,PRSNTTN_COL_WIDTH,PRSNTTN_ACC_TAG,PRSNTTN_HTML_ATTR,PRSNTTN_RADIO_GRP,PRSNTTN_RADIO_BUTTON,
        TIM_ZN_CD,CREATED_DT,MODIFY_DT, CREATED_BY,MODIFY_BY,PRSNTTN_READ_ONLY,PRSNTTN_TYPE,PRSNTTN_LAUNCHTYPE,
        PRSNTTN_PERMISSIONID,PRSNTTN_PRODUCTUID,PRSNTTN_COPERMISSIONID,PRSNTTN_DENYUSERCLASS, PRSNTTN_COMPANY_TYPE,
        PRSNTTTN_ALLOW_USERCLASS,PRSNTTN_GROUP_ID,EXCLUDED_COPERMISSIONS) 
        VALUES  
        ('VisPro_Diameter_userP_prsn',null,null,null,'VisProactive - Diameter',null,
        '4412',null,'02104',null,null,
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
	('VisPro_Diameter_userP', 'VisPro_Diameter_userP', null, '3', null, 
	null, 'VisPro_Diameter_userP_prsn', null, null, 
	null, null, null, null, 
	null, null, null, null, null, 
	systimestamp, systimestamp, 'cn=directory manager', 'uid=xroads,o=crossroads', null, 
	null, 'user', null, null, 
	'n',1);
-- ######################################
-- dn: permissionid=VisPro_Diameter_cmpyP, ou=PermissionCode,o=Crossroads
-- ######################################
INSERT INTO presentation (PRSNTTN_ID,FRAME_ID,URL,ONCLICK,PRSNTTN_LABEL,PRSNTTN_LABEL2,
        PRSNTTN_LABEL_TAG,PRSNTTN_LABEL_POS,PRSNTTN_ORDER,PRSNTTN_RESTRICT,PRSNTTN_CHK_BX_GRP, PRSNTTN_POS,
        PRSNTTN_COL_HEADING,PRSNTTN_COL_WIDTH,PRSNTTN_ACC_TAG,PRSNTTN_HTML_ATTR,PRSNTTN_RADIO_GRP,PRSNTTN_RADIO_BUTTON,
        TIM_ZN_CD,CREATED_DT,MODIFY_DT, CREATED_BY,MODIFY_BY,PRSNTTN_READ_ONLY,PRSNTTN_TYPE,PRSNTTN_LAUNCHTYPE,
        PRSNTTN_PERMISSIONID,PRSNTTN_PRODUCTUID,PRSNTTN_COPERMISSIONID,PRSNTTN_DENYUSERCLASS, PRSNTTN_COMPANY_TYPE,
        PRSNTTTN_ALLOW_USERCLASS,PRSNTTN_GROUP_ID,EXCLUDED_COPERMISSIONS) 
        VALUES  
        ('VisPro_Diameter_cmpyP_prsn',null,null,null,'Diameter (requires Visibility Diameter Option)',null,
        '4409',null,'0070',null,null,
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
	('VisPro_Diameter_cmpyP', 'VisPro_Diameter_cmpyP', null, '2', 'Unassigned', 
	null, 'VisPro_Diameter_cmpyP_prsn', null, null, 
	null, null, null, null, 
	null, null, null, null, null, 
	systimestamp, systimestamp, 'cn=directory manager', 'uid=xroads,o=crossroads', null, 
	null, null, null, null, 
	'n',1);
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('VisPro_Diameter_cmpyP','VisPro','Product',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('VisPro_Diameter_cmpyP','VisPro_Co','Implied',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('VisPro_Diameter_cmpyP','VisPro_Vis','Implied',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('VisPro_Diameter_cmpyP','VisProactive_Co','PermissionGroup',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('Vis_Diameter_Option','VisPro_Diameter_cmpyP','Dependant',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
-- ######################################
-- dn: permissionid=VisPro_Diameter_Alert_Pod_View_userP, ou=PermissionCode,o=Crossroads
-- ######################################
INSERT INTO presentation (PRSNTTN_ID,FRAME_ID,URL,ONCLICK,PRSNTTN_LABEL,PRSNTTN_LABEL2,
        PRSNTTN_LABEL_TAG,PRSNTTN_LABEL_POS,PRSNTTN_ORDER,PRSNTTN_RESTRICT,PRSNTTN_CHK_BX_GRP, PRSNTTN_POS,
        PRSNTTN_COL_HEADING,PRSNTTN_COL_WIDTH,PRSNTTN_ACC_TAG,PRSNTTN_HTML_ATTR,PRSNTTN_RADIO_GRP,PRSNTTN_RADIO_BUTTON,
        TIM_ZN_CD,CREATED_DT,MODIFY_DT, CREATED_BY,MODIFY_BY,PRSNTTN_READ_ONLY,PRSNTTN_TYPE,PRSNTTN_LAUNCHTYPE,
        PRSNTTN_PERMISSIONID,PRSNTTN_PRODUCTUID,PRSNTTN_COPERMISSIONID,PRSNTTN_DENYUSERCLASS, PRSNTTN_COMPANY_TYPE,
        PRSNTTTN_ALLOW_USERCLASS,PRSNTTN_GROUP_ID,EXCLUDED_COPERMISSIONS) 
        VALUES  
        ('VisPro_Diameter_Alert_Pod_View_userP_prsn',null,null,null,'Alert Pod View',null,
        '4410',null,'0010',null,null,
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
	('VisPro_Diameter_Alert_Pod_View_userP', 'VisPro_Diameter_Alert_Pod_View_userP', null, '2', 'Unassigned', 
	null, 'VisPro_Diameter_Alert_Pod_View_userP_prsn', null, null, 
	null, null, null, null, 
	null, null, null, null, null, 
	systimestamp, systimestamp, 'cn=directory manager', 'uid=xroads,o=crossroads', null, 
	null, null, null, null, 
	'n',1);
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('VisPro_Diameter_Alert_Pod_View_userP','VisPro_Diameter_cmpyP','Copermission',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('VisPro_Diameter_Alert_Pod_View_userP','VisSvc','Product',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('VisPro_Diameter_Alert_Pod_View_userP','VisPro','Implied',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('VisPro_Diameter_Alert_Pod_View_userP','VisPro_Vis','Implied',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('VisPro_Diameter_Alert_Pod_View_userP','VisPro_Diameter_userP','PermissionGroup',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
-- ######################################
-- dn: permissionid=VisPro_Diameter_Heat_Map_Pod_View_userP, ou=PermissionCode,o=Crossroads
-- ######################################
INSERT INTO presentation (PRSNTTN_ID,FRAME_ID,URL,ONCLICK,PRSNTTN_LABEL,PRSNTTN_LABEL2,
        PRSNTTN_LABEL_TAG,PRSNTTN_LABEL_POS,PRSNTTN_ORDER,PRSNTTN_RESTRICT,PRSNTTN_CHK_BX_GRP, PRSNTTN_POS,
        PRSNTTN_COL_HEADING,PRSNTTN_COL_WIDTH,PRSNTTN_ACC_TAG,PRSNTTN_HTML_ATTR,PRSNTTN_RADIO_GRP,PRSNTTN_RADIO_BUTTON,
        TIM_ZN_CD,CREATED_DT,MODIFY_DT, CREATED_BY,MODIFY_BY,PRSNTTN_READ_ONLY,PRSNTTN_TYPE,PRSNTTN_LAUNCHTYPE,
        PRSNTTN_PERMISSIONID,PRSNTTN_PRODUCTUID,PRSNTTN_COPERMISSIONID,PRSNTTN_DENYUSERCLASS, PRSNTTN_COMPANY_TYPE,
        PRSNTTTN_ALLOW_USERCLASS,PRSNTTN_GROUP_ID,EXCLUDED_COPERMISSIONS) 
        VALUES  
        ('VisPro_Diameter_Heat_Map_Pod_View_userP_prsn',null,null,null,'Heat Map Pod',null,
        '4411',null,'0020',null,null,
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
	('VisPro_Diameter_Heat_Map_Pod_View_userP', 'VisPro_Diameter_Heat_Map_Pod_View_userP', null, '2', 'Unassigned', 
	null, 'VisPro_Diameter_Heat_Map_Pod_View_userP_prsn', null, null, 
	null, null, null, null, 
	null, null, null, null, null, 
	systimestamp, systimestamp, 'cn=directory manager', 'uid=xroads,o=crossroads', null, 
	null, null, null, null, 
	'n',1);
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('VisPro_Diameter_Heat_Map_Pod_View_userP','VisPro_Diameter_cmpyP','Copermission',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('VisPro_Diameter_Heat_Map_Pod_View_userP','VisSvc','Product',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('VisPro_Diameter_Heat_Map_Pod_View_userP','VisPro','Implied',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('VisPro_Diameter_Heat_Map_Pod_View_userP','VisPro_Vis','Implied',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY, RSRC_TYP_CD) 
	values 
	('VisPro_Diameter_Heat_Map_Pod_View_userP','VisPro_Diameter_userP','PermissionGroup',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','2');
-- ######################################
-- dn: uid=VisPro_Diameter_Menu_prsn, ou=PresentationObjects,o=Crossroads
-- ######################################
INSERT INTO presentation (PRSNTTN_ID,FRAME_ID,URL,ONCLICK,PRSNTTN_LABEL,PRSNTTN_LABEL2,
	PRSNTTN_LABEL_TAG,PRSNTTN_LABEL_POS,PRSNTTN_ORDER,PRSNTTN_RESTRICT,PRSNTTN_CHK_BX_GRP, PRSNTTN_POS,
	PRSNTTN_COL_HEADING,PRSNTTN_COL_WIDTH,PRSNTTN_ACC_TAG,PRSNTTN_HTML_ATTR,PRSNTTN_RADIO_GRP,PRSNTTN_RADIO_BUTTON,
	TIM_ZN_CD,CREATED_DT,MODIFY_DT, CREATED_BY,MODIFY_BY,PRSNTTN_READ_ONLY,PRSNTTN_TYPE,PRSNTTN_LAUNCHTYPE,
	PRSNTTN_PERMISSIONID,PRSNTTN_PRODUCTUID,PRSNTTN_COPERMISSIONID,PRSNTTN_DENYUSERCLASS, PRSNTTN_COMPANY_TYPE,
	PRSNTTTN_ALLOW_USERCLASS,PRSNTTN_GROUP_ID,EXCLUDED_COPERMISSIONS) 
	VALUES  
	('VisPro_Diameter_Menu_prsn',null,null, null,'Diameter',null,
	'4412',null,'0020',null,null,
	null,null,null,null,null,
	null,null,null,systimestamp,systimestamp,
	'cn=directory manager','uid=xroads,o=crossroads',null,'presentationgroup',null,
	null, null,null,null,
	null,null,null,null );
INSERT INTO resourc (RSRC_ID,RSRC_NAME,RSRC_DESC,RSRC_TYP_CD,CMPNY_TYP_CD,RSRC_REQ_HRCHY_CD,PRSNTTN_ID,
	RSRC_OWNER,RSRC_UNRESTRICTED_ACC,RSRC_RESTRICTED,RSRC_UNRESTRICTED, RSRC_HELP_URL,RSRC_HOST,RSRC_HOME_PAGE,RSRC_MAIL,
	RSRC_FULL_PATH,RSRC_PORTAL,RSRC_SUBJECT_AREA,CREATED_DT,MODIFY_DT,CREATED_BY,MODIFY_BY,PROD_MENU_CATEGORY, PRESENTATION_ORDER,
	RSRC_GROUPTYPE,RESTRICT_GRANT,ACC_ENV_SUFFIX,CO_PERM_CHECKED_FOR_CO,IS_LIVE) 
	values 
	('VisPro_Diameter_Menu', 'VisPro_Diameter_Menu', null, '4', null, 
	null, 'VisPro_Diameter_Menu_prsn', null, null, 
	null, null, null, null, 
	null, null, null, null, null, 
	systimestamp, systimestamp, 'cn=directory manager', 'uid=xroads,o=crossroads', null, 
	null, null, null, null, 
	'n',1);
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY,RSRC_TYP_CD) 
	values 
	('VisPro_Diameter_Menu','VisSvc','PresGroup',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','4');
-- ######################################
-- dn: uid=VisPro_Diameter_Alert_Pod_menuitem_prsn, ou=PresentationObjects,o=Crossroads
-- ######################################
INSERT INTO presentation (PRSNTTN_ID,FRAME_ID,URL,ONCLICK,PRSNTTN_LABEL,PRSNTTN_LABEL2,
	PRSNTTN_LABEL_TAG,PRSNTTN_LABEL_POS,PRSNTTN_ORDER,PRSNTTN_RESTRICT,PRSNTTN_CHK_BX_GRP, PRSNTTN_POS,
	PRSNTTN_COL_HEADING,PRSNTTN_COL_WIDTH,PRSNTTN_ACC_TAG,PRSNTTN_HTML_ATTR,PRSNTTN_RADIO_GRP,PRSNTTN_RADIO_BUTTON,
	TIM_ZN_CD,CREATED_DT,MODIFY_DT, CREATED_BY,MODIFY_BY,PRSNTTN_READ_ONLY,PRSNTTN_TYPE,PRSNTTN_LAUNCHTYPE,
	PRSNTTN_PERMISSIONID,PRSNTTN_PRODUCTUID,PRSNTTN_COPERMISSIONID,PRSNTTN_DENYUSERCLASS, PRSNTTN_COMPANY_TYPE,
	PRSNTTTN_ALLOW_USERCLASS,PRSNTTN_GROUP_ID,EXCLUDED_COPERMISSIONS) 
	VALUES  
	('VisPro_Diameter_Alert_Pod_menuitem_prsn','NEW_TAB','https://vis-diameter-portal-dev.syniverse.com/ng-diameter/visproGUI/alert/index.html','parent.NavFrame.openWindow(''https://vis-diameter-portal-dev.syniverse.com/ng-diameter/visproGUI/alert/index.html'',''AlertPod'',''Alert%20Pod'')','Alert Pod',null,
	'4413',null,'0010',null,null,
	null,null,null,null,null,
	null,null,null,systimestamp,systimestamp,
	'cn=directory manager','uid=xroads,o=crossroads',null,'menuitem',null,
	'VisPro_Diameter_Alert_Pod_View_userP', null,null,null,
	null,null,null,null );
INSERT INTO resourc (RSRC_ID,RSRC_NAME,RSRC_DESC,RSRC_TYP_CD,CMPNY_TYP_CD,RSRC_REQ_HRCHY_CD,PRSNTTN_ID,
	RSRC_OWNER,RSRC_UNRESTRICTED_ACC,RSRC_RESTRICTED,RSRC_UNRESTRICTED, RSRC_HELP_URL,RSRC_HOST,RSRC_HOME_PAGE,RSRC_MAIL,
	RSRC_FULL_PATH,RSRC_PORTAL,RSRC_SUBJECT_AREA,CREATED_DT,MODIFY_DT,CREATED_BY,MODIFY_BY,PROD_MENU_CATEGORY, PRESENTATION_ORDER,
	RSRC_GROUPTYPE,RESTRICT_GRANT,ACC_ENV_SUFFIX,CO_PERM_CHECKED_FOR_CO,IS_LIVE) 
	values 
	('VisPro_Diameter_Alert_Pod_menuitem', 'VisPro_Diameter_Alert_Pod_menuitem', null, '4', null, 
	null, 'VisPro_Diameter_Alert_Pod_menuitem_prsn', null, null, 
	null, null, null, null, 
	null, null, null, null, null, 
	systimestamp, systimestamp, 'cn=directory manager', 'uid=xroads,o=crossroads', null, 
	null, null, null, null, 
	'n',1);
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY,RSRC_TYP_CD) 
	values 
	('VisPro_Diameter_Alert_Pod_menuitem','VisPro_Diameter_Menu_prsn','PresGroup',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','4');
-- ######################################
-- dn: uid=VisPro_Diameter_Heat_Map_Pod_menuitem_prsn, ou=PresentationObjects,o=Crossroads
-- ######################################
INSERT INTO presentation (PRSNTTN_ID,FRAME_ID,URL,ONCLICK,PRSNTTN_LABEL,PRSNTTN_LABEL2,
	PRSNTTN_LABEL_TAG,PRSNTTN_LABEL_POS,PRSNTTN_ORDER,PRSNTTN_RESTRICT,PRSNTTN_CHK_BX_GRP, PRSNTTN_POS,
	PRSNTTN_COL_HEADING,PRSNTTN_COL_WIDTH,PRSNTTN_ACC_TAG,PRSNTTN_HTML_ATTR,PRSNTTN_RADIO_GRP,PRSNTTN_RADIO_BUTTON,
	TIM_ZN_CD,CREATED_DT,MODIFY_DT, CREATED_BY,MODIFY_BY,PRSNTTN_READ_ONLY,PRSNTTN_TYPE,PRSNTTN_LAUNCHTYPE,
	PRSNTTN_PERMISSIONID,PRSNTTN_PRODUCTUID,PRSNTTN_COPERMISSIONID,PRSNTTN_DENYUSERCLASS, PRSNTTN_COMPANY_TYPE,
	PRSNTTTN_ALLOW_USERCLASS,PRSNTTN_GROUP_ID,EXCLUDED_COPERMISSIONS) 
	VALUES  
	('VisPro_Diameter_Heat_Map_Pod_menuitem_prsn','NEW_TAB','https://vis-diameter-portal-dev.syniverse.com/ng-diameter/visproGUI/diameterHeatmap/index.html', 'parent.NavFrame.openWindow(''https://vis-diameter-portal-dev.syniverse.com/ng-diameter/visproGUI/diameterHeatmap/index.html'',''HeatMapPod'',''Heat%20Map%20Pod'')','Heat Map Pod',null,
	'4414',null,'0020',null,null,
	null,null,null,null,null,
	null,null,null,systimestamp,systimestamp,
	'cn=directory manager','uid=xroads,o=crossroads',null,'menuitem',null,
	'VisPro_Diameter_Heat_Map_Pod_View_userP', null,null,null,
	null,null,null,null );
INSERT INTO resourc (RSRC_ID,RSRC_NAME,RSRC_DESC,RSRC_TYP_CD,CMPNY_TYP_CD,RSRC_REQ_HRCHY_CD,PRSNTTN_ID,
	RSRC_OWNER,RSRC_UNRESTRICTED_ACC,RSRC_RESTRICTED,RSRC_UNRESTRICTED, RSRC_HELP_URL,RSRC_HOST,RSRC_HOME_PAGE,RSRC_MAIL,
	RSRC_FULL_PATH,RSRC_PORTAL,RSRC_SUBJECT_AREA,CREATED_DT,MODIFY_DT,CREATED_BY,MODIFY_BY,PROD_MENU_CATEGORY, PRESENTATION_ORDER,
	RSRC_GROUPTYPE,RESTRICT_GRANT,ACC_ENV_SUFFIX,CO_PERM_CHECKED_FOR_CO,IS_LIVE) 
	values 
	('VisPro_Diameter_Heat_Map_Pod_menuitem', 'VisPro_Diameter_Heat_Map_Pod_menuitem', null, '4', null, 
	null, 'VisPro_Diameter_Heat_Map_Pod_menuitem_prsn', null, null, 
	null, null, null, null, 
	null, null, null, null, null, 
	systimestamp, systimestamp, 'cn=directory manager', 'uid=xroads,o=crossroads', null, 
	null, null, null, null, 
	'n',1);
Insert into RSRC_TO_RSRC_REL (RSRC_ID1,RSRC_ID2,REL_TYP_CD,CREATED_DT,MODIFIED_DT,
	CREATED_BY,MODIFIED_BY,RSRC_TYP_CD) 
	values 
	('VisPro_Diameter_Heat_Map_Pod_menuitem','VisPro_Diameter_Menu_prsn','PresGroup',systimestamp,systimestamp,
	'cn=directory manager','cn=directory manager','4');
-- ######################################
-- dn: carrieruid=57, ou=Carriers,o=Crossroads
-- ######################################
INSERT INTO COMPANY_RESOURCE (CMPNY_RSRC_ID,CMPNY_ID,RSRC_ID,CREATED_DT,MODIFY_DT,CREATED_BY,
	MODIFY_BY,RSRC_TYP_CD) 
	select COMP_RSRC_ID_SEQ.nextval,'57','VisPro_Diameter_cmpyP',systimestamp,systimestamp,'cn=directory manager',
	'uid=xroads,o=crossroads','2' from dual where not exists (select * from COMPANY_RESOURCE where CMPNY_ID='57' and RSRC_ID='VisPro_Diameter_cmpyP' and RSRC_TYP_CD='2');
INSERT INTO COMPANY_RESOURCE (CMPNY_RSRC_ID,CMPNY_ID,RSRC_ID,CREATED_DT,MODIFY_DT,CREATED_BY,
	MODIFY_BY,RSRC_TYP_CD) 
	select COMP_RSRC_ID_SEQ.nextval,'57','VisSvc',systimestamp,systimestamp,'cn=directory manager',
	'uid=xroads,o=crossroads','1' from dual where not exists (select * from COMPANY_RESOURCE where CMPNY_ID='57' and RSRC_ID='VisSvc' and RSRC_TYP_CD='1');
