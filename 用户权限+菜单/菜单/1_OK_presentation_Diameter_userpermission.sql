INSERT
INTO XROADS_OWNER.presentation
  (
    PRSNTTN_ID,
    FRAME_ID,
    URL,
    ONCLICK,
    PRSNTTN_LABEL,
    PRSNTTN_LABEL2,
    PRSNTTN_LABEL_TAG,
    PRSNTTN_LABEL_POS,
    PRSNTTN_ORDER,
    PRSNTTN_RESTRICT,
    PRSNTTN_CHK_BX_GRP,
    PRSNTTN_POS,
    PRSNTTN_COL_HEADING,
    PRSNTTN_COL_WIDTH,
    PRSNTTN_ACC_TAG,
    PRSNTTN_HTML_ATTR,
    PRSNTTN_RADIO_GRP,
    PRSNTTN_RADIO_BUTTON,
    TIM_ZN_CD,
    CREATED_DT,
    MODIFY_DT,
    CREATED_BY,
    MODIFY_BY,
    PRSNTTN_READ_ONLY,
    PRSNTTN_TYPE,
    PRSNTTN_LAUNCHTYPE,
    PRSNTTN_PERMISSIONID,
    PRSNTTN_PRODUCTUID,
    PRSNTTN_COPERMISSIONID,
    PRSNTTN_DENYUSERCLASS,
    PRSNTTN_COMPANY_TYPE,
    PRSNTTTN_ALLOW_USERCLASS,
    PRSNTTN_GROUP_ID,
    EXCLUDED_COPERMISSIONS
  )
  VALUES
  (
    'VisProactive_Diameter_4LeftMemu',--PRSNTTN_ID
    NULL,
    NULL,
    NULL,
    'Diameter',
    NULL,
    'DIAMETER',
    NULL,
    '0020',
    NULL,
    NULL,
    NULL,
    NULL,
    NULL,
    NULL,
    NULL,
    NULL,
    NULL,
    NULL,
    sysdate,                    --CREATED_DT
    sysdate,                    --MODIFY_DT
    'cn=directory manager',
    'cn=directory manager',
    NULL,
    'presentationgroup',
    NULL,
    NULL,
    NULL,
    NULL,
    NULL,
    NULL,
    NULL,
    NULL,
    NULL
  );

-- Diameter
select * from XROADS_OWNER.presentation where PRSNTTN_ID='VisProactive_Diameter_4LeftMemu';
delete from XROADS_OWNER.presentation where PRSNTTN_ID='VisProactive_Diameter_4LeftMemu';
