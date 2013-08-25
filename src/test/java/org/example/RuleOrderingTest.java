package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.experimental.results.PrintableResult.testResult;
import static org.junit.experimental.results.ResultMatchers.isSuccessful;

public class RuleOrderingTest {
    private static String callSequence;

    public static class UsesExternalResource {
        @Rule
        public ExternalResource resource1 = new ExternalResource() {
            @Override
            protected void before() throws Throwable {
                callSequence += "before1 ";
            }

            @Override
            protected void after() {
                callSequence += "after1 ";
            }
        };
        @Rule
        public ExternalResource resource2 = new ExternalResource() {
            @Override
            protected void before() throws Throwable {
                callSequence += "before2 ";
            }

            @Override
            protected void after() {
                callSequence += "after2 ";
            }
        };

        @Before
        public void setup(){
            callSequence += "@before ";
        }

        @After
        public void tearDown(){
            callSequence += "@after ";
        }

        @Test
        public void testFoo() {
            callSequence += "test ";
        }
    }

    @Test
    public void externalResourceGeneratesCorrectSequence() {
        callSequence = "";
        assertThat(testResult(UsesExternalResource.class), isSuccessful());
        assertEquals("before2 before1 @before test @after after1 after2 ", callSequence);
    }
}
