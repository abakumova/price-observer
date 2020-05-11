package priceobserver.avic;

import java.time.LocalDate;
import java.time.Year;

public final class AvicPriceDtoBuilder {
    private String name;
    private String model;
    private Year year;
    private float price;
    private LocalDate date;

    private AvicPriceDtoBuilder() {
    }

    public static AvicPriceDtoBuilder anAvicPriceDto() {
        return new AvicPriceDtoBuilder();
    }

    public AvicPriceDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public AvicPriceDtoBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    public AvicPriceDtoBuilder withYear(Year year) {
        this.year = year;
        return this;
    }

    public AvicPriceDtoBuilder withPrice(float price) {
        this.price = price;
        return this;
    }

    public AvicPriceDtoBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public AvicPriceDto build() {
        AvicPriceDto avicPriceDto = new AvicPriceDto();
        avicPriceDto.setName(name);
        avicPriceDto.setModel(model);
        avicPriceDto.setYear(year);
        avicPriceDto.setPrice(price);
        avicPriceDto.setDate(date);
        return avicPriceDto;
    }
}
