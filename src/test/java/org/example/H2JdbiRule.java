package org.example;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.rules.ExternalResource;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

public class H2JdbiRule extends ExternalResource {

    private Handle handle = null;

    @Override
    protected void before() throws Throwable {
        // using in-memory H2 database via a pooled DataSource
        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL("jdbc:h2:mem:test2:MODE=MySQL");

        DBI dbi = new DBI(jdbcDataSource);
        handle = dbi.open();
        System.out.println("Created handle");
    }

    @Override
    protected void after() {
        if(null != handle) {
            System.out.println("Closing handle");
            handle.close();
            System.out.println("Closed handle");
        } else {
            System.out.println("Handle was null");
        }
    }

    public <SqlObjectType> SqlObjectType attach(Class<SqlObjectType> sqlObjectType) {
        System.out.println("Attaching to handle");
        return handle.attach(sqlObjectType);
    }
}
