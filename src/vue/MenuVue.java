package vue;

import javax.swing.JPanel;
import javax.swing.JList;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Class creant la vue du menu.
 */
public class MenuVue extends JPanel {

    private String levels;
    private JList<String> list;

    /**
     * Cree une liste des niveaux qui sont dans le String passe en parametre
     * @param levels Niveaux present dans le fichier des Niveaux
     */
    public MenuVue(String levels) {
        this.levels = levels;
        Matcher m = Pattern.compile(";(.*)\n").matcher(levels);
        List<String> matches = new ArrayList<>();
        while (m.find()) {
            matches.add(m.group(1));
        }
        list = new JList<String>(matches.toArray(new String[0]));

        this.add(list);
    }

    /**
     * Retourne la liste des numeros et des noms de niveaux
     * @return la liste des numeros et des nom de niveaux
     */
    public JList<String> getList() {
        return list;
    }
}
