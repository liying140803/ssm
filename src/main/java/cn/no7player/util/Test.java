package cn.no7player.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**
 * Created by liying
 * Date 2020-01-15
 */


public class Test {
    public static void main(String[] args) {
        LinkedList<Student> list = new LinkedList<Student>();
        Set<Student> set = new HashSet<Student>();
        Student stu1 = new Student(3, "张三");
        Student stu2 = new Student(3, "张三");
        System.out.println("stu1 == stu2 : " + (stu1 == stu2));
        System.out.println("stu1.equals(stu2) : " + stu1.equals(stu2));
        list.add(stu1);
        list.add(stu2);
        System.out.println("list size:" + list.size());

        set.add(stu1);
        set.add(stu2);
        System.out.println("set size:" + set.size());
      /*  System.out.println("180天之前的日期是：" + incr(new Date(), 180));
        System.out.println("180天之后的日期是：" + atferDate(new Date(), 180));
*/
     /*   diff("2020-07-20", "2020-12-31");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, -1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(calendar.getTime()));*/
        dayCompare("2019-04-04", "2021-03-16");

    }

    //计算两个日期相差的天数
    public static Long diff(String dateOne, String dateTwo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
        long diff1 = 0;
        try {
            Date date1 = dateFormat.parse(dateOne);
            Date date2 = dateFormat.parse(dateTwo);
            diff1 = (date2.getTime() - date1.getTime()) / (24 * 3600 * 1000);
            System.out.println("日期相差：" + diff1 + "天");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff1;
    }

    //计算当前时间指定天数之前的日期
    public static String incr(Date date, int days) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -days);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time1 = format.format(calendar.getTime());
        return time1;
    }

    //计算当前时间指定天数之后的日期
    public static String atferDate(Date date, int days) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time1 = format.format(calendar.getTime());
        return time1;
    }

    /**
     * 计算2个日期之间相差的相差多少月
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    public static int dayComparePrecise(String fromDate, String toDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        int result = 0;
        try {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(sdf.parse(fromDate));
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(sdf.parse(toDate));
            result = (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
            //result = "结果相差" + year + "年," + month + "月,共" + day + "天";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result == 0 ? 1 : Math.abs(result);

    }

    public static String dayCompare(String fromDate, String toDate) {

        Period period = Period.between(LocalDate.parse(fromDate), LocalDate.parse(toDate));
        StringBuffer sb = new StringBuffer();
        sb.append(period.getYears()).append("年")
                .append(period.getMonths()).append("月")
                .append(period.getDays()).append("天");
        System.out.println(sb.toString());
        return sb.toString();
    }
}
