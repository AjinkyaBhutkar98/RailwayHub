package com.ajinkyabhutkar.irctc.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "irctc_train_booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "train_schedule_id")
    private TrainSchedule trainSchedule;

    @ManyToOne
    @JoinColumn(name = "source_schedule_id")
    private Station sourceStation;

    @ManyToOne
    @JoinColumn(name = "destination_schedule_id")
    private Station destinationStation;

    private String pnr;
    
    private LocalDate journeyDate;

    private BigDecimal totalFare;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    private LocalDateTime bookingTime;

    //one passenger can have multiple bookings
    @OneToMany(mappedBy = "booking")
    private List<BookingPassenger> passengers;

    //cascade all means when we saved/delete payment and booking same gets changed in db
    @OneToOne(mappedBy = "booking",cascade = CascadeType.ALL)
    private Payment payment;

}
