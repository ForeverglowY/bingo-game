package com.fc.oss.controller;


import com.fc.commonutils.R;
import com.fc.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Everglow
 * Created at 2022/06/29 0:33
 */
@RestController
@RequestMapping("/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;
    /**
     * 上传图片的方法
     * @param file 上传的图片
     * @return
     */
    @PostMapping("/upload")
    public R uploadOssFile(MultipartFile file) throws Exception {
        //得到上传的文件的路径
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url", url);
    }

    /**
     * 上传文件列表
     */
    @PostMapping("/uploadList")
    public R uploadOssFileList(List<MultipartFile> fileList) {
        List<String> urlList = new ArrayList<>();
        for (MultipartFile file : fileList) {
            String url = ossService.uploadFileAvatar(file);
            urlList.add(url);
        }
        return R.ok().data("urlList", urlList);
    }
}
