package uz.pdp.projecttwo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private Integer floor;

    @Column(nullable = false)
    private Integer size;

    @ManyToOne
    private Hotel hotel;

    public Room(Integer number, Integer floor, Integer size, Hotel hotel) {
        this.number = number;
        this.floor = floor;
        this.size = size;
        this.hotel = hotel;
    }
}
