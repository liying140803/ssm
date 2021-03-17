package cn.no7player.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by liying
 * Date 2019-05-17
 */


public class TT {
      public static void main(String[] args) {
          Scanner scanner = new Scanner(System.in);
          while (true) {
              try {
                  int orderNo = scanner.nextInt();
                  System.out.println((orderNo % 1000 % 15) + 1);
              } catch (Exception e) {
                  System.out.println("输入的数据格式不对");
              }
          }
        /*
       public int hashCode() {
            int h = hash;//default 0
            if (h == 0 && value.length > 0) {
                char val[] = value;

                for (int i = 0; i < value.length; i++) {
                    h = 31 * h + val[i];
                }
                hash = h;
            }
            return h;
        }*/


/*    String a = " 'id':0, 'Name': 'Applepay', 'type': 'method', 'value': 'applepay', 'token': '', 'sub_options': None,\n" +
            "                'refundable': True, 'is_default': False, 'payment_channel': 0,\n" +
            "                'payment_channels': [10, 55, 56, 57, 58, 59, 60, 61, 62], 'icon_list': [\n" +
            "            {'key': 'mobile', 'size': '112*112',\n" +
            "                    'resource': 'https://res.klook.com/image/upload/v1576232855/%E6%94%AF%E4%BB%98%E6%96%B9%E5%BC%8FMobile/General/icon_payment_apple.png'},\n" +
            "            {'key': 'ios', 'size': '112*112',\n" +
            "                    'resource': 'https://res.klook.com/image/upload/v1576232855/%E6%94%AF%E4%BB%98%E6%96%B9%E5%BC%8FMobile/General/icon_payment_apple.png'},\n" +
            "            {'key': 'android', 'size': '112*112',\n" +
            "                    'resource': 'https://res.klook.com/image/upload/v1576232855/%E6%94%AF%E4%BB%98%E6%96%B9%E5%BC%8FMobile/General/icon_payment_apple.png'},\n" +
            "            {'key': 'desktop', 'size': '70*42',\n" +
            "                    'resource': 'https://res.klook.com/image/upload/v1576662807/%E6%94%AF%E4%BB%98%E6%96%B9%E5%BC%8FWeb/payment_default_70x42.png'}],\n" +
            "            'down_price': {'value': '10', 'currency': 'THB'}, 'up_price': {'value': '0', 'currency': 'THB'},\n" +
            "            'contract_account': '' '' ";

    for (int i = 0; i < list11.size(); i++) {
          if (a.contains((CharSequence) list11.get(i))) {
              System.out.println("存在");
          } else {
              System.out.println("不存在");
          }


      }*/


   /* static String src = "今天的天气真的好!!!!";

    public static void main(String[] args) throws IOException {
        OutputStream out = null;
        try {
            StringBuilder sb = new StringBuilder();
            byte[] buff = new byte[1024];
            //从字符串获取字节写入流
            InputStream is = new ByteArrayInputStream(src.getBytes("utf-8"));
            int len = -1;
            String outpath = "/Users/klook/Documents/doc/first.txt";
            File saveDir = new File(outpath);
            File dir = saveDir.getParentFile();
            if (!dir.exists()) {
                dir.mkdir();
            }
            if (!saveDir.exists()) {
                saveDir.createNewFile();
            }
            out = new FileOutputStream(outpath);
            while (-1 != (len = is.read(buff))) {
                //将字节数组转换为字符串
                String res = new String(buff, 0, len);
                sb.append(res);
                System.out.println(sb);
                out.write(buff, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }*/


    }

}
        