package software.ulpgc;


import software.ulpgc.commands.ImportCommand;
import software.ulpgc.ui.MainFrame;
import software.ulpgc.commands.TogglePokemonChartCommand;
import software.ulpgc.io.FilePokemonLoader;
import software.ulpgc.io.TsvPokemonDeserializer;
import software.ulpgc.model.Pokemon;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        File file = new File("src/main/resources/pokemon.csv");
        List<Pokemon> pokemons = new FilePokemonLoader(file, new TsvPokemonDeserializer()).load();
        MainFrame mainFrame = new MainFrame();
        mainFrame.put("toggle", new TogglePokemonChartCommand(mainFrame.piechartDisplay(),pokemons));
        mainFrame.setVisible(true);
        ImportCommand command = new ImportCommand();
        command.execute();
    }
}