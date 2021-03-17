package cn.no7player.util;

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
        TokenTest test = new TokenTest();
        test.TokenTest();
    }
}