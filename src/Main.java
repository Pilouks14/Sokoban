import utulities.FileParser;
import vue.GUI;
import vue.SokobanVue;

/**
 * @author KLAEYLE Pierre-Louis, LAFAY Gareth, MIALON Laurine, JAROSSAY Max
 */
public class Main {
    public static void main(String[] args) throws Exception {

        String filePath1 = "src/levels/lvl.xsb";
        String levels = FileParser.parse(filePath1);

        //LevelMaker est composé de méthode static

        SokobanVue.initializeResource();
        new GUI(levels);
    }
}
