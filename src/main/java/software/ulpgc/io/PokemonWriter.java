package software.ulpgc.io;

import software.ulpgc.model.Pokemon;

import java.io.IOException;

public interface PokemonWriter extends AutoCloseable {
    void write(Pokemon pokemon) throws IOException;
}
