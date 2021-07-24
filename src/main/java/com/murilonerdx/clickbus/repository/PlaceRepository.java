package com.murilonerdx.clickbus.repository;

import com.murilonerdx.clickbus.entity.Place;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
  @Query("select p from Place p where lower(p.name) like lower(concat('%', :name,'%'))")
  List<Place> getByName(@Param("name") String name);
}
