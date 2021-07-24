package com.murilonerdx.clickbus.service;


import static org.assertj.core.api.Assertions.assertThat;

import com.murilonerdx.clickbus.dto.PlaceDTO;
import com.murilonerdx.clickbus.entity.Place;
import com.murilonerdx.clickbus.entity.Place.PlaceBuilder;
import com.murilonerdx.clickbus.impl.PlaceServiceImpl;
import com.murilonerdx.clickbus.repository.PlaceRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class PlaceServiceTest {

  PlaceServiceImpl service;

  @MockBean
  PlaceRepository repository;

  @BeforeEach
  public void setUp() {
    this.service = new PlaceServiceImpl(repository);
  }

  @Test
  @DisplayName("Must save a place")
  public void savePlaceTest() {
    //Cenario
    Place place = createAPlace();
    Mockito.when(repository.save(place))
        .thenReturn(Place.builder().id(1L).createdAt(LocalDateTime.now())
            .city("São Paulo").name("Ibirapuera").slug("SP_IBIRAPUERA").state("São Paulo")
            .updatedAt(LocalDateTime.now()).build());


    //Execucao
    Place savePlace = repository.save(place);
    //Verificacao
    assertThat(savePlace.getId()).isNotNull();
    assertThat(savePlace.getCity()).isEqualTo("São Paulo");
    assertThat(savePlace.getName()).isEqualTo("Ibirapuera");
    assertThat(savePlace.getSlug()).isEqualTo("SP_IBIRAPUERA");
    assertThat(savePlace.getState()).isEqualTo("São Paulo");

  }

  private Place createAPlace() {
    Place placeBuilder = Place.builder().id(1L).createdAt(LocalDateTime.now())
        .city("São Paulo").name("Ibirapuera").slug("SP_IBIRAPUERA").state("São Paulo")
        .updatedAt(LocalDateTime.now()).build();
    return placeBuilder;
  }

  @Test
  @DisplayName("Must getById a place")
  public void getByIdTest() {
    Long id = 1L;
    Place place = createAPlace();
    place.setId(id);
    Mockito.when(repository.findById(id)).thenReturn(Optional.of(place));
    Optional<Place> foundPlace = Optional.ofNullable(service.getById(id));

    assertThat(foundPlace.isPresent()).isTrue();
    assertThat(foundPlace.get().getId()).isEqualTo(id);
    assertThat(foundPlace.get().getCity()).isEqualTo(place.getCity());
    assertThat(foundPlace.get().getName()).isEqualTo(place.getName());
    assertThat(foundPlace.get().getSlug()).isEqualTo(place.getSlug());
    assertThat(foundPlace.get().getState()).isEqualTo(place.getState());

  }

  @Test
  @DisplayName("Must delete a place")
  public void deletePlaceTest(){
    Place place = Place.builder().id(1L).build();
    org.junit.jupiter.api.Assertions.assertDoesNotThrow(()->service.delete(place));

    Mockito.verify(repository, Mockito.times(1)).delete(place);
  }

}
