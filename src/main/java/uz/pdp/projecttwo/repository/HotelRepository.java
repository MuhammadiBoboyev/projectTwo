package uz.pdp.projecttwo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.projecttwo.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
