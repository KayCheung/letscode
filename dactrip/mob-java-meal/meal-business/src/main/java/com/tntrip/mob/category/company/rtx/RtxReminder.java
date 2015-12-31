package com.tntrip.mob.category.company.rtx;

import com.alibaba.fastjson.JSON;
import com.tntrip.mob.category.util.CommUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by libing2 on 2015/11/2.
 */

@Service
public class RtxReminder implements ApplicationListener<ApplicationEvent> {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(RtxReminder.class);

    public static String PUSH_URL = "http://172.30.40.11:8083/push/sendRTXMsg";
    @Autowired
    private Organization0 org0;

    private ScheduledExecutorService esRemoveWaitingUser = Executors.newSingleThreadScheduledExecutor((r) -> new Thread(r, "push-meal-reminder"));

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            // 30s 一次的频率调用 removeOldestWaitingUsers()
            // esRemoveWaitingUser.scheduleWithFixedDelay(this::doPush, 10, 30, TimeUnit.SECONDS);
        }
    }

    public int whoseDuty(List[] listGroups) {
        int weekOfYear = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        int reminder = weekOfYear % listGroups.length;
        int who = reminder;
        LOG.info("whoseDuty-- weekOfYear={}, reminder={}, who={}", weekOfYear, reminder, who);
        return who;
    }

    public void main(String[] args) {
        whoseDuty(new List[4]);
    }


    /**
     * 推送消息到RTX（所有发送给客服的消息都会推送）
     *
     * @param
     * @return boolean
     */
    private boolean doPush() {
        List[] listGroups = org0.getGroups();

        @SuppressWarnings("unchecked")
        List<String> onDutyGroup = (List<String>) listGroups[whoseDuty(listGroups)];

        List<Integer> ids = new ArrayList<>(onDutyGroup.size());
        List<String> names = new ArrayList<>(onDutyGroup.size());
        for (String aMember : onDutyGroup) {
            int pos = aMember.indexOf(":");
            String userId = aMember.substring(0, pos);
            ids.add(CommUtil.str2int(userId, -1));
            String name = aMember.substring(pos + 1);
            names.add(name);
        }

        RTXRequestVO msg = createRtxMsg(names);
        try {
            for (Integer aUserId : ids) {
                msg.setUserId(aUserId);
                String rv = JSON.toJSONString(msg);
                String result = getNotBase64(PUSH_URL, rv, 1);
                LOG.info("push message to rtx userId -->" + msg.getUserId() + "the url:" + PUSH_URL + " result is:" + result);
            }
            return true;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }


    private RTXRequestVO createRtxMsg(List<String> names) {
        RTXRequestVO msg = new RTXRequestVO();
        msg.setTitle("今天你领饭");
        msg.setContent(createContent(names));
        msg.setUserId(-1);
        return msg;
    }

    private String createContent(List<String> names) {
        StringBuilder sb = new StringBuilder();
        sb.append("本周是年内第 ");
        sb.append(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
        sb.append(" 周，该你和你的小伙伴们领饭了（");
        CommUtil.concat(sb, names, "，", "");
        sb.append("）。代表组织感谢你们 ^_^。\n领饭信息：\nhttp://172.30.40.11:8081/meal/summary\n");
        return sb.toString();
    }


    /**
     * 获取数据 不需要base64数据 采用post方式提交
     *
     * @param requestUrl
     * @param param
     * @param timeout
     * @return
     */
    public static String getNotBase64(String requestUrl, String param, long timeout) {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        Future<HttpResponse> future = null;
        String value = null;
        try {
            httpclient.start();
            HttpPost request = new HttpPost(requestUrl);
            request.setHeader("Content-Type", "application/json");
            //放入参数
            request.setEntity(new NStringEntity(param, "UTF-8"));
            future = httpclient.execute(request, null);
            //接口设置2秒把返回直接报错
            HttpResponse response = future.get(timeout, TimeUnit.SECONDS);
            HttpEntity entity = response.getEntity();
            value = EntityUtils.toString(entity);
        } catch (InterruptedException | ExecutionException | IOException e) {
            LOG.error(e.getMessage(), e);
        } catch (TimeoutException e) {
            future.cancel(true);
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return value;
    }


}
