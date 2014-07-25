INSERT
INTO XROADS_OWNER.rsrc_to_rsrc_rel
  (
    RSRC_ID1,
    RSRC_ID2,
    REL_TYP_CD,
    CREATED_DT,
    MODIFIED_DT,
    CREATED_BY,
    MODIFIED_BY,
    RSRC_TYP_CD
  )
  VALUES
  (
    'VisProactive_Diameter_Heat_Map_Pod_View',    --RSRC_ID1
    'VisProactive_Diameter',         --RSRC_ID2
    'PermissionGroup',     --REL_TYP_CD
    sysdate,               --CREATED_DT
    sysdate,               --MODIFIED_DT
    'cn=directory manager',--CREATED_BY
    'cn=directory manager',--MODIFIED_BY
    '2'                    --RSRC_TYP_CD
  );