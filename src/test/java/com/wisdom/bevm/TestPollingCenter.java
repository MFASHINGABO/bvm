package com.wisdom.bevm;

import com.wisdom.bevm.respositories.PollingCenterRepository;
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
public class TestPollingCenter {

    @Autowired
    private PollingCenterRepository pollingCenterRepository;

    @Test
    public void testCountByPollingCenterId(){
        Long pollingCenterId = 1L;
        Long count = pollingCenterRepository.countByPollingCenterId(pollingCenterId);
        assertThat(count).isGreaterThan(0);
    }

    @Test
    public void testCountBySupervisorRollNo(){
        Long supervisorRollNo = 2L;
        Long count = pollingCenterRepository.countBySupervisorRollNo(supervisorRollNo);
        System.out.println(count);
        assertThat(count).isGreaterThan(0);
    }


}
