package com.uexcel.apartment.persistence;

import com.uexcel.apartment.model.ReservationDates;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationDateRepository extends JpaRepository<ReservationDates,String> {
    List<ReservationDates> findByDate(LocalDate date);
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "delete from reservation_dates where id = ?")
    @Override
   void deleteById(@Param("id") String id);

    boolean existsById(String id);

    @Query( value = "SELECT d FROM ReservationDates d WHERE  d.reservation.phone=:phoneNumber")
    List<ReservationDates> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query (nativeQuery = true,value = "SELECT * from reservation_dates where  apartment_code like 'A1%' and date >=curdate()")
    List<ReservationDates> findByA1Apartment();

    @Query (nativeQuery = true,value = "SELECT * from reservation_dates where  apartment_code like 'A2%' and date >=curdate()")
    List<ReservationDates> findByA2Apartment();


}
