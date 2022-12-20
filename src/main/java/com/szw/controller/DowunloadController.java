package com.szw.controller;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Auther: szw
 * @Date: 2022/10/29 - 10 - 29 - 19:10
 * @Description: com.szw.controller
 * @version: 1.0
 */
@Controller
public class DowunloadController {
    @RequestMapping("/downloaddemo")
    public ResponseEntity<byte[]> getFile(HttpSession session) throws IOException {
        //获取servletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取真实路径
        String realpath = "/static/img/n2.png";
        //部署路径
        InputStream fileInputStream = new FileInputStream(servletContext.getRealPath(realpath));
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=n2.png");
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, status);
        fileInputStream.close();
        return responseEntity;

    }

    @RequestMapping("/uploaddemo")
    public String testupload(MultipartFile photo, HttpSession session) throws IOException {
        String name = photo.getOriginalFilename();
//解决文件重名问图
        String suffixName = name.substring(name.lastIndexOf("."));
        // uuid
        String uuid = UUID.randomUUID().toString();
        name = uuid + suffixName;

        ServletContext servletContext = session.getServletContext();
        String photopath = servletContext.getRealPath("photo");
        File file = new File(photopath);
        if (!file.exists()) {
            file.mkdir();
        }
        String finalPth = photopath + File.separator + name;
        photo.transferTo(new File(finalPth));

        return "success";
    }
}
