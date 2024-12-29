
package software.ulpgc.io;

import software.ulpgc.model.Pokemon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilePokemonLoader implements PokemonLoader{
    private final File file;
    private final PokemonDeserializer deserializer;

    public FilePokemonLoader(File file, PokemonDeserializer deserializer) {
        this.file = file;
        this.deserializer = deserializer;
    }

    @Override
    public List<Pokemon> load() throws IOException {
        List<Pokemon> pokemons = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                pokemons.add(deserializer.deserialize(line));
            }
            return pokemons;
        }
    }

}
