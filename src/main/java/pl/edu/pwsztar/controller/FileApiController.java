package pl.edu.pwsztar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.util.Date;

@Controller
@RequestMapping(value="/api")
public class FileApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieApiController.class);

    @CrossOrigin
    @GetMapping(value = "/download-txt")
    public ResponseEntity<Resource> downloadTxt() throws IOException {
        LOGGER.info("--- download txt file ---");

        // TODO: --- Kod wymagajacy refaktoryzacji ---
        // TODO: Zanim zaczniesz refaktorowac pomysl o zasadzie KISS

        File f=File.createTempFile("tmp", ".txt");
        FileOutputStream fos=new FileOutputStream(f);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i = 0; i < 10; i++) {
            bw.write("something");
            bw.newLine();
        }

        bw.close();

        fos.flush();
        fos.close();

        InputStream stream = new FileInputStream(f);
        InputStreamResource inputStreamResource = new InputStreamResource(stream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "test_"+(new Date().getTime())+".txt")
                .contentLength(f.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(inputStreamResource);

        // TODO: --- Kod wymagajacy refaktoryzacji ---
    }
} //start commit test
