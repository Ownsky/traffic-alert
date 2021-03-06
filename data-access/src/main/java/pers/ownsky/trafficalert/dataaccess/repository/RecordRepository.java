package pers.ownsky.trafficalert.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pers.ownsky.trafficalert.dataaccess.model.Record;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findByToCarIdAndPushed(Long cid, Boolean pushed);
}
