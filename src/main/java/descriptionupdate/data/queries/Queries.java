package descriptionupdate.data.queries;

/**
 * A utility class that contains SQL query strings used for database operations.
 */
public final class Queries {

        /**
         * SQL query to retrieve all records from the DESCRIZIONEGRUPPI table.
         */
        public static final String GET_ALL_TABLE = """
                        select *
                        from DESCRIZIONEGRUPPI
                        where Descrizione like ?
                        and INGLESE like ?
                        and Gruppo like ?
                        order by 4,2,3;
                                    """;
        /**
         * SQL query to retrieve a single record from the DESCRIZIONEGRUPPI table.
         */
        public static final String GET_ONE_DES = """
                        SELECT *
                        FROM DESCRIZIONEGRUPPI
                        where Descrizione = ?
                        AND INGLESE = ?
                        AND Gruppo = ?;
                                    """;
        /**
         * SQL query to insert a new record into the DESCRIZIONEGRUPPI table.
         */
        public static final String INSERT_ONE_DES = """
                        INSERT INTO DESCRIZIONEGRUPPI (Descrizione, INGLESE ,Gruppo ) VALUES
                        (?, ?, ?);
                                    """;
        /**
         * SQL query to delete a record from the DESCRIZIONEGRUPPI table.
         */
        public static final String DELETE_ONE_DES = """
                                    delete FROM DESCRIZIONEGRUPPI
                        where Descrizione = ?
                        AND INGLESE = ?
                        AND Gruppo = ?;
                                                """;
        /**
         * SQL query to update a record in the DESCRIZIONEGRUPPI table.
         */
        public static final String UPDATE_ONE_DES = """
                        update DESCRIZIONEGRUPPI
                         SET Descrizione = ?
                         , INGLESE = ?
                         where Descrizione = ?
                         AND INGLESE = ?
                         AND Gruppo = ?;
                                     """;
        /**
         * SQL query to retrieve all distinct group types from the DESCRIZIONEGRUPPI
         * table.
         */
        public static final String ALL_GROUP_TYPE_STRING = """
                        SELECT DISTINCT Gruppo
                         FROM DESCRIZIONEGRUPPI
                         WHERE Gruppo IS NOT NULL
                         ORDER BY Gruppo;
                                     """;

}
