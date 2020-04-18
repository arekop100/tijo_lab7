package pl.edu.pwsztar.domain.files;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dto.FileDto;

import java.io.*;

@Component
public class CsvGeneratorImpl implements CsvGenerator {
    @Override
    public InputStreamResource toCsv(FileDto fileDto) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDto.getFile())));
        bufferedWriter.write("rok,tytul");
        bufferedWriter.newLine();
        fileDto.getMovies().forEach(movie -> {
            try {
                bufferedWriter.write(movie.getYear() + "," + movie.getTitle().replaceAll(",", " "));
                bufferedWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        bufferedWriter.close();

        return new InputStreamResource(new FileInputStream(fileDto.getFile()));
    }
}
