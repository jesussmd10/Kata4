package software.ulpgc.io;


import software.ulpgc.model.Pokemon;

import java.io.*;

public class FilePokemonReader implements PokemonReader, AutoCloseable{
    private final BufferedReader reader;
    private final PokemonDeserializer deserializer;

    public FilePokemonReader(File file, PokemonDeserializer deserializer) throws IOException {
        this.deserializer = deserializer;
        this.reader = new BufferedReader(new FileReader(file));
        this.reader.readLine();
    }

    public Pokemon read() throws IOException {
        String line = reader.readLine();
        if (line == null) return null;
        return deserializer.deserialize(line);
    }



    public void close() throws Exception {
        this.reader.close();
    }
}
