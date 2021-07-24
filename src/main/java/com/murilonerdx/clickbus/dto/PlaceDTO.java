package com.murilonerdx.clickbus.dto;

import com.murilonerdx.clickbus.entity.Place;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PlaceDTO {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotEmpty(message = "Enter the name of the place")
  private String name;
  @NotEmpty(message = "Enter a slug")
  private String slug;
  @NotEmpty(message = "Enter a city")
  private String city;
  @NotEmpty(message = "Enter a state")
  private String state;

  public PlaceDTO(Place obj){
    this.name = obj.getName();
    this.slug = obj.getSlug();
    this.city = obj.getCity();
    this.state = obj.getState();
  }


  public Place toEntity(){
    Place place = new Place();
    place.setState(this.state);
    place.setSlug(this.slug);
    place.setCity(this.city);
    place.setName(this.name);
    return place;
  }
}
