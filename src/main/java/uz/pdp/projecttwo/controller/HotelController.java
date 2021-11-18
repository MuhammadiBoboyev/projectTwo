package uz.pdp.projecttwo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.projecttwo.entity.Hotel;
import uz.pdp.projecttwo.repository.HotelRepository;

import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;

    // for department
    @GetMapping()
    public Page<Hotel> getHotel(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return hotelRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable int id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        return optionalHotel.orElse(null);
    }

    @PostMapping()
    public String addHotel(@RequestBody Hotel hotel) {
        hotelRepository.save(new Hotel(hotel.getName()));
        return "added";
    }

    @PutMapping("/{id}")
    public String editHotel(@PathVariable int id, @RequestBody Hotel hotel) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()) {
            optionalHotel.get().setName(hotel.getName());
            hotelRepository.save(optionalHotel.get());
            return "edited";
        }
        return "hotel not found";
    }

    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable int id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()) {
            hotelRepository.delete(optionalHotel.get());
            return "deleted";
        }
        return "hotel not found";
    }
}
