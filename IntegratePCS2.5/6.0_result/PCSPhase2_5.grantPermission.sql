--This is the first line
insert into access_auth (accessor_id, rsrc_id, scrty_acc_cd, created_by,modify_by,rsrc_typ_cd)
select distinct aa.accessor_id , 'PCS_Service_Level_View_userP' , '60', 'uid=admin,o=crossroads','uid=admin,o=crossroads', '2'  
from access_auth aa , accessor a , user_xroads ux , cmpny_user cu
where aa.accessor_id = a.accessor_id 
and 
 a.usr_id = ux.usr_login_id 
 and ux.usr_typ_cd != 'UserGroup' and a.acsor_typ_cd = 'User'
and ux.usr_id = cu.usr_id and cu.cmpny_id = '57' and aa.rsrc_typ_cd = '2' and aa.rsrc_id = 'PCS_GUI' and not exists 
( select c.accessor_id from access_auth c where c.accessor_id = aa.accessor_id and c.rsrc_id = 'PCS_Service_Level_View_userP' 
and c.rsrc_typ_cd = '2');
insert into access_auth (accessor_id, rsrc_id, scrty_acc_cd, created_by,modify_by,rsrc_typ_cd)
select distinct aa.accessor_id , 'SUBSCRIBER' , '60',  'uid=admin,o=crossroads','uid=admin,o=crossroads', '1'  
from access_auth aa , accessor a , user_xroads ux , cmpny_user cu
where aa.accessor_id = a.accessor_id and 
 a.usr_id = ux.usr_login_id 
 and ux.usr_typ_cd != 'UserGroup' and a.acsor_typ_cd = 'User'
and ux.usr_id = cu.usr_id and cu.cmpny_id = '57' and aa.rsrc_typ_cd = '2' and aa.rsrc_id = 'PCS_GUI' and not exists 
( select c.accessor_id from access_auth c where c.accessor_id = aa.accessor_id and c.rsrc_id = 'SUBSCRIBER' 
and c.rsrc_typ_cd = '1');
insert into access_auth (accessor_id, rsrc_id, scrty_acc_cd, created_by,modify_by,rsrc_typ_cd)
select distinct aa.accessor_id , 'PCS_Service_Level_View_userP' , '60', 'uid=admin,o=crossroads','uid=admin,o=crossroads', '2'  
from access_auth aa , accessor a , company_role cr
where aa.accessor_id = a.accessor_id and a.role_id = cr.role_id and  a.acsor_typ_cd = 'Role'
and cr.cmpny_id = '57' and aa.rsrc_typ_cd = '2' and aa.rsrc_id = 'PCS_GUI' and not exists 
( select c.accessor_id from access_auth c where c.accessor_id = aa.accessor_id and c.rsrc_id = 'PCS_Service_Level_View_userP' 
and c.rsrc_typ_cd = '2');
insert into access_auth (accessor_id, rsrc_id, scrty_acc_cd, created_by,modify_by,rsrc_typ_cd)
select distinct aa.accessor_id , 'SUBSCRIBER' , '60', 'uid=admin,o=crossroads','uid=admin,o=crossroads', '1'  
from access_auth aa , accessor a , company_role cr
where aa.accessor_id = a.accessor_id and a.role_id = cr.role_id and  a.acsor_typ_cd = 'Role'
and cr.cmpny_id = '57' and aa.rsrc_typ_cd = '2' and aa.rsrc_id = 'PCS_GUI' and not exists 
( select c.accessor_id from access_auth c where c.accessor_id = aa.accessor_id and c.rsrc_id = 'SUBSCRIBER' 
and c.rsrc_typ_cd = '1');
insert into access_auth (accessor_id, rsrc_id, scrty_acc_cd, created_by,modify_by,rsrc_typ_cd)
select distinct aa.accessor_id , 'PCS_Service_Level_View_userP' , '60', 'uid=admin,o=crossroads','uid=admin,o=crossroads', '2'  
from access_auth aa , accessor a , user_xroads ux , cmpny_user cu
where aa.accessor_id = a.accessor_id and 
 a.usr_id = ux.usr_login_id 
 and ux.usr_typ_cd = 'UserGroup' and a.acsor_typ_cd = 'User'
and ux.usr_id = cu.usr_id and cu.cmpny_id = '57' and aa.rsrc_typ_cd = '2' and aa.rsrc_id = 'PCS_GUI' and not exists 
( select c.accessor_id from access_auth c where c.accessor_id = aa.accessor_id and c.rsrc_id = 'PCS_Service_Level_View_userP' 
and c.rsrc_typ_cd = '2');
insert into access_auth (accessor_id, rsrc_id, scrty_acc_cd, created_by,modify_by,rsrc_typ_cd)
select distinct aa.accessor_id , 'SUBSCRIBER' , '60', 'uid=admin,o=crossroads','uid=admin,o=crossroads', '1'  
from access_auth aa , accessor a , user_xroads ux , cmpny_user cu
where aa.accessor_id = a.accessor_id and 
 a.usr_id = ux.usr_login_id 
 and ux.usr_typ_cd = 'UserGroup' and a.acsor_typ_cd = 'User'
and ux.usr_id = cu.usr_id and cu.cmpny_id = '57' and aa.rsrc_typ_cd = '2' and aa.rsrc_id = 'PCS_GUI' and not exists 
( select c.accessor_id from access_auth c where c.accessor_id = aa.accessor_id and c.rsrc_id = 'SUBSCRIBER' 
and c.rsrc_typ_cd = '1');
