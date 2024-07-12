package cn.no7player.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.*;

/**
 * Created by liying
 * Date 2020-08-07
 */


public class HttpUrl {
    //post请求提交json数据
    public static String getPostDataByUrl(String url, JSONObject json) {
        String data = null;
        //构造HttpClient的实例
        HttpClient httpClient = new HttpClient();
        //创建post方法的实例
        PostMethod postMethod = new PostMethod(url);
        //设置头信息
        postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:39.0) Gecko/20100101 Firefox/39.0");
        postMethod.addRequestHeader("Content-Type", "application/json;charset=UTF-8");
        //遍历设置要提交的参数
      /*  Iterator it = array.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) it.next();
            String key = entry.getKey();
            String value = entry.getValue().trim();
            postMethod.setParameter(key, value);
        }*/
        String toJson = json.toString();
        try {
            RequestEntity se = new StringRequestEntity(toJson, "application/json", "UTF-8");
            postMethod.setRequestEntity(se);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //使用系统提供的默认的恢复策略
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        try {
            //执行postMethod
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed:" + postMethod.getStatusLine());
            }
            //读取内容
            byte[] responseBody = postMethod.getResponseBody();
            //处理内容
            data = new String(responseBody);
        } catch (HttpException e) {
            //发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("Please check your provided http address!");
            data = "";
            e.printStackTrace();
        } catch (IOException e) {
            //发生网络异常
            data = "";
            e.printStackTrace();
        } finally {
            //释放连接
            postMethod.releaseConnection();
        }
        System.out.println(data);
        return data;
    }

    /*
    上传文件
     */
    public static String doUploadFile(File file, String url) throws IOException {
        String response = "";
        if (!file.exists()) {
            return "file not exists";
        }
        PostMethod postMethod = new PostMethod(url);
        try {
            //----------------------------------------------
            // FilePart：用来上传文件的类,file即要上传的文件
            FilePart fp = new FilePart("file", file);
            Part[] parts = {fp};

            // 对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装
            MultipartRequestEntity mre = new MultipartRequestEntity(parts, postMethod.getParams());
            postMethod.setRequestEntity(mre);
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);// 由于要上传的文件可能比较大 , 因此在此设置最大的连接超时时间
            int status = client.executeMethod(postMethod);
            if (status == HttpStatus.SC_OK) {
                InputStream inputStream = postMethod.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String str = "";
                while ((str = br.readLine()) != null) {
                    stringBuffer.append(str);
                }
                response = stringBuffer.toString();
            } else {
                response = "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放连接
            postMethod.releaseConnection();
        }
        System.out.println(response);
        return response;
    }


    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "王女士");
        jsonObject.put("amount", "1234567");
        //getPostDateByUrl("http://127.0.0.1:8080/addUserInfo", jsonObject);
        try {
            Long startTime = System.currentTimeMillis();
            doUploadFile(new File("/Users/klook/Documents/gitProject/test/src/main/resources/static/upload/imgs/背景.jpg"), "http://127.0.0.1:8080/uploadFile");
            Long endTime = System.currentTimeMillis();
            System.out.println("耗时" + (endTime - startTime) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
