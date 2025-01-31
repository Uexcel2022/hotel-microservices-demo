package com.uexcel.executive.persistence;

import com.uexcel.executive.model.ExecutiveRoom;
import com.uexcel.executive.model.ReservationDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExecutiveRoomRepository extends JpaRepository<ExecutiveRoom, Long> {
    @Query(value = "SELECT  d FROM  ReservationDates d WHERE d.executiveRoom.roomNumber=:roomNumber ")
    List<ReservationDates> findByRoomNumberJpql(@Param("roomNumber") String roomNumber);
    boolean existsByRoomNumber ( String roomNumber);
    ExecutiveRoom findByRoomNumber (String roomNumber);
}
