package com.whut.teaching.util;

import io.rong.RongCloud;
import io.rong.messages.TxtMessage;
import io.rong.models.CodeSuccessResult;
import io.rong.models.TokenResult;

/**
 * 对融云的操作封装
 * Created by wpc on 2017/5/21.
 */
public class RongYunUtil {

    private static final String appKey = "n19jmcy59nj69";
    private static final String appSecret = "G3Iep6hIk4tLy";
    private static final RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);

    /**
     * 用户获取融云的用户token
     * @param userId
     * @param userName
     * @param portratUri
     * @return
     */
    public static String getToken(String userId, String userName, String portratUri) {
        TokenResult userGetTokenResult = null;
        try {
            userGetTokenResult = rongCloud.user.getToken(userId, userName, portratUri);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TokenResult tokenResult = GsonUtils.parse(userGetTokenResult.toString(), TokenResult.class);

        return tokenResult.getToken();
    }

    /**
     * 发送系统消息，文本消息
     * @param fromId
     * @param toId
     * @param message
     * @return
     */
    public static CodeSuccessResult PublishSystem(String fromId, String[] toId,String message) {

        CodeSuccessResult codeSuccessResult = null;
        TxtMessage txtMessage = new TxtMessage(message, "");

        try {
            codeSuccessResult = rongCloud.message.PublishSystem(fromId, toId, txtMessage, "课堂消息", "", 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return codeSuccessResult;
    }

    /**
     * 一对一聊天，文本消息
     * @param fromId
     * @param toId
     * @param message
     * @return
     */
    public static CodeSuccessResult publishPrivate(String fromId, String toId, String message) {

        String[] to_a_Id = {toId};
        CodeSuccessResult codeSuccessResult = null;
        TxtMessage txtMessage = new TxtMessage(message, "");

        try {
            codeSuccessResult = rongCloud.message.publishPrivate(fromId,to_a_Id, txtMessage, "", "", "4", 0, 1, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return codeSuccessResult;
    }

}
