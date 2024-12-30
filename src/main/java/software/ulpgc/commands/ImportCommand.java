package software.ulpgc.commands;

import software.ulpgc.io.*;
import software.ulpgc.model.Pokemon;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ImportCommand implements Command{
    public ImportCommand() {
    }

    @Override
    public void execute()  {
        try {
            File input = getInputFile();
            File output = getOutputFile();
            doExecute(input, output);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File getOutputFile() {
        return new File("pokemons.db");
    }

    private File getInputFile() {
        return new File("src/main/resources/pokemon.csv");

    }

        private void doExecute(File input, File output) throws Exception {
            try (PokemonReader reader = createPokemonReader(input);
                 PokemonWriter writer = createPokemonWriter(output)) {
            while (true) {
                Pokemon pokemon = reader.read();
                if (pokemon == null) break;
                writer.write(pokemon);
            }
        }
    }
    private static DatabasePokemonWriter createPokemonWriter(File file) throws SQLException {
        return new DatabasePokemonWriter(deleteIfExists(file));
    }

    private static File deleteIfExists(File file) {
        if (file.exists()) file.delete();
        return file;
    }

    private static FilePokemonReader createPokemonReader(File file) throws IOException {
        return new FilePokemonReader(file, new TsvPokemonDeserializer());
    }

}
