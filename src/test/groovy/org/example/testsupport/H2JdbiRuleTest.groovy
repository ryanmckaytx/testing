package org.example.testsupport

import org.example.MyDAO
import org.example.testsupport.H2JdbiRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class H2JdbiRuleTest {
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
    void testInteractionWithPreviousTest() {
        // Given
        // When
        List<String> names = dao.findAll()

        // Then
        assert names == []

        dao.insert(2, "Dos");
    }

    @Test
    void testInteractionWithPreviousTest2() {
        // Given
        // When
        List<String> names = dao.findAll()

        // Then
        assert names == []

        dao.insert(2, "Dos");
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
        assert names == ["Tres"]
    }
}
