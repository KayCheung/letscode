INSERT
INTO XROADS_OWNER.RESOURC
  (
    RSRC_ID,
    RSRC_NAME,
    RSRC_DESC,
    RSRC_TYP_CD,
    CMPNY_TYP_CD,
    RSRC_REQ_HRCHY_CD,
    PRSNTTN_ID,
    RSRC_OWNER,
    RSRC_UNRESTRICTED_ACC,
    RSRC_RESTRICTED,
    RSRC_UNRESTRICTED,
    RSRC_HELP_URL,
    RSRC_HOST,
    RSRC_HOME_PAGE,
    RSRC_MAIL,
    RSRC_FULL_PATH,
    RSRC_PORTAL,
    RSRC_SUBJECT_AREA,
    CREATED_DT,
    MODIFY_DT,
    CREATED_BY,
    MODIFY_BY,
    PROD_MENU_CATEGORY,
    PRESENTATION_ORDER,
    RSRC_GROUPTYPE,
    RESTRICT_GRANT,
    ACC_ENV_SUFFIX,
    CO_PERM_CHECKED_FOR_CO,
    OWNER_PHONE,
    OWNER_PHONE_EXT,
    IS_LIVE
  )
  VALUES
  (
	'VisProactive_Diameter_Alert_Pod',         --RSRC_ID
    'VisProactive_Diameter_Alert_Pod',     --RSRC_NAME
    NULL,                       --RSRC_DESC
    '4',                        --RSRC_TYP_CD
    'Unassigned',                    --CMPNY_TYP_CD
    NULL,                       --RSRC_REQ_HRCHY_CD
    'VisProactive_Diameter_Alert_Pod',--PRSNTTN_ID
    NULL,                       --RSRC_OWNER
    NULL,                       --RSRC_UNRESTRICTED_ACC
    NULL,                       --RSRC_RESTRICTED
    NULL,                       --RSRC_UNRESTRICTED
    NULL,                       --RSRC_HELP_URL
    NULL,                      --RSRC_HOST
    NULL,                       --RSRC_HOME_PAGE
    NULL,                       --RSRC_MAIL
    NULL,                        --RSRC_FULL_PATH
    NULL,                       --RSRC_PORTAL
    NULL,                       --RSRC_SUBJECT_AREA
    sysdate,                    --CREATED_DT
    sysdate,                    --MODIFY_DT
    'cn=directory manager',     --CREATED_BY
    'cn=directory manager',     --MODIFY_BY
    'None',                     --PROD_MENU_CATEGORY
    NULL,                       --PRESENTATION_ORDER
    NULL,                     --RSRC_GROUPTYPE
    'N',                      --RESTRICT_GRANT
    NULL,                       --ACC_ENV_SUFFIX
    'n',                        --CO_PERM_CHECKED_FOR_CO
    NULL,                       --OWNER_PHONE
    NULL,                       --OWNER_PHONE_EXT
    1                        --IS_LIVE
  );
  

-- Alert Pod
select * from RESOURC where RSRC_ID='VisProactive_Diameter_Alert_Pod' and RSRC_TYP_CD='4';
delete from RESOURC where RSRC_ID='VisProactive_Diameter_Alert_Pod' and RSRC_TYP_CD='4';
