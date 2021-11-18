package uz.pdp.projecttwo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.projecttwo.entity.Hotel;
import uz.pdp.projecttwo.entity.Room;
import uz.pdp.projecttwo.payload.RoomDto;
import uz.pdp.projecttwo.repository.HotelRepository;
import uz.pdp.projecttwo.repository.RoomRepository;

import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HotelRepository hotelRepository;

    // for department
    @GetMapping()
    public Page<Room> getRoom(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return roomRepository.findAll(pageable);
    }

    @GetMapping("/hotelId/{id}")
    public Page<Room> getRoomByHotelId(@PathVariable int id, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return roomRepository.findAllByHotel_Id(id, pageable);
    }

    @PostMapping("/addRoom")
    public String addRoom(@RequestBody RoomDto roomDto) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent())
            return "hotel not found";
        roomRepository.save(new Room(roomDto.getNumber(), roomDto.getFloor(), roomDto.getSize(), optionalHotel.get()));
        return "added";
    }

    @PutMapping("/editRoom/{id}")
    public String editRoom(@PathVariable int id, @RequestBody RoomDto roomDto) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent())
            return "room not found";
        Optional<Hotel> optionalHotel1 = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel1.isPresent())
            return "hotel not found";
        Room room = optionalRoom.get();
        room.setNumber(room.getNumber());
        room.setFloor(room.getFloor());
        room.setSize(room.getSize());
        room.setHotel(optionalHotel1.get());
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        roomRepository.save(room);
        return "edited";
    }

    @DeleteMapping("/deleteRoom/{id}")
    public String deleteRoom(@PathVariable int id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            roomRepository.delete(optionalRoom.get());
            return "deleted";
        }
        return "room not found";
    }
}
