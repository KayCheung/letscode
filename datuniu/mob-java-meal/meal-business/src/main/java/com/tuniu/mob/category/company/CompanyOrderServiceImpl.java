package com.tuniu.mob.category.company;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by libing2 on 2015/6/10.
 */
public class CompanyOrderServiceImpl {
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0";
    private static final Logger LOG = LoggerFactory.getLogger(CompanyOrderServiceImpl.class);

    @Value("${meal.departments}")
    private List<String> departments;

    public static void main(String[] args) {
    }

    private static class LoginResponse {
        private boolean success;
        private String msg;
        private int errorCode;
        private Data data;

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        private static class Data {
            private Integer userId;

            public Integer getUserId() {
                return userId;
            }

            public void setUserId(Integer userId) {
                this.userId = userId;
            }
        }
    }
}
