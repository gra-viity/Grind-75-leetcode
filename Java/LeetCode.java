//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class LeetCode {
    public LeetCode() {
    }

    static int findSecretCOde(int secrectCode, int firstKey, int secondyKey) {
        BigInteger S = BigInteger.valueOf((long)secrectCode);
        BigInteger N = BigInteger.valueOf((long)firstKey);
        BigInteger M = BigInteger.valueOf((long)secondyKey);
        BigInteger b1 = new BigInteger("10");
        BigInteger b2 = new BigInteger("1000000007");
        BigInteger ans = S.pow(N.intValue());
        ans = ans.mod(b1);
        ans = ans.pow(M.intValue());
        ans = ans.mod(b2);
        int intValue = ans.intValue();
        return intValue;
    }

    static int calculateDesktopProductIDs(char[] productID) {
        int ans = 0;

        for(int i = 0; i < productID.length; ++i) {
            if (productID[i] != 'a' || productID[i] != 'e' || productID[i] != 'i' || productID[i] != 'o' || productID[i] != 'u' || productID[i] != 'A' || productID[i] != 'E' || productID[i] != 'I' || productID[i] != 'O' || productID[i] != 'U') {
                ++ans;
            }
        }

        return ans;
    }

    static void formPattren(String inputWord) {
        int matrixLen = inputWord.length();
        char[][] mat = new char[matrixLen][matrixLen];

        for(int i = 0; i < matrixLen; ++i) {
            for(int j = 0; j < i; ++j) {
            }
        }

    }

    static String checkPassword(String password) {
        String no = "No-";
        ArrayList<Integer> ar = new ArrayList();
        String starCh = password.substring(0, 1);
        String endCh = password.substring(password.length() - 1, password.length());
        System.out.println(starCh + "--" + endCh);
        String st_la = password.substring(1, password.length() - 1);
        Pattern allSml = Pattern.compile("[a-z]");
        Pattern alldigits = Pattern.compile("[0-9]");
        Pattern caps = Pattern.compile("[A-Z]");
        Pattern spec = Pattern.compile("[@#%&?]");
        if (!allSml.matcher(starCh).find() || !allSml.matcher(endCh).find()) {
            ar.add(1);
        }

        if (!alldigits.matcher(st_la).find()) {
            ar.add(2);
        }

        if (!caps.matcher(st_la).find()) {
            ar.add(3);
        }

        if (!spec.matcher(st_la).find()) {
            ar.add(4);
        }

        if (password.length() < 10) {
            ar.add(5);
        }

        if (ar.size() <= 0) {
            return "YES";
        } else {
            String s = "";

            for(int i = 0; i < ar.size(); ++i) {
                s = s + ar.get(i) + ",";
            }

            return no + s.substring(0, s.length() - 1);
        }
    }

    public static String decodeSequence(String sequence) {
        Map<String, Character> nucleobaseMap = new HashMap();
        nucleobaseMap.put("001", 'C');
        nucleobaseMap.put("010", 'G');
        nucleobaseMap.put("011", 'A');
        nucleobaseMap.put("101", 'T');
        nucleobaseMap.put("110", 'U');
        String typeIdentifier = sequence.substring(0, 3);
        StringBuilder decodedSequence = new StringBuilder();
        if (typeIdentifier.equals("000")) {
            for(int i = 3; i < sequence.length(); i += 3) {
                String currentGroup = sequence.substring(i, Math.min(i + 3, sequence.length()));
                if (currentGroup.equals("110")) {
                    decodedSequence.append('T');
                } else {
                    decodedSequence.append(nucleobaseMap.get(currentGroup));
                }
            }
        } else if (typeIdentifier.equals("111")) {
            for(int i = 3; i < sequence.length(); i += 3) {
                String currentGroup = sequence.substring(i, Math.min(i + 3, sequence.length()));
                if (currentGroup.equals("101")) {
                    decodedSequence.append('U');
                } else {
                    decodedSequence.append(nucleobaseMap.get(currentGroup));
                }
            }
        } else {
            decodedSequence.append("Unknown type: ");
        }

        return decodedSequence.toString();
    }


    public static void main(String[] args) {
        /*
        I'm giving you control text and discussion now you have to answer is is possible to implement this in aws if possible show how if not type NA don't provide extra information.

         */

        String inputSequence = "000001001011101010010110011";
        String outputSequence = decodeSequence(inputSequence);
        System.out.println(inputSequence);
        System.out.println(outputSequence);
    }
}
