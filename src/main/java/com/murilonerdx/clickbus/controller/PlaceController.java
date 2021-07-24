package com.murilonerdx.clickbus.controller;

import com.murilonerdx.clickbus.dto.PlaceDTO;
import com.murilonerdx.clickbus.entity.Place;
import com.murilonerdx.clickbus.exception.ApiErrors;
import com.murilonerdx.clickbus.exception.PlaceNotFound;
import com.murilonerdx.clickbus.impl.PlaceServiceImpl;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clickbus")
/* Todos construtores @Autowired seram complementados
* */
@AllArgsConstructor(onConstructor = @__(@Autowired))

public class PlaceController {

  private final PlaceServiceImpl service;


  @GetMapping
  public List<PlaceDTO> getAllPlaces() {
    return service.getAll();
  }


  @GetMapping("/place")
  public List<Place> getPlaceByName(@RequestParam("name") String placeName) throws PlaceNotFound {
    List<Place> place = service.getByName(placeName);
    if (place == null) {
      throw new IllegalArgumentException("Place not exist");
    }
    return place;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Place createPlace(@RequestBody @Valid PlaceDTO place) {
    return service.create(place);
  }

  @PutMapping(value="/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Place updatePlace(@PathVariable Long id, @RequestBody @Valid PlaceDTO place) throws PlaceNotFound {
    return service.update(id, place);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePlace(@PathVariable Long id) throws PlaceNotFound {
    Place place = service.getById(id);
    service.delete(place);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiErrors handleValidationExceptions(MethodArgumentNotValidException e) {
    BindingResult bindingResult = e.getBindingResult();
    return new ApiErrors(bindingResult);
  }

  @ExceptionHandler(PlaceNotFound.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiErrors handlePlaceNotFound(PlaceNotFound e) {
    return new ApiErrors(e);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiErrors handleCannotBeNull(IllegalArgumentException e) {
    return new ApiErrors(e);
  }


}