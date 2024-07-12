package cn.no7player.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by liying
 * Date 2020-07-20
 */


public class AmountUtil {

    private static final String[] CN_NUMBERS = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private static final String[] CN_MONETETARY_UNIT = {"分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾",
            "佰", "仟", "兆", "拾", "佰", "仟"};
    private static final String CN_FULL = "整";
    private static final String CN_NEGATIVE = "负";
    private static final String CN_ZERO = "零角零分";
    private static final long MAX_NUMBER = 10000000000000000l;// 最大16位整数
    private static final String INVALID_AMOUNT = "金额超出最大金额千兆亿(16位整数)";

    //金额转成汉字大写金额
    public static String formatToCN(double amount) {
        if (Math.abs(amount) >= MAX_NUMBER)
            return INVALID_AMOUNT;
        StringBuilder result = new StringBuilder();
        long value = Math.abs(Math.round(amount * 100));
        int i = 0;
        while (true) {
            int temp = (int) (value % 10);
            result.insert(0, CN_MONETETARY_UNIT[i]);
            result.insert(0, CN_NUMBERS[temp]);
            value = value / 10;
            if (value < 1)
                break;
            i++;
        }
        // "零角零分" 转换成 "整"
        int index = result.indexOf(CN_ZERO);
        if (index > -1) {
            result.replace(index, index + 4, CN_FULL);
        }
        // 负数
        if (amount < 0) {
            result.insert(0, CN_NEGATIVE);
        }
        return result.toString();
    }

    //保留两位小数
    public static String getFormat(double amount) {
        DecimalFormat format = new DecimalFormat("###,###.00");
        String amount1 = format.format(amount);
        return amount1;
    }

    //获取百分比格式
    public static void getPercent(double data) {
        //创建一个中国地区的 百分比格式
        NumberFormat perFormat = NumberFormat.getPercentInstance(Locale.CHINA);
        DecimalFormat percentFormat;
        try {
            percentFormat = (DecimalFormat) perFormat;
            //设置Pattern 会使百分比格式，自带格式失效
            //percentFormat.applyPattern("##.00");
            //设置小数部分 最小位数为2，按小数点第3位数四舍五入
            percentFormat.setMinimumFractionDigits(2);
            System.out.println("百分比是：" + percentFormat.format(data));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //获取货币格式
    public static void getCurrency(double amount) {
        //创建一个中国地区的 货币格式
        NumberFormat curFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);
        DecimalFormat currencyFormat;
        try {
            currencyFormat = (DecimalFormat) curFormat;
            //设置Pattern 会使百分比格式，自带格式失效
            //currencyFormat.applyPattern("##.00");
            System.out.println(currencyFormat.format(amount));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        double amount = 1001.38534;
        double amountTest = 987654321.00d;
        System.out.println(getFormat(amount));
        getPercent(0.912345);
        getCurrency(0.912345);
        System.out.println(formatToCN(amountTest));
    }
}
