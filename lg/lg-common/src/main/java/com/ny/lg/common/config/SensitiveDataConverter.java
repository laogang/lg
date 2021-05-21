package com.ny.lg.common.config;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version 1.0.0
 * @author：guog
 * @date 2021/5/20 18:15
 * @description 日志脱敏
 */
public class SensitiveDataConverter extends MessageConverter {

    private Logger logger = LoggerFactory.getLogger(SensitiveDataConverter.class);

    private static String[] sensitiveDataKeys = new String[]{"aesKey", "publicKey", "privateKey"};

    @Override
    public String convert(ILoggingEvent event) {
        //获取原始日志
        String logMsg = event.getFormattedMessage();
        //获取脱敏后的日志
        return invokeLogMsg(logMsg);
    }

    private String invokeLogMsg(String logMsg) {
        if (sensitiveDataKeys == null || sensitiveDataKeys.length == 0) {
            return logMsg;
        }
        return matcherLogMsg(logMsg);
    }

    private String matcherLogMsg(String logMsg) {
        for (String key : sensitiveDataKeys) {
            logMsg = matcherLogMsg(logMsg, 0, key);
        }
        return logMsg;
    }

    private String matcherLogMsg(String logMsg, int idx, String key) {
        Pattern p = Pattern.compile(key);
        Matcher m = p.matcher(logMsg);
        int sum = 0;
        while (m.find()) {
            if (sum == idx) {
                int index = m.start();
                int valueStart = getValueStartIndex(logMsg, index + key.length());
                int valueEnd = getValueEndIndex(logMsg, valueStart);
                //对获取的值进行脱敏
                String subStr = logMsg.substring(valueStart, valueEnd);
                subStr = sensitive(subStr, key);
                logMsg = logMsg.substring(0, valueStart) + subStr + logMsg.substring(valueEnd);
                return matcherLogMsg(logMsg, idx + 1, key);
            }
            sum++;
        }
        return logMsg;
    }

    //后续扩展
    private String sensitive(String subStr, String key) {
        return "********";
    }

    /**
     * 获取值的结束位置
     *
     * @param msg
     * @param end
     * @return
     */
    private int getValueEndIndex(String msg, int end) {
        do {
            if (end == msg.length()) {
                break;
            }
            char ch = msg.charAt(end);
            if (ch == '"') {
                //保留数据完整json结构
                if (end - 1 > 0) {
                    char preCh = msg.charAt(end - 1);
                    if (preCh == '\\') {
                        end--;
                    }
                }
                break;
            } else {
                end++;
            }
        } while (true);
        return end;
    }

    /**
     * 获取值的开始位置
     *
     * @param msg
     * @param start
     * @return
     */
    private int getValueStartIndex(String msg, int start) {
        do {
            char ch = msg.charAt(start);
            if (ch == ':' || ch == '=') {
                start++;
                ch = msg.charAt(start);
                //保留数据完整json结构
                if (ch == '\\') {
                    start++;
                    ch = msg.charAt(start);
                }
                if (ch == '"') {
                    start++;
                }
                break;
            } else {
                start++;
            }
        } while (true);

        return start;
    }

}