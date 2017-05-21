package com.whut.teaching.util;

import io.rong.RongCloud;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.Reader;
import java.util.Date;

/**
 * Created by wpc on 2017/5/19.
 */
public class TokenUtil {

    /**
     * 自己生成token
     * @param sid
     * @return
     */
    public static String genToken(String sid) {
        return DigestUtils.md5Hex(sid + System.currentTimeMillis());
    }

}
