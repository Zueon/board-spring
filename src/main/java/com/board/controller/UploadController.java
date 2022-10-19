package com.board.controller;

import com.board.domain.AttachmentDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@Log4j2
public class UploadController {
    // 년월일 폴더 생성
    private String getFolder(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateFormat = format.format(date);

        return dateFormat.replace("-", File.separator);
    }

    private boolean isImgFile(File file){
        try {
            String contentType= Files.probeContentType(file.toPath());

            return contentType.startsWith("image");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @PostMapping(value= "/upload",  produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<AttachmentDTO>> uploadFile(MultipartFile[] uploadFile){
        List<AttachmentDTO> list = new ArrayList<>();

        String uploadFolder =  "/Users/jueon/Desktop/study/spring/upload/temp/";

        // 폴더 생성 (년월일로)
        File uploadPath = new File(uploadFolder, getFolder());  // parent, child
        if (!uploadPath.exists()) uploadPath.mkdirs();

        //파일 업로드
        for (MultipartFile file: uploadFile) {
            AttachmentDTO dto = new AttachmentDTO();

            String filename = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();

            dto.setFilename(filename);
            dto.setUuid(uuid);
            dto.setUploadPath(uploadPath.getPath());

            filename = uuid + "_" + filename;

            try {
                File saveFile = new File(uploadPath, filename);

                file.transferTo(saveFile);

                if(isImgFile(saveFile)){
                    dto.setImage(true);

                    FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + filename));
                    Thumbnailator.createThumbnail(file.getInputStream(), thumbnail, 100, 100);
                    thumbnail.close();
                }
                list.add(dto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/deleteFile")
    @ResponseBody
    public ResponseEntity<String> deleteFile(String filename, String type){
        log.info("deleteFile" + filename + type);

        File file;

        file = new File(filename);
        file.delete();
        if (type.equals("img")) {
            String largeFilename = file.getAbsolutePath().replace("s_", "");
            log.info("largeFilename" + largeFilename);

            file = new File(largeFilename);
            file.delete();
        }
        return new ResponseEntity<String>("deleted", HttpStatus.OK);

    }

    // 특정한 파일 이름을 받아서 이미지 데이터를 전송
    @GetMapping("/display")
    @ResponseBody
    public ResponseEntity<byte[]> getFile(String filename) {
        log.info("filename : " + filename);

        File file = new File(filename);
        log.info("file : "+file);
        ResponseEntity<byte[]> result = null;


        try {
            HttpHeaders header = new HttpHeaders();

            header.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
