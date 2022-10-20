package com.board.task;

import com.board.domain.BoardAttachVO;
import com.board.mapper.BoardAttachMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Component
public class FileCheckTask {

    @Setter(onMethod_ = @Autowired)
    private BoardAttachMapper attachMapper;

    private String getFolderPrevDay(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);

        String time = formatter.format(calendar.getTime());

        return time.replace("-", File.separator);
    }

    @Scheduled(cron = "0 0 2 * * *")        // 주기 제어
    public void checkFiles(){
        log.warn("FileCheckTask - checkFiles ----------------------");

        List<BoardAttachVO> fileList = attachMapper.getOldFiles();

        List<Path> fileListPaths = fileList.stream()
                .map(vo -> Paths.get(vo.getUploadPath(), vo.getUuid()+ "_"+vo.getFilename())).collect(Collectors.toList());

        fileList.stream().filter(vo -> vo.isFileType() == true)
                .map(vo -> Paths.get(vo.getUploadPath(), "s_"+vo.getUuid()+"_"+vo.getFilename())).forEach(p -> fileListPaths.add(p));

        log.warn("-------------------------------------");

        fileListPaths.forEach(path -> log.warn(path));

        File targetDir = Paths.get("/Users/jueon/Desktop/study/spring/upload/temp/", getFolderPrevDay()).toFile();
        File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);

        log.warn("-------------------------------------");

        for (File file : removeFiles) {
            log.warn(file.getAbsolutePath());
            file.delete();
        }
    }
}
