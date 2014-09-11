	public ArrayList getCompanyObjects(String sFilter, boolean bIdOnly, boolean tinyObjects,
			String[] sortAttrs) throws LDAPException {

		boolean[] isAscending = { true };
		String sortAtt = createOrderByClause(sortAttrs, isAscending);

		ArrayList companies = new ArrayList(1500);

		if (sFilter == "") {
			throw new LDAPExceptionManipulator("Bad Serach Filter",
					LDAPException.PARAM_ERROR);
		}

		try {

			conn = CrossRoadsConnectionManager.getInstance().getConnection();
			st = conn.createStatement();
			ColumnMapping colmap = new ColumnMapping();

			String filterquery = "";
			String columnname = "";
			String colvalue = "";
			String szQuery = "";

			if (sFilter != null && sFilter.indexOf("=") > 0) {
				columnname = sFilter.substring(0, sFilter.indexOf("="));
				colvalue = sFilter.substring(sFilter.indexOf("=") + 1);
				if (columnname.indexOf("(") != -1) {
					columnname = columnname.substring(
							columnname.lastIndexOf("(") + 1,
							columnname.length());
				}

				if (colvalue.indexOf(")") != -1) {
					colvalue = colvalue.substring(0, colvalue.indexOf(")"));
				}				

				if (columnname.equalsIgnoreCase("ctpcompanyid")) {
					filterquery = " and " + colmap.getDbColumn(columnname)
							+ "='" + colvalue + "'";
					szQuery = " select  c.CMPNY_CD as Carrieruid,c.CMPNY_TYP_CD as companytype, "
							+ " c.CMPNY_NAME as CARRIERNAME,  c.CMPNY_SHORTNAME as shortname,c.CMPNY_LOGO as companylogo,"
							+ " c.CMPNY_DEFAULT_PORTAL as defaultportal,  c.TSG_CARRIER_ID as tsgcarrierid, "
							+ " c.CTP_COMPANY_ID as ctpcompanyid,c.VIS_CARRIER_ID as viscarrierid, "
							+ " c.CTP_COMPANY_NAME as ctpcompanyname,c.CMPNY_ACCESS_STAT as accessstatus, "
							+ " c.PROFILEMODIFIEDBY,  c.PROFILEMODIFIEDDATE, p.PRSNTTN_LABEL as presentationlabel, "
							+ " p.PRSNTTN_LABEL_TAG as presentationlabeltag,  p.PRSNTTN_ORDER as presentationorder, "
							+ " c.PERMSMODIFIEDBY,c.PERMSMODIFIEDDATE,c.IPMODIFIEDBY,c.IPMODIFIEDDATE,  c.LSRPASSWORD, "
							+ " c.LSRDOMAIN,c.ALTSPID,c.ROAMWISEPATH  FROM  COMPANY_XROADS c,  COMPANY_TYPE_CODE ctc, "
							+ " PRESENTATION p where trim(ctc.CMPNY_TYP_CD)= trim(c.CMPNY_TYP_CD)  "
							+ " and  trim(ctc.PRSNTTN_ID) =  trim( p.PRSNTTN_ID) "
							+ " and upper(ctc.CMPNY_TYP_CD) <> upper('nonadmin') "
							+ filterquery + sortAtt;
				} else if (columnname.equalsIgnoreCase("aocid")) {
					szQuery = "SELECT c.cmpny_cd AS carrieruid,  c.cmpny_typ_cd AS companytype,   c.cmpny_name "
							+ " AS carriername,   c.cmpny_shortname AS shortname,  c.cmpny_logo AS companylogo,"
							+ " c.cmpny_default_portal AS defaultportal,  c.tsg_carrier_id AS tsgcarrierid, "
							+ " c.ctp_company_id AS ctpcompanyid,   c.vis_carrier_id AS viscarrierid, "
							+ " c.ctp_company_name AS ctpcompanyname,  c.cmpny_access_stat AS accessstatus, "
							+ " c.profilemodifiedby,  c.profilemodifieddate,  p.prsnttn_label AS presentationlabel, "
							+ " p.prsnttn_label_tag AS presentationlabeltag,  p.prsnttn_order AS presentationorder, "
							+ " c.permsmodifiedby,  c.permsmodifieddate,  c.ipmodifiedby,  c.ipmodifieddate, "
							+ " c.lsrpassword,  c.lsrdomain,  c.altspid,  c.roamwisepath,  cr.RSRC_ID as aocid "
							+ " FROM company_xroads c,  company_type_code ctc  ,presentation p, "
							+ " COMPANY_RESOURCE cr WHERE TRIM(ctc.cmpny_typ_cd) = TRIM(c.cmpny_typ_cd) "
							+ " AND TRIM(ctc.prsnttn_id) = TRIM(p.prsnttn_id) "
							+ " and c.cmpny_cd = cr.CMPNY_ID and upper(ctc.CMPNY_TYP_CD) <> upper('nonadmin') "
							+ " AND cr.RSRC_TYP_CD='"
							+ XroadsEnums.RSRC_TYP_CD_AOCID
							+ "' and cr.RSRC_ID='" + colvalue + "'" + sortAtt;
				} else if (columnname.equalsIgnoreCase("roleuid")) {
					szQuery = "SELECT c.cmpny_cd AS carrieruid,  c.cmpny_typ_cd AS companytype,   c.cmpny_name "
							+ " AS carriername,   c.cmpny_shortname AS shortname,  c.cmpny_logo AS companylogo,"
							+ "c.cmpny_default_portal AS defaultportal,  c.tsg_carrier_id AS tsgcarrierid,"
							+ "c.ctp_company_id AS ctpcompanyid,   c.vis_carrier_id AS viscarrierid,"
							+ "c.ctp_company_name AS ctpcompanyname,  c.cmpny_access_stat AS accessstatus,"
							+ "c.profilemodifiedby,  c.profilemodifieddate, c.permsmodifiedby,"
							+ "c.permsmodifieddate,  c.ipmodifiedby,  c.ipmodifieddate,"
							+ "c.lsrpassword,  c.lsrdomain,  c.altspid FROM company_xroads c ,COMPANY_ROLE cr"
							+ "where cr.ROLE_ID='"
							+ colvalue
							+ "' and c.CMPNY_CD = cr.CMPNY_ID" + sortAtt;

				} else if (columnname.equalsIgnoreCase("companytype")) {
					szQuery = "SELECT c.cmpny_cd AS carrieruid,  c.cmpny_typ_cd AS companytype,   c.cmpny_name "
							+ " AS carriername,   c.cmpny_shortname AS shortname,  c.cmpny_logo AS companylogo,"
							+ "c.cmpny_default_portal AS defaultportal,  c.tsg_carrier_id AS tsgcarrierid,"
							+ "c.ctp_company_id AS ctpcompanyid,   c.vis_carrier_id AS viscarrierid,"
							+ "c.ctp_company_name AS ctpcompanyname,  c.cmpny_access_stat AS accessstatus,"
							+ "c.profilemodifiedby,  c.profilemodifieddate, c.permsmodifiedby,"
							+ "c.permsmodifieddate,  c.ipmodifiedby,  c.ipmodifieddate,"
							+ "c.lsrpassword,  c.lsrdomain,  c.altspid FROM company_xroads c "
							+ "where c.CMPNY_TYP_CD='"
							+ colvalue
							+ "' "
							+ sortAtt;

				} else if (columnname.equalsIgnoreCase("objectclass")) {
					String szComptype1 = "stars_corp_acct";
					String szComptype2 = "stars_cons";
					szQuery = "SELECT c.cmpny_cd AS carrieruid,  c.cmpny_typ_cd AS companytype,   c.cmpny_name"
							+ " AS carriername,   c.cmpny_shortname AS shortname,  c.cmpny_logo AS companylogo,"
							+ "c.cmpny_default_portal AS defaultportal,  c.tsg_carrier_id AS tsgcarrierid,"
							+ "c.ctp_company_id AS ctpcompanyid,   c.vis_carrier_id AS viscarrierid,"
							+ "c.ctp_company_name AS ctpcompanyname,  c.cmpny_access_stat AS accessstatus,"
							+ "c.profilemodifiedby,  c.profilemodifieddate, c.permsmodifiedby,"
							+ "c.permsmodifieddate,  c.ipmodifiedby,  c.ipmodifieddate,"
							+ "c.lsrpassword,  c.lsrdomain,  c.altspid FROM company_xroads c "
							+ "where c.CMPNY_TYP_CD<>'"
							+ szComptype1
							+ "' and c.CMPNY_TYP_CD<>'"
							+ szComptype2
							+ "'"
							+ sortAtt; // and

				} else if (columnname.equalsIgnoreCase("carrieruid")) {
					if (colvalue.equals("*")) {
						szQuery = " SELECT c.cmpny_cd AS carrieruid,  c.cmpny_typ_cd AS companytype,   c.cmpny_name "
								+ " AS carriername,   c.cmpny_shortname AS shortname,  c.cmpny_logo AS companylogo, "
								+ " c.cmpny_default_portal AS defaultportal,  c.tsg_carrier_id AS tsgcarrierid, "
								+ " c.ctp_company_id AS ctpcompanyid,   c.vis_carrier_id AS viscarrierid, "
								+ " c.ctp_company_name AS ctpcompanyname,  c.cmpny_access_stat AS accessstatus, "
								+ " c.profilemodifiedby,  c.profilemodifieddate, c.permsmodifiedby, "
								+ " c.permsmodifieddate,  c.ipmodifiedby,  c.ipmodifieddate, "
								+ " c.lsrpassword,  c.lsrdomain,  c.altspid FROM company_xroads c "
								+ sortAtt;

					} else if (colvalue.equals("57")) {
						szQuery = " SELECT c.cmpny_cd AS carrieruid,  c.cmpny_typ_cd AS companytype,   c.cmpny_name "
								+ " AS carriername,   c.cmpny_shortname AS shortname,  c.cmpny_logo AS companylogo, "
								+ " c.cmpny_default_portal AS defaultportal,  c.tsg_carrier_id AS tsgcarrierid, "
								+ " c.ctp_company_id AS ctpcompanyid,   c.vis_carrier_id AS viscarrierid, "
								+ " c.ctp_company_name AS ctpcompanyname,  c.cmpny_access_stat AS accessstatus, "
								+ " c.profilemodifiedby,  c.profilemodifieddate, c.permsmodifiedby, "
								+ " c.permsmodifieddate,  c.ipmodifiedby,  c.ipmodifieddate, "
								+ " c.lsrpassword,  c.lsrdomain,  c.altspid FROM company_xroads c "
								+ " where c.CMPNY_CD<>'"
								+ colvalue
								+ "'"
								+ sortAtt;

					} else {
						szQuery = " SELECT c.cmpny_cd AS carrieruid,  c.cmpny_typ_cd AS companytype,   c.cmpny_name "
								+ " AS carriername,   c.cmpny_shortname AS shortname,  c.cmpny_logo AS companylogo, "
								+ " c.cmpny_default_portal AS defaultportal,  c.tsg_carrier_id AS tsgcarrierid, "
								+ " c.ctp_company_id AS ctpcompanyid,   c.vis_carrier_id AS viscarrierid, "
								+ " c.ctp_company_name AS ctpcompanyname,  c.cmpny_access_stat AS accessstatus, "
								+ " c.profilemodifiedby,  c.profilemodifieddate, c.permsmodifiedby, "
								+ " c.permsmodifieddate,  c.ipmodifiedby,  c.ipmodifieddate, "
								+ " c.lsrpassword,  c.lsrdomain,  c.altspid FROM company_xroads c "
								+ " where c.CMPNY_CD='"
								+ XroadsUtility.sqlParameterEscaper(colvalue)
								+ "'"
								+ sortAtt;

					}
				} else if (columnname
						.equalsIgnoreCase(XroadsEnums.ATTR_PERMISSION_ID)) {

					if (colvalue.equals("^*")) {
						szQuery = "SELECT c.cmpny_cd AS carrieruid,  c.cmpny_typ_cd AS companytype,   c.cmpny_name "
								+ " AS carriername,   c.cmpny_shortname AS shortname,  c.cmpny_logo AS companylogo,  "
								+ " c.cmpny_default_portal AS defaultportal,  c.tsg_carrier_id AS tsgcarrierid, "
								+ " c.ctp_company_id AS ctpcompanyid,   c.vis_carrier_id AS viscarrierid, "
								+ " c.ctp_company_name AS ctpcompanyname,  c.cmpny_access_stat AS accessstatus, "
								+ " c.profilemodifiedby,  c.profilemodifieddate,  p.prsnttn_label AS presentationlabel, "
								+ " p.prsnttn_label_tag AS presentationlabeltag,  p.prsnttn_order AS presentationorder, "
								+ " c.permsmodifiedby,  c.permsmodifieddate,  c.ipmodifiedby,  c.ipmodifieddate, "
								+ " c.lsrpassword,  c.lsrdomain,  c.altspid,  c.roamwisepath,  cr.RSRC_ID as permissionid "
								+ " FROM company_xroads c,  company_type_code ctc  ,presentation p, "
								+ " COMPANY_RESOURCE cr WHERE TRIM(ctc.cmpny_typ_cd) = TRIM(c.cmpny_typ_cd) "
								+ " AND TRIM(ctc.prsnttn_id) = TRIM(p.prsnttn_id) "
								+ " and c.cmpny_cd = cr.CMPNY_ID "
								+ " and  upper(ctc.cmpny_typ_cd) <> upper('nonadmin') "
								+ " AND cr.RSRC_TYP_CD='"
								+ XroadsEnums.RSRC_TYP_CD_PERMISSION
								+ "'"
								+ sortAtt;

					} else {
						String vals = "";
						String str[] = sFilter
								.split(XroadsEnums.PERMISSION_CODE_FILTER);
						for (int i = 0; i < str.length; i++) {
							String element = str[i];
							if (!str[i].equals("")) {
								if (element.indexOf(")") != -1) {
									element = element.substring(0,
											element.indexOf(")"));
									if (element.indexOf("^") != -1) {
										element = element.substring(0,
												element.indexOf("^"));
									}
									if(!element.equals("")) {
										vals += "'" + XroadsUtility.sqlParameterEscaper(element) + "',";
									}
								} else if (element.indexOf("(") != -1) { // This is required as the there could be leading ( in the search filter
									element = element.substring(0,
											element.indexOf("("));
									if (element.indexOf("^") != -1) {
										element = element.substring(0,
												element.indexOf("^"));
									}
									if(!element.equals("")) {
										vals += "'" + XroadsUtility.sqlParameterEscaper(element) + "',";
									}
								} else {
									if (element.indexOf("^") != -1) {
										element = element.substring(0,
												element.indexOf("^"));
									}
									vals += "'" + XroadsUtility.sqlParameterEscaper(element) + "'";
								}
							}
						}
						if (vals.endsWith(",")) {
							vals = vals.substring(0, vals.length() - 1);
						}
						//if(vals != null && !vals.equals("")) {
						//	vals = XroadsUtility.sqlParameterEscaper(vals);
						//}
						if (vals.equals("'Acc_FOD'")) {
							vals = vals.substring(1, vals.length() - 1);
							szQuery = "SELECT c.cmpny_cd AS carrieruid,  c.cmpny_typ_cd AS companytype,   c.cmpny_name "
									+ " AS carriername,   c.cmpny_shortname AS shortname,  c.cmpny_logo AS companylogo,  "
									+ " c.cmpny_default_portal AS defaultportal,  c.tsg_carrier_id AS tsgcarrierid, "
									+ " c.ctp_company_id AS ctpcompanyid,   c.vis_carrier_id AS viscarrierid, "
									+ " c.ctp_company_name AS ctpcompanyname,  c.cmpny_access_stat AS accessstatus, "
									+ " c.profilemodifiedby,  c.profilemodifieddate,  p.prsnttn_label AS presentationlabel, "
									+ " p.prsnttn_label_tag AS presentationlabeltag,  p.prsnttn_order AS presentationorder, "
									+ " c.permsmodifiedby,  c.permsmodifieddate,  c.ipmodifiedby,  c.ipmodifieddate, "
									+ " c.lsrpassword,  c.lsrdomain,  c.altspid,  c.roamwisepath,  cr.RSRC_ID as permissionid "
									+ " FROM company_xroads c,  company_type_code ctc  ,presentation p, "
									+ " COMPANY_RESOURCE cr WHERE TRIM(ctc.cmpny_typ_cd) = TRIM(c.cmpny_typ_cd) "
									+ " AND TRIM(ctc.prsnttn_id) = TRIM(p.prsnttn_id) "
									+ " and c.cmpny_cd = cr.CMPNY_ID "
									+ " and upper(ctc.cmpny_typ_cd) <>upper('nonadmin')"
									+ " AND (cr.RSRC_TYP_CD='"
									+ XroadsEnums.RSRC_TYP_CD_PERMISSION
									+ "' "
									+ " and cr.RSRC_ID = '"
									+ vals
									+ "')and(cr.RSRC_TYP_CD='"
									+ XroadsEnums.RSRC_TYP_CD_PRODUCT
									+ "'"
									+ " and cr.RSRC_ID='AccEU') or (cr.RSRC_TYP_CD='"
									+ XroadsEnums.RSRC_TYP_CD_PRODUCT
									+ "'"
									+ " and cr.RSRC_ID='AccNA')" + sortAtt;

						} else {
							szQuery = "SELECT c.cmpny_cd AS carrieruid,  c.cmpny_typ_cd AS companytype,   c.cmpny_name "
									+ " AS carriername,   c.cmpny_shortname AS shortname,  c.cmpny_logo AS companylogo,  "
									+ " c.cmpny_default_portal AS defaultportal,  c.tsg_carrier_id AS tsgcarrierid, "
									+ " c.ctp_company_id AS ctpcompanyid,   c.vis_carrier_id AS viscarrierid, "
									+ " c.ctp_company_name AS ctpcompanyname,  c.cmpny_access_stat AS accessstatus, "
									+ " c.profilemodifiedby,  c.profilemodifieddate,  "
									+ " c.permsmodifiedby,  c.permsmodifieddate,  c.ipmodifiedby,  c.ipmodifieddate, "
									+ " c.lsrpassword,  c.lsrdomain,  c.altspid,  c.roamwisepath,  cr.RSRC_ID as permissionid "
									+ " FROM company_xroads c, "
									+ " COMPANY_RESOURCE cr WHERE  "
									+ " c.cmpny_cd = cr.CMPNY_ID "
									+ " AND cr.RSRC_TYP_CD='"
									+ XroadsEnums.RSRC_TYP_CD_PERMISSION
									+ "' "
									+ " and cr.RSRC_ID in ("
									+ vals
									+ ")"
									+ sortAtt;
						}
					}
				} else if (columnname
						.equalsIgnoreCase(XroadsEnums.ATTR_PRODUCT_ID)) {
					if (colvalue.equals("^*")) {
						szQuery = "SELECT c.cmpny_cd AS carrieruid,  c.cmpny_typ_cd AS companytype,   c.cmpny_name "
								+ " AS carriername,   c.cmpny_shortname AS shortname,  c.cmpny_logo AS companylogo,  "
								+ " c.cmpny_default_portal AS defaultportal,  c.tsg_carrier_id AS tsgcarrierid, "
								+ " c.ctp_company_id AS ctpcompanyid,   c.vis_carrier_id AS viscarrierid, "
								+ " c.ctp_company_name AS ctpcompanyname,  c.cmpny_access_stat AS accessstatus, "
								+ " c.profilemodifiedby,  c.profilemodifieddate,  p.prsnttn_label AS presentationlabel, "
								+ " p.prsnttn_label_tag AS presentationlabeltag,  p.prsnttn_order AS presentationorder, "
								+ " c.permsmodifiedby,  c.permsmodifieddate,  c.ipmodifiedby,  c.ipmodifieddate, "
								+ " c.lsrpassword,  c.lsrdomain,  c.altspid,  c.roamwisepath,  cr.RSRC_ID as permissionid "
								+ " FROM company_xroads c,  company_type_code ctc  ,presentation p, "
								+ " COMPANY_RESOURCE cr WHERE TRIM(ctc.cmpny_typ_cd) = TRIM(c.cmpny_typ_cd) "
								+ " AND TRIM(ctc.prsnttn_id) = TRIM(p.prsnttn_id) "
								+ " and c.cmpny_cd = cr.CMPNY_ID "
								+ " and upper(ctc.cmpny_typ_cd) <>upper('nonadmin')"
								+ " AND cr.RSRC_TYP_CD='"
								+ XroadsEnums.RSRC_TYP_CD_PRODUCT
								+ "'"
								+ sortAtt;

					} else {
						String vals = "";
						String str[] = sFilter
								.split(XroadsEnums.PRODUCTS_FILTER);
						for (int i = 0; i < str.length; i++) {
							String element = str[i];
							if (!str[i].equals("")) {
								if (element.indexOf(")") != -1) {
									element = element.substring(0,
											element.indexOf(")"));
									if (element.indexOf("^") != -1) {
										element = element.substring(0,
												element.indexOf("^"));
									}
									vals += "'" + XroadsUtility.sqlParameterEscaper(element) + "',";
								} else {
									if (element.indexOf("^") != -1) {
										element = element.substring(0,
												element.indexOf("^"));
									}
									vals += "'" + XroadsUtility.sqlParameterEscaper(element) + "'";
								}
							}
						}
						if (vals.endsWith(",")) {
							vals = vals.substring(0, vals.length() - 1);
						}
						//szQuery = "SELECT c.cmpny_cd AS carrieruid,  c.cmpny_typ_cd AS companytype,   c.cmpny_name "
						szQuery = "SELECT distinct c.cmpny_cd AS carrieruid,  c.cmpny_typ_cd AS companytype,   c.cmpny_name "
								+ " AS carriername,   c.cmpny_shortname AS shortname,  c.cmpny_logo AS companylogo,  "
								+ " c.cmpny_default_portal AS defaultportal,  c.tsg_carrier_id AS tsgcarrierid, "
								+ " c.ctp_company_id AS ctpcompanyid,   c.vis_carrier_id AS viscarrierid, "
								+ " c.ctp_company_name AS ctpcompanyname,  c.cmpny_access_stat AS accessstatus, "
								+ " c.profilemodifiedby,  c.profilemodifieddate,  p.prsnttn_label AS presentationlabel, "
								+ " p.prsnttn_label_tag AS presentationlabeltag,  p.prsnttn_order AS presentationorder, "
								+ " c.permsmodifiedby,  c.permsmodifieddate,  c.ipmodifiedby,  c.ipmodifieddate, "
								//+ " c.lsrpassword,  c.lsrdomain,  c.altspid,  c.roamwisepath,  cr.RSRC_ID as permissionid "
								+ " c.lsrpassword,  c.lsrdomain,  c.altspid,  c.roamwisepath,  rrr.rsrc_id2 as permissionid "
								//+ " FROM company_xroads c,  company_type_code ctc  ,presentation p, "
								+ " FROM company_xroads c,  company_type_code ctc  ,presentation p, rsrc_to_rsrc_rel rrr,"
								+ " COMPANY_RESOURCE cr WHERE TRIM(ctc.cmpny_typ_cd) = TRIM(c.cmpny_typ_cd) "
								+ " AND TRIM(ctc.prsnttn_id) = TRIM(p.prsnttn_id) "
								+ " and c.cmpny_cd = cr.CMPNY_ID "
								+ " and upper(ctc.cmpny_typ_cd) <>upper('nonadmin')"
								+ " AND cr.rsrc_typ_cd = '" + XroadsEnums.RSRC_TYP_CD_PERMISSION + "' and cr.rsrc_id = rrr.rsrc_id1 "
								+ " AND rrr.rsrc_id2 = " + vals + " and rrr.rel_typ_cd = 'Product' and rrr.rsrc_typ_cd = cr.rsrc_typ_cd "
								+ sortAtt;
								//+ " AND cr.RSRC_TYP_CD='"
								//+ XroadsEnums.RSRC_TYP_CD_PRODUCT
								//+ "' "
								//+ " and cr.RSRC_ID in (" + vals + ")" + sortAtt;

					}
				}
			}

            if (bIdOnly) {
                rs = st.executeQuery(szQuery);
                ResultSetMetaData metaData = rs.getMetaData();

                while (rs.next()) {
                   companies.add(rs.getString(XroadsEnums.ATTR_CARRIER_UID));
                }
//            } else if (tinyObjects){
//                CarrierImpl impl = new CarrierImpl();
//                companies = impl.executeListDBOperation(szQuery.substring(szQuery.indexOf("FROM "), szQuery.length() - 1));
//            } else {
//                rs = st.executeQuery(szQuery);
//                ResultSetMetaData metaData = rs.getMetaData();
//
//                while (rs.next()) {
//                   companies.add(new XroadsCompany(rs.getString(XroadsEnums.ATTR_CARRIER_UID), sLDAPHost, iLDAPPort));
//                }
//            }
            } else {
                CarrierImpl impl = new CarrierImpl();
                companies = impl.executeListDBOperation(szQuery.substring(szQuery.indexOf("FROM "), szQuery.length() - 1));
            }
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
			throw new LDAPExceptionManipulator(e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new LDAPExceptionManipulator(e);
		} finally {
			try {
				CrossRoadsConnectionManager.getInstance().closeConnection(rs,
						st, conn);
			} catch (SQLException e) {
				LOGGER.error(e);
			}

		}

		return companies;

	}

