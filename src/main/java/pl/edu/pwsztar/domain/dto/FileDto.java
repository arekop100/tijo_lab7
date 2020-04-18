package pl.edu.pwsztar.domain.dto;

import pl.edu.pwsztar.domain.entity.Movie;

import java.io.File;
import java.util.Collection;

public class FileDto {
    private File file;
    private Collection<Movie> movies;

    private FileDto(Builder builder){
        file = builder.file;
        movies = builder.movies;
    }

    public File getFile(){
        return this.file;
    }

    public Collection<Movie> getMovies() {
        return movies;
    }

    public static final class Builder{
        File file;
        private Collection<Movie> movies;

        public Builder(){
        }

        public Builder file(File file){
            this.file = file;
            return this;
        }

        public Builder movies(Collection<Movie> movies){
            this.movies = movies;
            return this;
        }

        public FileDto build(){
            return new FileDto(this);
        }
    }
}
