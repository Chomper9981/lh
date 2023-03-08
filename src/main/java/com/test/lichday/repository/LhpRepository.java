package com.test.lichday.repository;
import com.test.lichday.model.Lhp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LhpRepository extends JpaRepository<Lhp, Long> {
    List<Lhp> findByToa(String toa);
    List<Lhp> findByPhong(String phong);
    List<Lhp> findByThu(int thu);
    List<Lhp> findByKip(int kip);
}
