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
    'VisProactive_Diameter',    --RSRC_ID1
    'VisSvc',         --RSRC_ID2
    'PresGroup',     --REL_TYP_CD
    sysdate,               --CREATED_DT
    sysdate,               --MODIFIED_DT
    'uid=xroads,o=crossroads', --CREATED_BY
    'cn=directory manager',--MODIFIED_BY
    '4'                    --RSRC_TYP_CD
  );

select * from XROADS_OWNER.rsrc_to_rsrc_rel where RSRC_ID1='VisProactive_Diameter' and RSRC_ID2='VisSvc' and REL_TYP_CD='PresGroup' and RSRC_TYP_CD='4';
delete from XROADS_OWNER.rsrc_to_rsrc_rel where RSRC_ID1='VisProactive_Diameter' and RSRC_ID2='VisSvc' and REL_TYP_CD='PresGroup' and RSRC_TYP_CD='4';
