package tests;

import org.junit.jupiter.api.Test;
import utulities.FileParser;
import vue.GUI;
import vue.SokobanVue;

public class TestTemps {

    public TestTemps() throws Exception {

    }

    @Test
    public void TestTemps() throws Exception {
        Long begin = System.currentTimeMillis();
        String filePath1 = "src/levels/lvl.xsb";
        String levels = FileParser.parse(filePath1);

        SokobanVue.initializeResource();
        new GUI(levels);
        Long end=System.currentTimeMillis();
        Long timeExecution=end-begin;
        System.out.println(timeExecution);
            }
        }



