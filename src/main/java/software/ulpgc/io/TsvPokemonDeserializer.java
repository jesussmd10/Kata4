package software.ulpgc.io;


import software.ulpgc.model.Pokemon;

public class TsvPokemonDeserializer implements PokemonDeserializer {
    private static final int POKEMON = 1;
    private static final int TYPE = 2;
    private static final int GENERATION = 11;
    private static final int LEGENDARY = 12;

    @Override
    public Pokemon deserialize(String line) {
        return read(line.split(","));
    }

    private Pokemon read(String[] fields) {
        return new Pokemon(
                fields[POKEMON],
                fields[TYPE],
                toInteger(fields[GENERATION]),
                fields[LEGENDARY]
        );
    }

    private int toInteger(String field) {
        try {
            return Integer.parseInt(field);
        }
        catch (NumberFormatException e) {
            return -1;
        }
    }
}
