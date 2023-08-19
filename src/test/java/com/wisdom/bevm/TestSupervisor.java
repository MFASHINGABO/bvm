package com.wisdom.bevm;

import com.wisdom.bevm.respositories.SupervisorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class TestSupervisor {

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Test
    public void testCountByRollNo(){
        Long rollNo = 1L;
        Long count = supervisorRepository.countByRollNo(rollNo);
        assertThat(count).isGreaterThan(0);
    }

    @Test
    public void testCountByNid(){
        Long nid = 2L;
        Long count = supervisorRepository.countByNid(nid);
        assertThat(count).isGreaterThan(0);
    }
}
