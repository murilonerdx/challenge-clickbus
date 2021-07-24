package com.murilonerdx.clickbus.impl;

import com.murilonerdx.clickbus.dto.PlaceDTO;
import com.murilonerdx.clickbus.entity.Place;
import com.murilonerdx.clickbus.exception.PlaceNotFound;
import com.murilonerdx.clickbus.repository.PlaceRepository;
import com.murilonerdx.clickbus.service.PlaceService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PlaceServiceImpl implements PlaceService {

  private final PlaceRepository repository;

  @Override
  public Place create(PlaceDTO placeDTO) {
    Place place = toModel(placeDTO);
    place.setCreatedAt(LocalDateTime.now());
    place.setUpdatedAt(place.getCreatedAt());
    return repository.save(place);
  }


  /* Mapper
   * Criando metodo para criar um novo modelo
   * Transformando DTO em entity */
  private Place toModel(PlaceDTO placeDTO) {
    return new ModelMapper().map(placeDTO, Place.class);
  }

  /* Verificação update
   * Verificando se a entidade existe
   * Transformando DTO em entity */
  @Override
  public Place update(Long id, PlaceDTO place) throws PlaceNotFound {
    Place entity = getById(id);
    if(entity == null){
      throw new PlaceNotFound("Place not found");
    }
    entity.setCity(place.getCity());
    entity.setSlug(place.getSlug());
    entity.setUpdatedAt(LocalDateTime.now());
    entity.setState(place.getState());
    return repository.save(entity);
  }

  @Override
  public void delete(Place place) throws PlaceNotFound{
    if(place == null | Objects.requireNonNull(place).getId() == null){
      throw new PlaceNotFound("Place not found");
    }
    repository.delete(place);
  }

  @Override
  public Place getById(Long id) {
    return repository.findById(id).orElseThrow(()-> new PlaceNotFound("Place not found"));
  }

  @Override
  public List<PlaceDTO> getAll() {
    return repository.findAll().stream()
        .map(x->new ModelMapper().map(x, PlaceDTO.class))
        .collect(Collectors.toList());
  }

  @Override
  public List<Place> getByName(String name) {
    try{
      return repository.getByName(name);
    }catch(IllegalArgumentException e){
      return null;
    }
  }


}
