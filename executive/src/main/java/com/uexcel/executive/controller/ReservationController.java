package com.uexcel.executive.controller;

import com.uexcel.executive.dto.*;
import com.uexcel.executive.service.IReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "REST APIs For Executive Rooms  Reservation.",
        description = "The REST APIs For Regular Rooms  Reservation CRUD operations.")

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/executive", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {
    private final IReservationService reservationService;

    @Operation(summary = "The API to Search For Executive Room Free Dates in Days. ",
            description = "The API to Search For Executive Room Free Days Can Be Filtered by Desired number of days.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",description = "Ok",
                            content = @Content(schema=@Schema(implementation= FreeRoomsDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",description = "Bad Request",
                            content = @Content(schema=@Schema(implementation= ErrorResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",description = "Not Found",
                            content = @Content(schema=@Schema(implementation=ErrorResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",description = "Internal Server Error",
                            content = @Content(schema=@Schema(implementation= ErrorResponseDto.class))
                    )
            }

    )

    @GetMapping("/days")
    public ResponseEntity<List<FreeRoomsDto>> roomCalendar(
            @RequestParam(required = false ) Integer numberOfDays){
        List<FreeRoomsDto> freeRoomsDtoList =
                reservationService.getFreeRoomsByDays(numberOfDays);
        return ResponseEntity.ok(freeRoomsDtoList);
    }
    @Operation(summary = "The API to Search For Executive Room Free Dates in Month.",
            description = "The API to Search For Executive Room Free Days Can Be Filtered by Desired Month.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",description = "Ok",
                            content = @Content(schema=@Schema(implementation= AvailableRoomsDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",description = "Bad Request",
                            content = @Content(schema=@Schema(implementation= ErrorResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",description = "Not Found",
                            content = @Content(schema=@Schema(implementation=ErrorResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",description = "Internal Server Error",
                            content = @Content(schema=@Schema(implementation= ErrorResponseDto.class))
                    )
            }

    )

    @GetMapping("/month")
    public ResponseEntity<List<FreeRoomsDto>> roomCalendar(@RequestParam(required = false) String monthName){
        List<FreeRoomsDto> freeRoomsDtoList = reservationService.getFreeRoomsByMonth(monthName);
        return ResponseEntity.ok(freeRoomsDtoList);
    }
    @Operation(summary = "The API to Create Executive Room Reservation.",
            description = "The API to Create Executive Room Reservation.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",description = "Created",
                            content = @Content(schema=@Schema(implementation= ResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",description = "Bad Request",
                            content = @Content(schema=@Schema(implementation= ErrorResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "417",description = "Exception Failed",
                            content = @Content(schema=@Schema(implementation=ErrorResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",description = "Internal Server Error",
                            content = @Content(schema=@Schema(implementation= ErrorResponseDto.class))
                    )
            }

    )

    @PostMapping("/reservation")
    public ResponseEntity<ReservationResponseDto> saveReservation(@RequestBody @Valid ReservationDto reservationDto){
      ReservationResponseDto rRDto =  reservationService.saveReservation(reservationDto);
      return ResponseEntity.status(rRDto.getStatus()).body(rRDto);
    }

    @Operation(summary = "The API to Delete Executive Room Reservation.",
            description = "The API to Delete All Past Dates Executive Room Reservations.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",description = "Ok",
                            content = @Content(schema=@Schema(implementation= ResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",description = "Bad Request",
                            content = @Content(schema=@Schema(implementation= ErrorResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",description = "Not Found",
                            content = @Content(schema=@Schema(implementation=ErrorResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",description = "Internal Server Error",
                            content = @Content(schema=@Schema(implementation= ErrorResponseDto.class))
                    )
            }

    )

    @DeleteMapping("/reservation")
    private ResponseEntity<ResponseDto> deletePastReservations(){
        ResponseDto rs = reservationService.deletePastReservations();
        return  ResponseEntity.status(rs.getStatus()).body(rs);
    }

}
