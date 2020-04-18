package pl.edu.pwsztar.service;

import org.springframework.core.io.InputStreamResource;

import java.io.IOException;

public interface FileService {
    InputStreamResource returnTxt() throws IOException;
    InputStreamResource returnCsv() throws IOException;
}
