--1. directly assigned
SELECT UserPermissionsEntityObject.RSRC_ID,
ux.usr_login_id as user_login_id,
cs.cmpny_id
FROM ACCESS_AUTH UserPermissionsEntityObject
inner join accessor aa on aa.accessor_id = UserPermissionsEntityObject.accessor_id
inner join user_xroads ux on ux.usr_id = aa.usr_id
inner join cmpny_user cs on cs.usr_id = ux.usr_id and accessible_cmpny_id is null
WHERE UserPermissionsEntityObject.RSRC_TYP_CD='2'
union
--2. directly implied assigned
SELECT rr.RSRC_ID2,
ux.usr_login_id as user_login_id,
cs.cmpny_id
FROM ACCESS_AUTH UserPermissionsEntityObject
inner join rsrc_to_rsrc_rel rr on rr.rsrc_id1 = UserPermissionsEntityObject.RSRC_ID and rr.rel_typ_cd = 'Implied'
inner join accessor aa on aa.accessor_id = UserPermissionsEntityObject.accessor_id
inner join user_xroads ux on ux.usr_id = aa.usr_id
inner join resourc rc1 on rr.rsrc_id2 = rc1.RSRC_ID   
inner join cmpny_user cs on cs.usr_id = ux.usr_id and accessible_cmpny_id is null
WHERE UserPermissionsEntityObject.RSRC_TYP_CD='2'
and ((select cmpny_typ_cd from company_xroads where cmpny_cd = (select cu.cmpny_id from cmpny_user cu where cu.accessible_cmpny_id is null and cu.usr_id = aa.usr_id) ) in (select regexp_substr(rc1.cmpny_typ_cd,'[^,]+', 1, level) from dual connect by regexp_substr(rc1.cmpny_typ_cd,'[^,]+', 1, level) is not null) or
rc1.cmpny_typ_cd = 'Unassigned' or
(select decode((select cmpny_typ_cd from company_xroads where cmpny_cd = (select cu.cmpny_id from cmpny_user cu where cu.accessible_cmpny_id is null and cu.usr_id = aa.usr_id) ),'admin', (select 'admin' from dual),(select 'nonadmin' from dual)) from dual)
  in (select regexp_substr(rc1.cmpny_typ_cd,'[^,]+', 1, level) from dual connect by regexp_substr(rc1.cmpny_typ_cd,'[^,]+', 1, level) is not null)
  )
and rr.rsrc_id2 not in (select rsrc_id from rsrc_to_company_rel rc where rc.rsrc_id = rr.rsrc_id2 and rc.company_id = (select cx1.cmpny_id from cmpny_user cx1 where cx1.usr_id = ux.usr_id and cx1.accessible_cmpny_id is null) and rel_type_code = 'deny')
and (rr.rsrc_id2 in (select rsrc_id from rsrc_to_company_rel rc where rc.rsrc_id = rr.rsrc_id2 and rc.company_id = (select cx1.cmpny_id from cmpny_user cx1 where cx1.usr_id = ux.usr_id and cx1.accessible_cmpny_id is null) and rel_type_code = 'Allow'
) or 0 = (select count(rsrc_id) from rsrc_to_company_rel rc where rc.rsrc_id = rr.rsrc_id2 and rel_type_code = 'Allow'))
union
--3. assigned by role
SELECT RolePermissionsEntityObject.RSRC_ID,
ux.usr_login_id as user_login_id,
cs.cmpny_id
FROM ACCESS_AUTH RolePermissionsEntityObject
inner join accessor aa on aa.accessor_id = RolePermissionsEntityObject.accessor_id
inner join user_role ur on ur.role_id = aa.role_id
inner join user_xroads ux on ux.usr_id = ur.user_id
inner join cmpny_user cs on cs.usr_id = ux.usr_id and accessible_cmpny_id is null
WHERE RolePermissionsEntityObject.RSRC_TYP_CD='2'
union
--4. implied by assigned role
SELECT rr.RSRC_ID2,
ux.usr_login_id as user_login_id,
cs.cmpny_id
FROM ACCESS_AUTH RolePermissionsEntityObject
inner join rsrc_to_rsrc_rel rr on rr.rsrc_id1 = RolePermissionsEntityObject.RSRC_ID and rr.rel_typ_cd = 'Implied'
inner join accessor aa on aa.accessor_id = RolePermissionsEntityObject.accessor_id
inner join user_role ur on ur.role_id = aa.role_id
inner join user_xroads ux on ux.usr_id = ur.user_id
inner join resourc rc1 on rr.rsrc_id2 = rc1.RSRC_ID  
inner join cmpny_user cs on cs.usr_id = ux.usr_id and accessible_cmpny_id is null 
WHERE RolePermissionsEntityObject.RSRC_TYP_CD='2'
and ((select cmpny_typ_cd from company_xroads where cmpny_cd = (select cu.cmpny_id from cmpny_user cu where cu.accessible_cmpny_id is null and cu.usr_id = ux.usr_id) ) in (select regexp_substr(rc1.cmpny_typ_cd,'[^,]+', 1, level) from dual connect by regexp_substr(rc1.cmpny_typ_cd,'[^,]+', 1, level) is not null) or
rc1.cmpny_typ_cd = 'Unassigned' or
(select decode((select cmpny_typ_cd from company_xroads where cmpny_cd = (select cu.cmpny_id from cmpny_user cu where cu.accessible_cmpny_id is null and cu.usr_id = ux.usr_id) ),'admin', (select 'admin' from dual),(select 'nonadmin' from dual)) from dual)
  in (select regexp_substr(rc1.cmpny_typ_cd,'[^,]+', 1, level) from dual connect by regexp_substr(rc1.cmpny_typ_cd,'[^,]+', 1, level) is not null)
  )
and rr.rsrc_id2 not in (select rsrc_id from rsrc_to_company_rel rc where rc.rsrc_id = rr.rsrc_id2 and rc.company_id = (select cx1.cmpny_id from cmpny_user cx1 where cx1.usr_id = ux.usr_id and cx1.accessible_cmpny_id is null) and rel_type_code = 'deny')
and (rr.rsrc_id2 in (select rsrc_id from rsrc_to_company_rel rc where rc.rsrc_id = rr.rsrc_id2 and rc.company_id = (select cx1.cmpny_id from cmpny_user cx1 where cx1.usr_id = ux.usr_id and cx1.accessible_cmpny_id is null) and rel_type_code = 'Allow'
) or 0 = (select count(rsrc_id) from rsrc_to_company_rel rc where rc.rsrc_id = rr.rsrc_id2 and rel_type_code = 'Allow'))