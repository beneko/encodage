package exos.java;

import org.junit.Assert;
import tools.TransCoder;


public class Main {

    public static void main(String[] args) {
        String testGermain = "6Qe0IsEEH1utWRe7UKzGMiDTytOB3HS1dEfIB4imna3IRHXHRn5ZrvKFEcPjmPgKYGuytG+gDAl1m2DdHalJQg==";
        TransCoder transcodeTest = new TransCoder(testGermain);
        StringBuilder str =transcodeTest.decode("BYAPASBNBGAPASBGASBNASAFBHBGBNAHAJBNAZAFBLADBNAPASASAJAMAPADBNBJBJBJ");
        System.out.println(str);

    }
}

