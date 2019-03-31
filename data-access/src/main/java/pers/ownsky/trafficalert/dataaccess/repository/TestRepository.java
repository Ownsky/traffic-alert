package pers.ownsky.trafficalert.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pers.ownsky.trafficalert.dataaccess.datasource.TargetDataSource;
import pers.ownsky.trafficalert.dataaccess.model.Test;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    @TargetDataSource("read")
    List<Test> findAllByData(String data);
}
