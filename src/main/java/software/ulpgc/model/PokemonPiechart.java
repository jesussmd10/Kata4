package software.ulpgc.model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokemonPiechart {

    public static Piechart generationBarchart(List<Pokemon> pokemonList) {
        Piechart piechart = new Piechart("Distribución de Pokémon por Generación");
        Map<Integer, Integer> generationCounts = new HashMap<>();

        for (Pokemon pokemon : pokemonList) {
            int generation = pokemon.generation();
            generationCounts.put(generation, generationCounts.getOrDefault(generation, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : generationCounts.entrySet()) {
            piechart.add("Generación " + entry.getKey(), entry.getValue());
        }
        return piechart;
    }

    public static Piechart typeBarchart(List<Pokemon> pokemonList) {
        Piechart piechart = new Piechart("Distribución de Pokémon por Tipo");
        Map<String, Integer> typeCounts = new HashMap<>();

        for (Pokemon pokemon : pokemonList) {
            String type = pokemon.type();
            typeCounts.put(type, typeCounts.getOrDefault(type, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : typeCounts.entrySet()) {
            piechart.add(entry.getKey(), entry.getValue());
        }
        return piechart;
}
}

