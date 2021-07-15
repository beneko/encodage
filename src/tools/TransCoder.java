package tools;

import org.apache.commons.lang3.StringUtils;
import org.germain.tool.ManaBox;

import java.util.Arrays;
import java.util.HashMap;

public class TransCoder {


    private HashMap <Character, String> encode = new HashMap<>();
    private HashMap <String , Character> decode= new HashMap<>();


    public TransCoder(String str) {

        String keys = ManaBox.decrypt(str);
        StringBuilder values = new StringBuilder();
        char chr1 = 'A';
        char chr2 = 'A';

        for (int i=0; i<keys.length();i++){

                values.append(chr1);
                values.append(chr2);
                chr2++;
                if(chr2 > 'Z'){
                    chr1++;
                    chr2 ='A';
                }
        }

        for (int i=0; i<keys.length();i++) {

            encode.put(keys.charAt(i),values.substring((i*2),(i*2)+2));
            decode.put(values.substring((i*2),(i*2)+2),keys.charAt(i));

        }
    }

    public HashMap<Character, String> getEncode() {
        return encode;
    }

    public HashMap<String, Character> getDecode() {
        return decode;
    }

    public StringBuilder encode(String str){
        
            StringBuilder crypt =  new StringBuilder();
            str = StringUtils.stripAccents(str);// replace characters with accent
                
            for(int i=0;i<str.length(); i++){

                crypt.append(encode.get(str.charAt(i)));

            }
        return crypt;
    }

    public StringBuilder decode(String str){

        String[] subStr = new String[str.length()/2];
        StringBuilder decrypt =  new StringBuilder();

        for(int i=0;i< subStr.length; i++){
            subStr[i] = str.substring(i*2, (i*2)+2);
        }

        for (String s : subStr) {
            decrypt.append(decode.get(s));
        }
        return decrypt;
    }

}
