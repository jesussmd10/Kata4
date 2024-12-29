

package software.ulpgc.commands;


import software.ulpgc.model.Piechart;
import software.ulpgc.ui.PiechartDisplay;
import software.ulpgc.model.PokemonPiechart;
import software.ulpgc.model.Pokemon;

import java.util.List;

public class TogglePokemonChartCommand implements Command {

    private final PiechartDisplay piechartDisplay;
    private int i = 0;
    private final List<Pokemon> pokemonList;

    public TogglePokemonChartCommand(PiechartDisplay piechartDisplay, List<Pokemon> pokemonList) {
        this.piechartDisplay = piechartDisplay;
        this.pokemonList = pokemonList;
    }

    public void execute() {
        Piechart nextPiechart = this.getBarchart(++this.i);
        this.piechartDisplay.display(nextPiechart);
    }

    private Piechart getBarchart(int index) {
        return index % 2 == 0 ? generationBarchart() : LengendaryBarchart();
    }

    private Piechart generationBarchart() {
        return PokemonPiechart.generationBarchart(pokemonList);
    }

    private Piechart LengendaryBarchart() {
        return PokemonPiechart.typeBarchart(pokemonList);
    }
}
