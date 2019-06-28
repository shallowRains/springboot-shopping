package com.springcloud.common;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101100662518";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCY87TBYZROuSA5RuWhPCQpzRHkt5WxtD95lFRyOL8umkdJCGYAqrrYGhgpXRpfpG7dCpruhxtTPo3durqra80Wji3DoVt7WF7jKaiJ0t0SqWTGR+X/OxCjVqh0BBmvmsqMoPUUHI2grV9glHmU5gD/6Otz5veHbiykYM4RDtDaGw1hAP+WK4RpWVDRxmIp1ftVCUeOau5VORQu5/QyYUsutsuqXW1xgl/lfCI8pz7xtEwpfQrShosdlFcACnQwSjyWH8NphLJ624Wacba/T/jmwxh8gKuT1oxVTqMiGw1YUPX3U37Cn4SKsQFVb5dPW6xOPcGPUqmBi2DzUvmzr1DhAgMBAAECggEAZ2vSezgZ6ikvastPlfhuk31AbiDcrW4txW3Qs/M/Aqn7LZzqK+VFtb3fPIjhvm4XMIttO4BLr7wKfTU355Va9bdppnBCxWJ5tqyCc78LEQVNSO1wzWYI2AlOmjPzklPeRbzIOrMb6NuSNGHw3AeyAVhjbNlt4jtTIEr8y3fzLKY0D1PhWCwd4W/9TAp7g4hgVBey0Ijf10uwYLdWmOR2y+0dsX1W/0tlofOpEAj/fk24brQPG4JaKoCQyHGMqTh5dluta6qjEIkIP2rKTHf1BgJ8SGLqEzmdRhba7IGYnge65c5tQX5g6Hnal2QmZCacxCeFDWFjxCPgrv3cbIlPCQKBgQDis8YqM0tHh8dK+NLa7mQF3L2Y4anqFvri1Lrr1696y1okkcwRilB0lvG9ENOWWAMUGuRng2yIBRIroYyrJYGg6nuBx/rBuMCNTd09zSQL7BC7ahLUKVR52wOE7NfGvsEWGtOknrzshBgdCI6p2V1oXV2AaS4kuU1u4nh7jR6WCwKBgQCst/mtWgRmuG18ManZ/ib8gOc0S2Eg5Vabq0NNDXBi0fTaxeqt8ZpQoHZAOTqeQ346MSmm83KTHzGngpKBtC41kFJUMLzXv7CldHsuvU+ba6CjX1wRoqlx7SCDTAJqzOfUOq3jaCjIEtQTiXx0vnEm9ZOoGccEj0KY121qag2kQwKBgQCiWENM7Ta3ntBlLBMznQwb9pv/43qenJ5F2jmRsv+DXCTNk2skFHzRQVcUIfyj6ZsqxqdSSZdrQNZmUGZAGbfe2IxhwCgemoOprV0GaOWrc/33eFrLfF12dhTrGADd3rS/c66kcNiyN1ZdVEd18qclmdbfonLc8aY2BhBpygFhSQKBgDqFFy56CnW3VTk4JoF60EDLeArK43ze14pk66emvwZsdtbNKieHba6Vv5shCNBos9MJp0Auygo5DnzgC72wi9uQK6Tk4ocDJtnYbog2kktpmwLfo3VCNMEqA9MYpNUE2iOZQiODvhWT85EvCyEPCY2WtVHGch0EYR8iFqtwhXC7AoGBAMFqC93p0rsTEf00GOb7I+NFm1sVn175rFn7J85It2fF24AnqtfjhI82Owzql/lO3sD9U4cEs6O3N5TetUjlJhae+ic+mB0LBhUgJvioY46LNJDuOyPIjUOqadfrlyT8/UgmkfyN78qGpVA2MX2hu+qKAhZPDL+G2qOTNt5viWYX";
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo8as1nE2CACTx7ObUPLPr/0cpS3bjvPOZ7bI89gY5SYBxRYdG7QNflhsZlNCRN4kJWttT2IAXM1ROnLDYWwxguRXgKq120oGAGYLhLazAL+q8H1LvgajpLTMeeJm//Zpl0lP0kA3kWDX3lNEbbtIW8znj/55U3NMxOB9V2VSkgvg5uwwB+GHlwAQVECqfD46EchJdIq1DOrby9q7x8oer788XdIyeV6e1AeZXllsxD8R64XHh4F9vrnF2Wuwtr+NsmsdzyzktVYzlokGlW9vzrzSQedR30mv2XAEbTqSMo73nGiOT7PQLY12xFo8t/LyQeHEFyEZU+RC+6iXxDzYZwIDAQAB";
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:9001/orders/pay/alipay/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:9001/orders/pay/alipay/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
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

