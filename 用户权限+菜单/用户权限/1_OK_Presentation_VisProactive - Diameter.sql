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
    'VisProactive_Diameter',--PRSNTTN_ID
    NULL,                       --FRAME_ID
    NULL,                       --URL
    NULL,                       --ONCLICK
    'VisProactive - Diameter',  --PRSNTTN_LABEL
    NULL,                       --PRSNTTN_LABEL2
    'NO_TRANSLATE',             --PRSNTTN_LABEL_TAG
    NULL,                       --PRSNTTN_LABEL_POS
    '0217',                        --PRSNTTN_ORDER
    NULL,                       --PRSNTTN_RESTRICT
    NULL,                       --PRSNTTN_CHK_BX_GRP
    NULL,                       --PRSNTTN_POS
    NULL,                       --PRSNTTN_COL_HEADING
    NULL,                       --PRSNTTN_COL_WIDTH
    NULL,                       --PRSNTTN_ACC_TAG
    NULL,                       --PRSNTTN_HTML_ATTR
    NULL,                       --PRSNTTN_RADIO_GRP
    NULL,                       --PRSNTTN_RADIO_BUTTON
    NULL,                       --TIM_ZN_CD
    sysdate,                    --CREATED_DT
    sysdate,                    --MODIFY_DT
    'cn=directory manager',     --CREATED_BY
    'cn=directory manager',     --MODIFY_BY
    NULL,                       --PRSNTTN_READ_ONLY
    NULL,                       --PRSNTTN_TYPE
    NULL,                       --PRSNTTN_LAUNCHTYPE
    NULL,                       --PRSNTTN_PERMISSIONID
    NULL,                       --PRSNTTN_PRODUCTUID
    NULL,                       --PRSNTTN_COPERMISSIONID
    NULL,                       --PRSNTTN_DENYUSERCLASS
    NULL,                       --PRSNTTN_COMPANY_TYPE
    NULL,                       --PRSNTTTN_ALLOW_USERCLASS
    NULL,                       --PRSNTTN_GROUP_ID
    NULL                        --EXCLUDED_COPERMISSIONS
  );