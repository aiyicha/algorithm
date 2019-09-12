package com.aiyicha.algorithm.utils;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpService {

    public String post(String url, String body,String requestEncoding, String responseEncoding) throws Exception {
        String result = null;
        BufferedReader bufferedReader = null;
        try {
            URL request = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) request.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            //有数据提交时
            if (body != null) {
                String property = "";
                if (requestEncoding != null && !"".equals(requestEncoding)) {
                    property = "application/json; charset=" + requestEncoding;
                }
                else {
                    property = "application/json; charset=utf-8";
                }
                conn.setRequestProperty("Content-Type", property);
                OutputStream outputStream = conn.getOutputStream();
                if (requestEncoding != null && !"".equals(requestEncoding)) {
                    outputStream.write(body.getBytes(requestEncoding));
                } else {
                    outputStream.write(body.getBytes("UTF-8"));
                }
                outputStream.flush();
                outputStream.close();
            } else {
                conn.setRequestProperty("Content-Length","0");
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.write("".getBytes("UTF-8"),0,0);
                os.close();
            }

            // 将返回的输入流转换成字符串
            if (responseEncoding != null && !"".equals(responseEncoding)) {
                bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(),responseEncoding));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            }
            String str;
            StringBuilder builder = new StringBuilder();
            while ((str = bufferedReader.readLine()) != null) {
                builder.append(str);
            }
            conn.disconnect();
            result = builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException execption) {
                execption.printStackTrace();
            }
        }
        return result;
    }

    public String getQryCardProfile(String merchantId, String billDate) {
        String json = "{\"MerchantId\":\"" + merchantId + "\",\"MerchantId\":\"" + billDate + "\"}";

        //String profieAddress = "http://10.39.8.91:8001/InsideWebSite/GetBillListOp" + ".json";
        String profieAddress = "http://10.39.12.56:8001/InsideWebSite/GetBillListOp" + ".json";
        System.out.println("getQryCardProfile：参数：" + json);
        try {
            String result = post(profieAddress, json, "UTF-8", "UTF-8");
            if (result != null && !"".equals(result)){
                return result;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static void main(String[] args) {
        HttpService httpService = new HttpService();
        String result = httpService.getQryCardProfile(args[0], args[1]);
        System.out.println(result);
    }
}
