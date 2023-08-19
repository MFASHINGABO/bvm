package com.wisdom.bevm;

import com.wisdom.bevm.models.Citizen;
import com.wisdom.bevm.respositories.CitizenRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestCitizen {
    @Autowired
    private CitizenRepository citizenRepository;

    @Test
    public void countByNid(){
        Long nid = 1L;
        Long count = citizenRepository.countByNid(nid);
        assertThat(count).isGreaterThan(0);
    }

    @Test
    public void testFindByFingerPrintId(){
        int f_id = 12;
        Optional<Citizen> citizen = citizenRepository.findByFingerPrintId(f_id);
        assertThat(citizen.get()).isNotNull();
    }

    @Test
    public void findByNid(){
        long nid = 1L;
        Optional<Citizen> citizen = citizenRepository.findById(nid);
        if (citizen.isPresent()){
            System.out.println(citizen.get().getNid());
        }else {
            System.out.println("NOPE");
        }
        assertThat(citizen).isNotNull();
    }
}
