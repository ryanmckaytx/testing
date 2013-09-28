package org.example;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rmckay
 * Date: 8/24/13
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MyDAO {
    @SqlUpdate("create table something (id int primary key, name varchar(100))")
    void createSomethingTable();

    @SqlUpdate("insert into something (id, name) values (:id, :name)")
    void insert(@Bind("id") int id, @Bind("name") String name);

    @SqlQuery("select name from something where id = :id")
    String findNameById(@Bind("id") int id);

    @SqlQuery("select name from something")
    List<String> findAll();

    @SqlQuery("select name from something ORDER BY id LIMIT :offset,:limit")
    List<String> findAllWithLimit(@Bind("offset") int offset, @Bind("limit") int limit);

    /**
     * close with no args is used to close the connection
     */
    void close();
}
