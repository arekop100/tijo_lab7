package pl.edu.pwsztar.domain.dto;

import java.io.Serializable;

public class MovieCounterDto implements Serializable {
    private Long counter;

    private MovieCounterDto(Builder builder) {
        counter = builder.counter;
    }

    public Long getCounter() {
        return counter;
    }

    public static final class Builder{
        private Long counter;

        public Builder(){
        }

        public Builder counter(Long counter){
            this.counter = counter;
            return this;
        }

        public MovieCounterDto build(){
            return new MovieCounterDto(this);
        }
    }
}
