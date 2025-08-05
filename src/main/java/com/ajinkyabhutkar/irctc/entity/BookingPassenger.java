package com.ajinkyabhutkar.irctc.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name ="irctc_booking_passenger")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookingPassenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //many passengers can book train for one booking
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private String name;

    private Integer age;

    private String gender;

    @ManyToOne
    private  TrainSeats trainSeat;

    private String seatNumber;

}
