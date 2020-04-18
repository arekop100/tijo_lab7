package pl.edu.pwsztar.service.serviceImpl;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.domain.dto.FileDto;
import pl.edu.pwsztar.domain.files.CsvGenerator;
import pl.edu.pwsztar.domain.files.TxtGenerator;
import pl.edu.pwsztar.domain.repository.MovieRepository;
import pl.edu.pwsztar.service.FileService;

import java.io.File;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {
    private final TxtGenerator txtGenerator;
    private final CsvGenerator csvGenerator;
    private final MovieRepository movieRepository;

    public FileServiceImpl(TxtGenerator txtGenerator
            , CsvGenerator csvGenerator
            , MovieRepository movieRepository){
        this.txtGenerator = txtGenerator;
        this.csvGenerator = csvGenerator;
        this.movieRepository = movieRepository;
    }

    @Override
    public InputStreamResource returnTxt() throws IOException {
        return txtGenerator.toTxt(new FileDto.Builder()
                .file(File.createTempFile("movies",".txt"))
                .movies(movieRepository.findAll(Sort.by(Sort.Direction.DESC,"year")))
                .build());
    }

    @Override
    public InputStreamResource returnCsv() throws IOException {
        return csvGenerator.toCsv(new FileDto.Builder()
                .file(File.createTempFile("movies",".csv"))
                .movies(movieRepository.findAll(Sort.by(Sort.Direction.DESC,"year")))
                .build());
    }
}
