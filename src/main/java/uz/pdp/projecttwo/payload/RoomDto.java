package uz.pdp.projecttwo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.projecttwo.entity.Hotel;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDto {
    private Integer number;
    private Integer floor;
    private Integer size;
    private int hotelId;
}
