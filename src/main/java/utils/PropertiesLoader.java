package utils;

import java.util.Base64;

public class PropertiesLoader {

    private static String userName="c2VyZ2U2Njg=";
    private static String accessKey="ekNjY0JicHE1R2RrSEVjZTlUUXg=";
    private static byte[] decodedBytes;

//    public static void encodeString() {
//        String decodedString = Base64.getEncoder().encodeToString(userName.getBytes());
//        System.out.println(decodedString);
//    }

    public static String decodeString(boolean ifUserName) {
        if (ifUserName){
            decodedBytes = Base64.getDecoder().decode(userName);
        } else {
            decodedBytes = Base64.getDecoder().decode(accessKey);
        }
        return new String(decodedBytes);
    }
}
