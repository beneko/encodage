package test.java;

import org.apache.commons.lang3.StringUtils;
import org.germain.tool.ManaBox;
import org.junit.Assert;
import org.junit.Test;
import tools.TransCoder;

import java.util.HashMap;
import java.util.Map;

public class ReadKeyTest {

    @Test
    public void readKeyTest() {
        // La clé cryptée donnée plus haut
        String keyCrypted = "6Qe0IsEEH1utWRe7UKzGMiDTytOB3HS1dEfIB4imna3IRHXHRn5ZrvKFEcPjmPgKYGuytG+gDAl1m2DdHalJQg==";
        // La clé décryptée que nous devrions obtenir
        String keyDecrypted = "CFfrkowl.aDzyS:eHjsGPZgMApWvRYVmtnK!BuU IQiEXTxbqhLdNJO,'c";
        // le test d'égalité entre la clé attendue et la sortie de la méthode de la librairie. Si le décryptage ne fonctionne pas nous aurons le message définit ici
        Assert.assertEquals("La librairie de décryptage est mal installée",keyDecrypted, ManaBox.decrypt(keyCrypted));

    }

    @Test
    public  void stripAccentsTest(){
        // string avec accent
        String str = "C'est une chaîne de caractère avec des accent.";
        Assert.assertEquals("C'est une chaine de caractere avec des accent.", StringUtils.stripAccents(str));

    }

    @Test
    public void createMapTest() {
       String testGermain = "6Qe0IsEEH1utWRe7UKzGMiDTytOB3HS1dEfIB4imna3IRHXHRn5ZrvKFEcPjmPgKYGuytG+gDAl1m2DdHalJQg==";
        TransCoder transcodeTest = new TransCoder(testGermain);
        Assert.assertNotNull(transcodeTest.getEncode());
        System.out.println(transcodeTest.getEncode().toString());
        Assert.assertNotNull(transcodeTest.getDecode());
        System.out.println(transcodeTest.getDecode().toString());
    }

}
