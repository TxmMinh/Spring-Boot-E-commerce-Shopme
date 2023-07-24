package com.shopme.admin.setting.state;

import com.shopme.common.entity.Country;
import com.shopme.common.entity.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class StateRepositoryTests {
    @Autowired
    private StateRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateStatesInIndia() {
        Integer countryId = 1;

        Country country = entityManager.find(Country.class, countryId);

        State state = repo.save(new State("Mumbai", country));

        assertThat(state).isNotNull();
        assertThat(state.getId()).isGreaterThan(0);

    }

    @Test
    public void testCreateManyStates() {
        Integer countryId1 = 1;
        Country country1 = entityManager.find(Country.class, countryId1);

        Integer countryId2 = 2;
        Country country2 = entityManager.find(Country.class, countryId2);

        List<State> listStates = Arrays.asList(
                new State("Karnataka", country1),
                new State("Punjab", country1),
                new State("Yen", country1),
                new State("West Bengal", country1),
                new State("California", country2),
                new State("Texas", country2),
                new State("New York", country2)
        );

        repo.saveAll(listStates);

        Iterable<State> iterable = repo.findAll();

        assertThat(iterable).size().isEqualTo(7);
    }

    @Test
    public void testListStatesByCountry() {
        Integer countryId = 2;

        Country country = entityManager.find(Country.class, countryId);
        List<State> listStates = repo.findByCountryOrderByNameAsc(country);

        listStates.forEach(System.out::println);

        assertThat(listStates.size()).isGreaterThan(0);
    }

    @Test
    public void testUpdateState() {
        Integer stateId = 3;
        String stateName = "Tamil Nadu";
        State state = repo.findById(stateId).get();

        state.setName(stateName);
        State updateState = repo.save(state);

        assertThat(updateState.getName()).isEqualTo(stateName);
    }

    @Test
    public void testGetState() {
        Integer stateId = 1;
        Optional<State> findById = repo.findById(stateId);

        assertThat(findById.isPresent());
    }

    @Test
    public void testDeleteState() {
        Integer stateId = 8;
        repo.deleteById(stateId);

        Optional<State> findById = repo.findById(stateId);
        assertThat(findById.isEmpty());
    }
}
