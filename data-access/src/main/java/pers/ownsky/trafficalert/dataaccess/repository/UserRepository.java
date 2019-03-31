package pers.ownsky.trafficalert.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pers.ownsky.trafficalert.dataaccess.datasource.TargetDataSource;
import pers.ownsky.trafficalert.dataaccess.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @TargetDataSource("read")
    public List<User> findAllByName(String name);
    @TargetDataSource("read")
    public User findByPhone(String phone);
    @TargetDataSource("read")
    public User findByPhoneAndPassword(String phone, String password);
    @TargetDataSource("main")
    public User save(User user);
}
