package software.ulpgc.io;


import software.ulpgc.model.Pokemon;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class DatabasePokemonWriter implements PokemonWriter, AutoCloseable{
    private final Connection connection;
    private final PreparedStatement insertStatement;
    private final static String CreateTableStatement = """
            CREATE TABLE IF NOT EXISTS pokemons (
                pokemon TEXT PRIMARY KEY,
                type TEXT NOT NULL,
                generation INTEGER,
                is_legendary TEXT NOT NULL
            )
            """;
    private final static String InsertRecordStatement = """
            INSERT INTO pokemons (pokemon,type,generation,is_legendary)
            VALUES (?,?,?,?)
            """;

    public DatabasePokemonWriter(File file) throws SQLException {
        this(connectionFor(file));
    }

    public DatabasePokemonWriter(String connection) throws SQLException {
        this.connection = DriverManager.getConnection(connection);
        this.connection.setAutoCommit(false);
        this.insertStatement = initDatabase(this.connection);
    }

    private PreparedStatement initDatabase(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CreateTableStatement);
        }
        return connection.prepareStatement(InsertRecordStatement);
    }
    private static String connectionFor(File file) {
        return "jdbc:sqlite:" + file.getAbsolutePath();
    }

    @Override
    public void write(Pokemon pokemon) throws IOException {
        try {
            updateInsertStatementWith(pokemon);
            insertStatement.execute();
        } catch (SQLException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void updateInsertStatementWith(Pokemon pokemon) throws SQLException {
        for (Parameter parameter : toParameters(pokemon))
            updateInsertStatementWith(parameter);
    }

    private void updateInsertStatementWith(Parameter parameter) throws SQLException {
        if (isNull(parameter.value))
            insertStatement.setNull(parameter.id, parameter.type);
        else
            insertStatement.setObject(parameter.id, parameter.value);
    }

    private boolean isNull(Object value) {
        return value instanceof Integer && (Integer) value == -1;
    }

    private List<Parameter> toParameters(Pokemon pokemon) {
        return List.of(
                new Parameter(1, pokemon.pokemon(), Types.LONGVARCHAR),
                new Parameter(2, pokemon.type(), Types.LONGVARCHAR),
                new Parameter(3, pokemon.generation(), Types.INTEGER),
                new Parameter(4, pokemon.legendary(), Types.LONGVARCHAR)
        );
    }

    private record Parameter(int id, Object value, int type) {
    }

    @Override
    public void close() throws Exception {
        this.connection.commit();
        this.insertStatement.close();
        this.connection.close();
    }
}
