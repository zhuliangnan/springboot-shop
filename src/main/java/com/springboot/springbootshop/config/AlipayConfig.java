package com.springboot.springbootshop.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author: zhuliangnan
 * @date :
 * @blog:
 * @version: 1.0.0
 * @description:支付宝接入配置
 **/
public class AlipayConfig {


    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2016102100729057";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCX8nL+D1yoXAzwPg/tJ43NEQNohxTbNGp9xroo/pRjVsWSDCrA5YK9EsJDMlsHHzAIPvIkchzaYvhRDredozBI8AG2kQ9Mmxgp467QsgXHhuKPDNDQplhJ8y2RN3qHKDatgbS3jskxSbhq9ok4BgLM9VudgKX/xuZLROaLrHBXz/Ka9Cx3W8CdP5dFjGDqTTb1qsTNIkh9KxQMHCGcDdL+5mjPApOG9YDokhXJ1a9WRpcxL1lFvgjsxDMS7JMk6eP05C77ch+/lDL++BQHipBDmY469Go1CFweDxo8IMfcpnqwuK7iX4h32iXSC4gG2nnn23k4bCcc1+Ig8Sln+nYPAgMBAAECggEBAJBTgJqFqPNaDAgeSy5uRailImGk/VCBZy11F9pchkIdZ6ij2fBDkH1bVh5RtFj9bLhIYePwaCufmlIT8qDPIswE/PuWqbuQDdGsLJ6amha9rnFGMQofFvmW/I9U7RN4RQdljmxUOhKQ/ZTL++a0/vADljPywLpFCPYxyfKdplAhBI+rwLFXC86+KpwC9p1q2hnScQ9hyLW2qyS3HYbWYOrVx7v7Z0xS/oFs3yI5MAklUi2+N5jB148pbDf8OtXgDvgGgqxvb6U+MQedM81hxvla5pgUHUdnAYQmGXQX4cFkQAVYUk7cBOOuAVG1GEBBRDc81ywR7Om0hc0lbHiM9SECgYEAx/qwpYd6ukZLLhS5pMKDKFH/dAIVQm7F02KWZx/yTzaHR714UjOuiHNa7tecV4uy06BypatQbKTdD/WTfp61+ikTQXxzoMc95cBnrlJ1d3ey5tNEUWzMh3Hy/Lh4VqYzESeLoNaONcPMec/jRO7LdRiMcoAeqZvkulgLEPAG8QcCgYEAwoMt4snIR5Bhjtf0dFm5j11NjmxOBlAygF3YESJh54u1yMzEWcEem4f+1X2RpPbUbk071ikGiv8I0hf1dPG9Wyjbu3wbUuxFmIkyIlW1TMxBU09D5qDzTttOTiW3RbjwV7jtqKUN7vHcHvota1U2eMEPDzy4FLY3cuM/EN5teLkCgYB7K2eTfF9VzafOaQtydC9uLRrmq8XGVg3YRXeXarDGozItOICcOKK9/ja6tuffb/ajq8YRTfPKBrz1cG745O32d0gZ5HofQ7hXAuuZDca13268BtGkSRusLL3ZdBECZXk8jpvMY3nRmZgeSrGTKnT72TPf7hN0qcT2FPYz53KPDwKBgQCAFITYeuoIRYgX2Re8rYBEaLmy+JoACjrWz/SAg9VX9GUFDeCVAVc4LrqctYx7xZRn9S9be7Q1Pm3+eYxFLxDyv9rAf542YiIJqP18Ile5dB3nz5/0czMDT+EmECu4G2kXOkwz6ktcdaHuO+pAbOHrbaSO8fp8mOe2HPDgGLaY8QKBgB/i3zUI9BRGCpDopfmYkAp1v1QuFoED6Hxn0BYyOWP1m8+81GQAcQuKh8Qut0iC2g9h01fAQ+1NifR0ID0dJl4ZZw/mv32FuxB0HYORGkwar8t35ulA+0QmmVcvs/Z4BACYTouvg6PQKLtQFKnL1gRpWL7eIvEGTGqQMt548s0c";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgoseNOt5f3T0LF3lhWISvtPwDMq1mZsS2R9vkBD9sVCtFFqo3Vf+1ajjgMJHrtXMDiRgUJ0BenjChjk1Nwcs6uZ/gZo2WOk5dC6bCb2K21P4tLXF87TXM+u/LlVSzvnIoYbTilMYVIdaNf4T6e1q4FetSXsKh1MVsKfkWSG3R7FJHv8rxgsVCNgqZZ+M0OWomFC95v2wIzk1OzMxUFofeGfRjRfrHM18AwEVojxV7SVQqEC3h4xDbI3BbMKO3razsFAWZkToLTd5HBU5Lj3O+mtnDV3AHZUaBoy/NWe2KHHK5ABrL3Wrf7RuMYAMGH7jesgQb/OTc1DIXc9/pM7VvwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/springboot-web/Login";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://localhost:8080/springboot-web/Login";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 日志路径
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
