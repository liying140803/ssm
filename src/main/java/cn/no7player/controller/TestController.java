package cn.no7player.controller;

import cn.no7player.model.UserExcel;
import cn.no7player.model.UserInfo;
import cn.no7player.service.UserService;
import cn.no7player.util.CommonResult;
import cn.no7player.util.ExcelUtil;
import cn.no7player.util.PDFUtil;
import cn.no7player.util.WordUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liying
 * Date 2020-08-10
 */


@Controller
public class TestController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    public final static String IMG_PATH = "static/upload/imgs";

    /**
     * 下载excel
     *
     * @param response
     * @param id
     */
    @RequestMapping("/downLoadExcel")
    @ResponseBody
    public void downLoadExcel(HttpServletResponse response, String id) {
        int rowNum = 1;
        String headers[] = {"姓名", "金额"};
        List<UserInfo> userList = userService.getUserInfo(id);
        List list = new ArrayList();
        if (userList != null && userList.size() > 0) {
            logger.info("user:" + userList);
            for (int i = 0; i < userList.size(); i++) {
                UserExcel user = new UserExcel();
                user.setName(userList.get(i).getName());
                user.setAmount(userList.get(0).getAmount());
                list.add(user);
            }
        }
        System.out.println(list);
        try {
            Boolean flag = ExcelUtil.downloadExcel("用户表", "信息表", headers, list, response);
            if (flag != true) {
                System.out.println("下载excel失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("downLoadExcel()方法中ExcelUtil.downLoadExcel()出现异常");
        }
    }

    /**
     * 下载word
     */
    @RequestMapping("/downLoadWord")
    public void downLoadWord(HttpServletResponse response) {
        try {
            /** 文件名称，唯一字符串 */
            Random r = new Random();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            StringBuffer sb = new StringBuffer();
            sb.append(sdf1.format(new Date()));
            sb.append("_");
            sb.append(r.nextInt(1000));
            //文件路径
            String filePath = "/Users/klook/Documents/";
            //文件名称
            String fileName = sb + ".doc";
            Map<String, Object> dataMap = new HashMap<String, Object>();
            /** 组装数据 */
            dataMap.put("userName", "seawater");
            dataMap.put("name", sb);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            dataMap.put("date", sdf.format(new Date()));
            dataMap.put("content", "freeMark生成Word文档段落内容");
            List<Map<String, Object>> listInfo = new ArrayList<Map<String, Object>>();
            for (int i = 1; i <= 10; i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("title", "标题" + i);
                map.put("content", "内容" + i);
                map.put("author", "作者" + i);
                listInfo.add(map);
            }
            dataMap.put("listInfo", listInfo);
            System.out.println(dataMap);
            /** 生成word*/
            WordUtil.createWord(response, dataMap, "template.ftl", filePath, fileName);

        } catch (Exception e) {
            System.out.println(e);
            logger.info("downLoadWord方法出现异常");
        }
    }

    /**
     * 下载pdf
     */
    @RequestMapping("/downLoadPdf")
    public void downLoadPdf(HttpServletResponse response) {
        try {
            /** 文件名称，唯一字符串 */
            Random r = new Random();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            StringBuffer sb = new StringBuffer();
            sb.append(sdf1.format(new Date()));
            sb.append("_");
            sb.append(r.nextInt(1000));
            //文件名称
            String fileName = sb + ".pdf";
            String filePath = "/Users/klook/Documents/pdf/test.pdf";
            //生成  合同文件
            PDFUtil.createPdf(filePath);
            //下载
            PDFUtil.downloadFile(response, fileName, filePath);
        } catch (Exception e) {
            System.out.println(e);
            logger.info("downLoadPdf方法出现异常");
        }
    }

    /**
     * 上传文件
     *
     * @param request
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return CommonResult.failed("文件不能为空");
        }
        if (file.getSize() / (1024 * 1024) >= 3) {
            return CommonResult.failed("文件不能超过3M");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件上传后的路径
        String fileDirPath = "src/main/resources/" + IMG_PATH;
        File fileDir = new File(fileDirPath);
        if (!fileDir.exists()) {
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        System.out.println(fileDir.getAbsolutePath());
        File dest = new File(fileDir.getAbsolutePath() + File.separator + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        System.out.println(dest);
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return CommonResult.failed("上传失败");
        } catch (IOException e) {
            e.printStackTrace();
            return CommonResult.failed("上传失败");
        }
        return CommonResult.success("上传成功");
    }
}


