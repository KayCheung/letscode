SELECT distinct UserPermissionsEntityObject.ACCESSOR_ID, 
       UserPermissionsEntityObject.RSRC_ID, 
       UserPermissionsEntityObject.SCRTY_ACC_CD, 
       UserPermissionsEntityObject.CREATED_DT, 
       UserPermissionsEntityObject.MODIFY_DT, 
       UserPermissionsEntityObject.CREATED_BY, 
       UserPermissionsEntityObject.MODIFY_BY, 
       UserPermissionsEntityObject.RSRC_TYP_CD
FROM ACCESS_AUTH UserPermissionsEntityObject
WHERE UserPermissionsEntityObject.RSRC_TYP_CD='2'
union
SELECT distinct UserPermissionsEntityObject.ACCESSOR_ID, 
       rr.RSRC_ID2 as rsrc_id, 
       UserPermissionsEntityObject.SCRTY_ACC_CD, 
       (select sysdate from dual) as CREATED_DT, 
       (select sysdate from dual) as MODIFY_DT, 
       (select '1' from dual) as CREATED_BY, 
       (select '1' from dual) as MODIFY_BY, 
       (select '2' from dual) as rsrc_typ_cd
FROM ACCESS_AUTH UserPermissionsEntityObject
inner join rsrc_to_rsrc_rel rr on rr.rsrc_id1 = UserPermissionsEntityObject.RSRC_ID and rr.rel_typ_cd = 'Implied'
inner join accessor aa on aa.accessor_id = UserPermissionsEntityObject.accessor_id
inner join resourc rc1 on rr.rsrc_id2 = rc1.RSRC_ID  
WHERE UserPermissionsEntityObject.RSRC_TYP_CD='2'
and ((select cmpny_typ_cd from company_xroads where cmpny_cd = (select cu.cmpny_id from cmpny_user cu where cu.accessible_cmpny_id is null and cu.usr_id = aa.usr_id) ) in (select regexp_substr(rc1.cmpny_typ_cd,'[^,]+', 1, level) from dual connect by regexp_substr(rc1.cmpny_typ_cd,'[^,]+', 1, level) is not null) or
rc1.cmpny_typ_cd = 'Unassigned' or
(select decode((select cmpny_typ_cd from company_xroads where cmpny_cd = (select cu.cmpny_id from cmpny_user cu where cu.accessible_cmpny_id is null and cu.usr_id = aa.usr_id) ),'admin', (select 'admin' from dual),(select 'nonadmin' from dual)) from dual)
  in (select regexp_substr(rc1.cmpny_typ_cd,'[^,]+', 1, level) from dual connect by regexp_substr(rc1.cmpny_typ_cd,'[^,]+', 1, level) is not null)
  )
and rr.rsrc_id2 not in (select rsrc_id from rsrc_to_company_rel rc where rc.rsrc_id = rr.rsrc_id2 and rc.company_id = (select cx1.cmpny_id from cmpny_user cx1 where cx1.usr_id = aa.usr_id and cx1.accessible_cmpny_id is null) and rel_type_code = 'deny')
and (rr.rsrc_id2 in (select rsrc_id from rsrc_to_company_rel rc where rc.rsrc_id = rr.rsrc_id2 and rc.company_id = (select cx1.cmpny_id from cmpny_user cx1 where cx1.usr_id = aa.usr_id and cx1.accessible_cmpny_id is null) and rel_type_code = 'Allow'
) or 0 = (select count(rsrc_id) from rsrc_to_company_rel rc where rc.rsrc_id = rr.rsrc_id2 and rel_type_code = 'Allow'))
order by 2