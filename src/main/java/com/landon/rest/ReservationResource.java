package com.landon.rest;

import com.landon.converter.RoomEntityToReservationResponseConverter;
import com.landon.entity.RoomEntity;
import com.landon.model.request.ReservationRequest;
import com.landon.model.response.ReservationResponse;
import com.landon.repository.PageableRoomRepository;
import com.landon.repository.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@RestController
@RequestMapping(ResourceConstants.ROOM_RESERVATION_V1)
public class ReservationResource {

    private final RoomRepository roomRepository;
    private final PageableRoomRepository pageableRoomRepository;
    private final RoomEntityToReservationResponseConverter roomConverter;

    public ReservationResource(RoomRepository roomRepository, PageableRoomRepository pageableRoomRepository,
                               RoomEntityToReservationResponseConverter roomConverter) {
        this.roomRepository = roomRepository;
        this.pageableRoomRepository = pageableRoomRepository;
        this.roomConverter = roomConverter;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoomEntity> getRoomById(@PathVariable Long id) {
        RoomEntity room = roomRepository.findById(id).get();

        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<ReservationResponse> getAvailableRooms(
            @RequestParam(value = "checkin")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate checkin,
            @RequestParam(value = "checkin")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate checkout,
            Pageable pageable) {

        Page<RoomEntity> roomEntityList = pageableRoomRepository.findAll(pageable);

        return roomEntityList.map(roomConverter::convert);
    }

    @RequestMapping(
            path = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationResponse> createReservation(
            @RequestBody
            ReservationRequest reservationRequest) {
        return new ResponseEntity<ReservationResponse>(new ReservationResponse(), HttpStatus.CREATED);
    }

    @RequestMapping(
            path = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationResponse> updateReservation(
            @RequestBody
            ReservationRequest reservationRequest) {
        return new ResponseEntity<ReservationResponse>(new ReservationResponse(), HttpStatus.OK);
    }

    @RequestMapping(
            path = "/{reservationId}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteReservation(
            @PathVariable
            long reservationId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
