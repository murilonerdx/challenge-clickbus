package com.murilonerdx.clickbus.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
@Builder
@Entity
public class Place {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String slug;
  private String city;
  private String state;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Place(Place place){
    this.name = place.getName();
    this.slug = place.getSlug();
    this.city = place.getCity();
    this.state = place.getState();
    this.createdAt = place.getCreatedAt();
    this.updatedAt = place.getUpdatedAt();
  }

}