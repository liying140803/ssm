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
    public static void main(String[] args) throws ParseException {
/*        LinkedList<Student> list = new LinkedList<Student>();
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
        List list2 = new ArrayList();
        list2.add(1);
        list2.add(8);
        list2.add(7);
        list2.add(9);
        list2.add(4);

//        Collections.sort(list2);
        list2.sort(null);

        System.out.println(list2);

        System.out.println("set size:" + set.size());*/
        int arr[] = {1, 4, 3, 7};
        QuickSort(arr, 0, 3);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
      /*  System.out.println("180天之前的日期是：" + incr(new Date(), 180));
        System.out.println("180天之后的日期是：" + atferDate(new Date(), 180));
*/
     /*   diff("2020-07-20", "2020-12-31");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, -1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(calendar.getTime()));*/
        System.out.println("日期是："+cha("2023-12-25",178));

    }

    //计算两个日期相差的天数
    public static Long diff(String dateOne, String dateTwo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
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
     * 计算指定日期指定天数之后的日期
     * @param fromDate
     * @param day
     */
    public static String cha(String fromDate,int day){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = null;
        try {
            Date start_time = format.parse(fromDate);
            result = atferDate(start_time, day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
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

    //快速排序
    public static int[] QuickSort(int arr[], int start, int end) {
        if (start >= end)
            return arr;
        int i = start;
        int j = end;
        // 基准数
        int baseval = arr[start];
        while (i < j) {
            // 从右向左找比基准数小的数
            while (i < j && arr[j] >= baseval) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }
            // 从左向右找比基准数大的数
            while (i < j && arr[i] < baseval) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }
        }
        // 把基准数放到i的位置
        arr[i] = baseval;
        // 递归
        QuickSort(arr, start, i - 1);
        QuickSort(arr, i + 1, end);
        return arr;
    }
}

