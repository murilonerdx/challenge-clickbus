package com.murilonerdx.clickbus.service;

import com.murilonerdx.clickbus.dto.PlaceDTO;
import com.murilonerdx.clickbus.entity.Place;
import java.util.List;

public interface PlaceService {
  Place create(PlaceDTO place);
  Place update(Long id, PlaceDTO place);
  void delete(Place place);
  Place getById(Long id);
  List<PlaceDTO> getAll();
  Place getByName(String name);
}

