package com.tuniu.mob.category.company;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.tuniu.mob.category.util.CommUtil;

/**
 * Created by libing2 on 2015/7/25.
 */
public class Beautiful {
    public static final String ENTER = "\r\n";

    public static final String HTML_HEADER = "<html><body>";
    public static final String HTML_TAIL = "</body></html>";
    public static final String BR = "<br/>";


    public static final String TABLE_HEADER = "<table width=\"550\"><tbody><tr align=\"left\"><th>名称</th><th>订餐部门</th><th>订餐人</th></tr>";
    public static final String TABLE_TAIL = "</tbody></table>";

    public static String beautiful(int totalCount, List<String> listDeptName, Map<String, List<MealInfo>> mapSaler2ListMeal) {
        StringBuilder sb = new StringBuilder(HTML_HEADER + BR );
        sb.append("<b>");
        CommUtil.concat(sb, listDeptName, "，", "");
        sb.append(" 订餐 总份数 <font color=\"red\"><b>" + totalCount +
                "</b></font></b>" + BR + BR);
        sb.append(map2Str(mapSaler2ListMeal));
        sb.append(Beautiful.BR + Beautiful.BR + Beautiful.BR);
        sb.append(HTML_TAIL);
        return sb.toString();
    }

    private static String map2Str(Map<String, List<MealInfo>> mapSaler2ListMeal) {
        StringBuilder sb = new StringBuilder();
        for (Entry<String, List<MealInfo>> e : mapSaler2ListMeal.entrySet()) {
            String saler = e.getKey();
            List<MealInfo> list = e.getValue();

            sb.append("************* " + saler + " ：<font color=\"red\"><b>" + list.size() +
                    "</b></font> *************" + BR);

            sb.append(TABLE_HEADER);
            for (MealInfo m : list) {
                sb.append(meal2ShowStr(m));
            }
            sb.append(TABLE_TAIL);
            sb.append(BR);
        }
        return sb.toString();
    }

    private static String meal2ShowStr(MealInfo m) {
        String td_open = "<td>";
        String td_close = "</td>";

        StringBuilder sb = new StringBuilder();
        sb.append("<tr>");
        sb.append(td_open + m.saler + td_close);
        sb.append(td_open + m.deptName + td_close);
        sb.append(td_open + m.owner + td_close);
        sb.append("</tr>");

        return sb.toString();
    }
}
