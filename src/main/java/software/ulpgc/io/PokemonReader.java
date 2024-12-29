package software.ulpgc.io;

import software.ulpgc.model.Pokemon;


import java.io.IOException;

public interface PokemonReader extends AutoCloseable {
    Pokemon read() throws IOException;
}
