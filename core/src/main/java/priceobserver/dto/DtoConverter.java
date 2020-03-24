package priceobserver.dto;

public interface DtoConverter<T, E> {

    T convertToDto(E entity);

    E convertToEntity(T dto);
}
