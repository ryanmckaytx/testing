package org.example.dao

import org.example.H2JdbiRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MyDAOTest {
    @Rule
    public H2JdbiRule jdbiRule = new H2JdbiRule();

    private MyDAO dao

    @Before
    void setUp() {
        dao = jdbiRule.attach(MyDAO.class)
        dao.createSomethingTable();
    }

    @Test
    void testFindById() {
        // Given
        dao.insert(2, "Dos");

        // When
        String name = dao.findNameById(2);

        // Then
        assert name == "Dos"
    }

    @Test
    void testFindAll() {
        // Given
        dao.insert(1, "Uno");
        dao.insert(2, "Dos");
        dao.insert(3, "Tres");

        // When
        List<String> names = dao.findAllWithLimit(2,1)

        // Then
        assert names == Arrays.asList("Tres")
    }
}
