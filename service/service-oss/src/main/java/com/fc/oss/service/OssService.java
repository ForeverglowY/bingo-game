package com.fc.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Everglow
 * Created at 2022/06/29 0:33
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
