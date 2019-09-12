package com.aiyicha.algorithm.test;

import com.aiyicha.algorithm.utils.*;
import com.sun.xml.internal.ws.util.StringUtils;
import org.junit.Test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class TestAlgorithm {

    @Test
    public void test1() {
        int r = AlgorithmUtils.digitCount(3, 12345);
        System.out.println(r);
    }

    @Test
    public void test2() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        for (String str : list.subList(0, 5)) {
            System.out.println(str);
        }
        for (String str : list.subList(5, 7)) {
            System.out.println(str);
        }
    }

    @Test
    public void test3() {
        Map<String, Object> map = new HashMap<>();
        List<String> communityIds = new ArrayList<>();
        communityIds.add("121313");
        map.put("communityIds", communityIds);
        List<String> cc = (List)map.get("communityIds");
        cc.stream().forEach(System.out :: println);
    }

    @Test
    public void test4() {
        int[] p = {3,2,5,7,9,1,9,3,4};
        quickSort(p, 0, p.length - 1);
        Stream.of(p).forEach(System.out :: println);
    }

    @Test
    public void test5() {
        String term = "201901";
        int year = Integer.parseInt(term.substring(0, 4));
        int month = Integer.parseInt(term.substring(4));
        System.out.println(year);
        System.out.println(month);
    }

    @Test
    public void test6() {
        String termStart = "201911";
        String termEnd = "202106";
        List<String> result = makeTermPeriod(termStart, termEnd);
        result.stream().forEach(System.out :: println);
    }

    @Test
    public void test7() {
        Map<String, String> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.addAll(null);
    }

    @Test
    public void test8() {
        BigDecimal b1 = new BigDecimal(3.44);
        b1.setScale(2, RoundingMode.HALF_UP);
        BigDecimal b2 = new BigDecimal(0.88);
        b2.setScale(2);
        BigDecimal b3 = b1.multiply(b2);
        System.out.println(b3);
    }

    @Test
    public void test9() {
        BigDecimal b1 = new BigDecimal(0.1);
        b1.setScale(2, RoundingMode.HALF_UP);
        BigDecimal b2 = new BigDecimal(3);
        BigDecimal b3 = b1.divide(b2, 2, RoundingMode.HALF_UP);
        System.out.println(b3);
    }

    @Test
    public void test10() {
        BigDecimal b = new BigDecimal("123456788.22");
        System.out.println(NumberToCN.getChineseNumber(b.toString()));
    }

    @Test
    public void test11() {
        BigDecimal b = new BigDecimal("103456788.00");
        System.out.println(NumberToCN.getChineseNumber(b.toString()));
    }

    @Test
    public void test12() throws Exception {
        Ref<String> macKey = new Ref<>("");
        Ref<String> cipherText = new Ref<>("");
        getEncryptPwd("123456", macKey, cipherText);
        String password = cipherText.get();
        String salt = macKey.get();
        System.out.println(password);
        System.out.println(salt);


    }

    @Test
    public void test13() throws Exception {
        String cipherText = CryptoUtil.encryptHMAC("123456", "RB6xg6u2p+cMdOVqePieW7dX1w4PUVSn8/s+4vLVtTHnjGEmMx7OcYZ+FExNmKaFF+eHjUMPtCbM\n" +
                "VFC9pEfJ7A==".replace("\r","").replace("\n","")).replace("\r","").replace("\n","");
        System.out.println("dT3LizKsiJExzZBmHQqvEN1H7JU=");
        System.out.println(cipherText);
    }

    @Test
    public void test14() throws Exception {
        byte s = -128;
        System.out.println(s & 0xff);
    }

    @Test
    public void test15() throws Exception {
        BigDecimal b = new BigDecimal(0.01);
        BigDecimal d = new BigDecimal(90);
        System.out.println(b.multiply(d).divide(new BigDecimal(100), RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void test16() throws Exception {
        int i = 1;
        Integer s = null;
        System.out.println(i == s);
    }

    @Test
    public void test17() throws Exception {
        String fileName = "ssss.xls";
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        int index = fileName.lastIndexOf(".");
        fileName = fileName.substring(0, index) + "_" + df.format(new Date()) + fileName.substring(index);
        System.out.println(fileName);
    }

    @Test
    public void test18() throws Exception {
        String file = "H4sIAAAAAAAEAIVQTU8bQQy9I/Efemwla+XxzOzs5N4Llx44tOcWDr2i/gAimpJCI1IlfKgFVCK+KlULLVFCCOqviWez/6Imm8BKVO3MwX7W87OfRzfHYa/Fzfo4PapQ9qMN+frnvHNQUahNpIhraxD2G7xxNO4OKhShu6tw7WTcPX3EnJ/j7Q+h3h+nHW5s81YfeHDFW5cPeHT9RaSyn0M+3AQhc6vKzUZ2dglc/8rDm7Dby3e7wOmnUG/OwEQzu92ZCF4Mws5AhksJCt1ijacy/9lEMu1xehjavaJyv/yUkHakUwij4V7RCKF9ISBcf8urrSnIV9v8+z1kG72wWgU+Xg9X52oaaX5u8QWh8qgpVoiGrE4Qnr98VSpqZ5VTsYPR5L6Qffwu1rPbriwCJZ7VVIYxIiwsotKEiM7FIgFy3iRR6OUhKqttbL2xVrpibxxpIOUjex8wMmaWc01OsQZLy6/fvgPu/yrMwRP5j0x4h6psgtA4R9ZhYv5pgtCSVWWokf5rIr5T184LichbD4ky0UNQUaxn+dTEm5Xlpb+4+AMRLx37wAIAAA==";
        //System.out.println(new String(uncompress(Base64.getDecoder().decode(file))));
        System.out.println(uncompressToString(Base64.getDecoder().decode(file),"UTF-8")+"hhhhh");
    }

    @Test
    public void test19() throws Exception {
        for (int i=1;i<27;i++) {

            if (i == 12) {
                continue;
            }
            String s = i < 10 ? "0" + i : i + "";
            System.out.println(HttpsUtil.httpsRequestToString("https://app.js.abchina.com/jiangsu/wecomm/api/community/statement/download?merchantId=JS0316501018259&billDate=201904" + s,"GET", null));
            System.out.println(HttpsUtil.httpsRequestToString("https://app.js.abchina.com/jiangsu/wecomm/api/community/statement/download?merchantId=JS0310300964661&billDate=201904" + s,"GET", null));
            System.out.println(HttpsUtil.httpsRequestToString("https://app.js.abchina.com/jiangsu/wecomm/api/community/statement/download?merchantId=JS0320600970312&billDate=201904" + s,"GET", null));
            System.out.println(HttpsUtil.httpsRequestToString("https://app.js.abchina.com/jiangsu/wecomm/api/community/statement/download?merchantId=JS0328700970119&billDate=201904" + s,"GET", null));
            System.out.println(HttpsUtil.httpsRequestToString("https://app.js.abchina.com/jiangsu/wecomm/api/community/statement/download?merchantId=JS0132000776716&billDate=201904" + s,"GET", null));
            System.out.println(HttpsUtil.httpsRequestToString("https://app.js.abchina.com/jiangsu/wecomm/api/community/statement/download?merchantId=JS0110501020410&billDate=201904" + s,"GET", null));
            System.out.println(HttpsUtil.httpsRequestToString("https://app.js.abchina.com/jiangsu/wecomm/api/community/statement/download?merchantId=JS0217501014854&billDate=201904" + s,"GET", null));
            System.out.println(HttpsUtil.httpsRequestToString("https://app.js.abchina.com/jiangsu/wecomm/api/community/statement/download?merchantId=JS0320700962514&billDate=201904" + s,"GET", null));
            System.out.println(HttpsUtil.httpsRequestToString("https://app.js.abchina.com/jiangsu/wecomm/api/community/statement/download?merchantId=JS0114800000714&billDate=201904" + s,"GET", null));
            System.out.println(HttpsUtil.httpsRequestToString("https://app.js.abchina.com/jiangsu/wecomm/api/community/statement/download?merchantId=JS0110501018236&billDate=201904" + s,"GET", null));
            System.out.println(HttpsUtil.httpsRequestToString("https://app.js.abchina.com/jiangsu/wecomm/api/community/statement/download?merchantId=JS0115401016956&billDate=201904" + s,"GET", null));
            System.out.println(HttpsUtil.httpsRequestToString("https://app.js.abchina.com/jiangsu/wecomm/api/community/statement/download?merchantId=JS0163401016943&billDate=201904" + s,"GET", null));
            System.out.println(HttpsUtil.httpsRequestToString("https://app.js.abchina.com/jiangsu/wecomm/api/community/statement/download?merchantId=JS0110501020420&billDate=201904" + s,"GET", null));
            System.out.println(HttpsUtil.httpsRequestToString("https://app.js.abchina.com/jiangsu/wecomm/api/community/statement/download?merchantId=JS0313200964221&billDate=201904" + s,"GET", null));
            System.out.println(HttpsUtil.httpsRequestToString("https://app.js.abchina.com/jiangsu/wecomm/api/community/statement/download?merchantId=JS0110501020418&billDate=201904" + s,"GET", null));
            System.out.println(HttpsUtil.httpsRequestToString("https://app.js.abchina.com/jiangsu/wecomm/api/community/statement/download?merchantId=JS0110501021064&billDate=201904" + s,"GET", null));
            System.out.println(HttpsUtil.httpsRequestToString("https://app.js.abchina.com/jiangsu/wecomm/api/community/statement/download?merchantId=JS0110501020423&billDate=201904" + s,"GET", null));
            System.out.println(HttpsUtil.httpsRequestToString("https://app.js.abchina.com/jiangsu/wecomm/api/community/statement/download?merchantId=JS0124200987230&billDate=201904" + s,"GET", null));
            System.out.println(HttpsUtil.httpsRequestToString("https://app.js.abchina.com/jiangsu/wecomm/api/community/statement/download?merchantId=JS0110501020427&billDate=201904" + s,"GET", null));
            System.out.println(HttpsUtil.httpsRequestToString("https://app.js.abchina.com/jiangsu/wecomm/api/community/statement/download?merchantId=JS0312300948315&billDate=201904" + s,"GET", null));
        }

    }

    @Test
    public void test20() {
        for (int i=1;i<27;i++) {
            String s = i < 10 ? "0" + i : i + "";
            if (i == 12) {
                continue;
            }
            System.out.println(HttpsUtil.httpsRequestToString("https://app.js.abchina.com/jiangsu/wecomm/api/community/statement/reconciliate?billDate=201904" + s,"GET", null));
        }
    }

    @Test
    public void test21() {
        Map<String, String> map = new HashMap<>();
        map.put("hello","2334");
        System.out.println(map.toString());
    }

    public static String uncompressToString(byte[] bytes, String encoding) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            return out.toString(encoding);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] compress(String str, String encoding) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(encoding));
            gzip.close();
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public static byte[] uncompress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    private static List<String> makeTermPeriod(String termStart, String termEnd) {
        List<String> result = new ArrayList<>();
        int termStartInt = Integer.parseInt(termStart);
        int termEndInt = Integer.parseInt(termEnd);
        for (int i = termStartInt; i <= termEndInt; i++) {
            result.add(i + "");
            if (i - i / 100 * 100 == 12) {
                i = (i / 100 + 1) * 100;
            }
        }
        return result;
    }

    protected void getEncryptPwd(String password, Ref<String> macKey, Ref<String> cipherText) throws Exception{
        macKey.set(CryptoUtil.initMacKey());
        cipherText.set(CryptoUtil.encryptHMAC(password, macKey.get()));
    }

    private static void quickSort(int[] param, int l , int r) {
        while (l < r) {
            int k = partition(param, l, r);
            quickSort(param, l, k - 1);
            quickSort(param, k + 1, r);

        }
    }

    private static int partition(int[] p, int left, int right) {
        int i = left;
        int key = p[right];
        for (int j = left; j < right; j++) {
            if (p[j] < key) {
                i++;
                swap(p, i, j);
            }
        }
        i++;
        swap(p, i ,right);
        return i;
    }

    private static void swap(int[] p, int l, int r) {
        int temp = p[r];
        p[r] = p[l];
        p[l] = temp;
    }
}
