//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package software.ulpgc.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Piechart {
    private final String title;
    private final Map<String, Integer> counts;

    public Piechart(String title) {
        this.title = title;
        this.counts = new HashMap();
    }




    public String getTitle() {
        return this.title;
    }

    public Set<String> categories() {
        return this.counts.keySet();
    }

    public void add(String key, Integer value) {
         this.counts.put(key, value);
    }


    public int get(String key) {
        return (Integer)this.counts.getOrDefault(key, 0);
    }


}
