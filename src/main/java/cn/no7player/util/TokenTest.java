package cn.no7player.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Created by liying
 * Date 2020-12-15
 */


public class TokenTest {

    //Token加解密测试
    public void TokenTest() {
        TokenUtil tku = new TokenUtil();
        Token tk = new Token();

        String result = tku.creatToken("asdfghjkl", "admin");
        System.out.println(result);
        tk = tku.getTokenData(result);
        System.out.println(tk.toString());
    }

    public static void main(String[] args) {
/*        TokenTest test = new TokenTest();
        test.TokenTest();*/
        String data = " {\n" +
                "                \"merchantID\":\"764764000004293\",\n" +
                "                \"invoiceNo\":\"1789661100-2617e7f8-c06cb090\",\n" +
                "                \"cardNo\":\"\",\n" +
                "                \"amount\":265,\n" +
                "                \"userDefined1\":\"\",\n" +
                "                \"userDefined2\":\"\",\n" +
                "                \"userDefined3\":\"\",\n" +
                "                \"userDefined4\":\"\",\n" +
                "                \"userDefined5\":\"\",\n" +
                "                \"currencyCode\":\"THB\",\n" +
                "                \"cardToken\":\"\",\n" +
                "                \"recurringUniqueID\":\"\",\n" +
                "                \"tranRef\":\"4024096\",\n" +
                "                \"referenceNo\":\"3794898\",\n" +
                "                \"approvalCode\":\"9ZDUSG\",\n" +
                "                \"eci\":\"05\",\n" +
                "                \"transactionDateTime\":\"20210806112404\",\n" +
                "                \"agentCode\":\"TMN\",\n" +
                "                \"channelCode\":\"TM\",\n" +
                "                \"issuerCountry\":\"  \",\n" +
                "                \"installmentMerchantAbsorbRate\":null,\n" +
                "                \"idempotencyID\":\"\",\n" +
                "                \"respCode\":\"0000\",\n" +
                "                \"respDesc\":\"Transaction is successful.\"\n" +
                "}";
        JSONObject jsonObj = JSONObject.parseObject(data);
        for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
            Object o = entry.getValue();
            if (o instanceof String) {
                System.out.println("key:" + entry.getKey() + "，value:" + entry.getValue());
            }
        }
        TokenUtil tku = new TokenUtil();
        String result = tku.getJsonData(jsonObj);
        System.out.println(result);

    }
}