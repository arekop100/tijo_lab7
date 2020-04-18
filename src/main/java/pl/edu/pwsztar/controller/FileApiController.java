package pl.edu.pwsztar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.pwsztar.service.FileService;

import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping(value="/api")
public class FileApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieApiController.class);
    private final FileService fileService;

    @Autowired
    public FileApiController(FileService fileService){
        this.fileService = fileService;
    }
    @CrossOrigin
    @GetMapping(value = "/download-txt")
    public ResponseEntity<Resource> downloadTxt() {
        LOGGER.info("--- download txt file ---");
        try{
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "movies_"+(new Date().getTime())+".txt")
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(fileService.returnTxt());
        } catch (IOException ioexception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin()
    @GetMapping(value = "/download-csv")
    public  ResponseEntity<Resource> downloadCsv(){
        LOGGER.info("--- download csv file ---");
        try{
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=" + "movies_"+(new Date().getTime())+".csv")
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(fileService.returnCsv());
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
