package com.murilonerdx.clickbus.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.murilonerdx.clickbus.entity.Place;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PlaceRepositoryTest {

  @Autowired
  TestEntityManager entityManager;

  @Autowired
  PlaceRepository repository;

  @Test
  @DisplayName("Must get a place by id")
  public void findByIdTest(){
    //cenario
    Place place = createAPlace();
    entityManager.persist(place);

    //execucao
    Optional<Place> foundPlace = repository.findById(place.getId());

    //verificacao
    assertThat(foundPlace.isPresent()).isTrue();
  }

  private Place createAPlace() {
    Place placeBuilder = Place.builder().createdAt(LocalDateTime.now())
        .city("São Paulo").name("Ibirapuera").slug("SP_IBIRAPUERA").state("São Paulo")
        .updatedAt(LocalDateTime.now()).build();
    return placeBuilder;
  }

  @Test
  @DisplayName("Must save a book")
  public void savePlaceTest(){
    Place place = createAPlace();
    Place savePlace = repository.save(place);

    assertThat(savePlace.getId()).isNotNull();
  }

  @Test
  @DisplayName("Must delete a place")
  public void deletePlaceTest(){
    Place place = createAPlace();
    entityManager.persist(place);
    Place foundPlace = entityManager.find(Place.class, place.getId());

    repository.delete(foundPlace);
    Place deletedPlace = entityManager.find(Place.class, place.getId());

    assertThat(deletedPlace).isNull();
  }
}
