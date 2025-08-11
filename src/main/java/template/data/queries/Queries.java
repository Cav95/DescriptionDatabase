package template.data.queries;

public final class Queries {

    public static final String GET_ALL_TABLE = """
                            select *
            from descrizioni
            order by Gruppo;
                        """;

    public static final String GET_ONE_DES = """
            SELECT *
            FROM descrizioni
            where Descrizione = ?
            AND DescrizioneEng = ?
            AND Gruppo = ?;
                        """;

    public static final String INSERT_ONE_DES = """
            INSERT INTO DESCRIZIONI (Descrizione, DescrizioneEng ,Gruppo ) VALUES
            (?, ?, ?);
                        """;

    public static final String DELETE_ONE_DES = """
                        delete FROM descrizioni
            where Descrizione = ?
            AND DescrizioneEng = ?
            AND Gruppo = ?;
                                    """;

    public static final String update_ONE_DES = """
                                   update descrizioni
            SET Descrizione = ?
            , DescrizioneEng = ?
            where Descrizione = ?
            AND DescrizioneEng = ?
            AND Gruppo = ?;
                                                """;

}
