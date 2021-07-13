package tools;

import java.util.HashMap;
import java.util.Map;

public class TransCoder {

    private HashMap<Character, String> encode = new HashMap<>();
    private HashMap<String , Character> decode= new HashMap<>();

    public TransCoder(String val) {

        String key = "CFfrkowl.aDzyS:eHjsGPZgMApWvRYVmtnK!BuU IQiEXTxbqhLdNJO,'c";
        char[] keyArray = new char[key.length()];
        String value = val;
        String[] valueArray = new String[val.length()];


        for (int i=0; i<key.length();i++) {
            keyArray[i]=key.charAt(i);
        }
        for (int j=0; j<value.length(); j=j+2) {
            valueArray[j]=value.substring(j);
        }
        for (int k=0; k<key.length();k++){
            this.encode.put(keyArray[k],valueArray[k]);
            this.decode.put(valueArray[k], keyArray[k]);
        }

    }


    public Map<Character, String> getEncode() {
        
        return encode;
    }


    public Map<String, Character> getDecode() {
        
        return decode;
    }

    public String encode(String msg){

        String msgCode = "";

        for (int i=0 ; i<msg.length(); i++) {
            for (char key: encode.keySet()) {
                if (msg.charAt(i) == key){
                    msgCode +=encode.get(i);
                }
            }
        }
    return msgCode;
    }

    public String decode(String msg){

    }

}
