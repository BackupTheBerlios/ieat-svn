package cgh.util;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;

public class Utilities
{
    public static void exitIfFalse(boolean x, String msg)
    {
        if (!x)
        {
            System.err.println(msg);
            System.exit(-1);
        }
    }
    
    public static String[] splitString(String in, String delimiter)
    {
        if (isEmpty(in))
            return new String[0];
        return in.split(delimiter);
    }
    
    public static boolean isEmpty(String s)
    {
        if (s == null || s.trim().isEmpty())
            return true;
        return false;
    }

    public static boolean isEmpty(Object[] o)
    {
        if (o == null || o.length == 0)
            return true;
        return false;
    }
    
    public static String prependToLength(String orig, int length, String c)
    {
        StringBuilder b = new StringBuilder();
        
        if (isEmpty(orig))
            return "";
        if (orig.length() >= length)
            return orig;
        int numToAdd = length - orig.length();
        for (int i = 0; i < numToAdd; i++)
            b.append(c);
        b.append(orig);
        
        return b.toString();
    }

    /*
     * Get the extension of a file. Stole this from the java filechooser
     * tutorial.
     */
    public static String getFileExtension(File f)
    {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1)
        {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    public static void printAndExit(String msg)
    {
        printAndExit(msg, -1);
    }

    public static void printAndExit(String msg, int status)
    {
        System.out.println(msg);
        System.exit(status);
    }

    /**
     * Tests if a number is within the given range inclusively
     * @param number
     * @param min
     * @param max
     * @return
     */
    public static boolean testRange(double number, double min, double max)
    {
        if (number > max || number < min)
            return false;
        return true;
    }

    public static double stdDeviation(long[] data)
    {
        final int n = data.length;
        long mean = 0;
        for (long l : data)
            mean += l;
        mean /= n;

        long sumS = 0;
        for (long l : data)
        {
            long x = (l - mean);
            sumS += (x * x);
        }
        return Math.sqrt(sumS / n);
    }

    public static double average(long[] data)
    {
        final int n = data.length;
        long sum = 0;
        for (long l : data)
            sum += l;
        return sum / n;
    }

    /**
     * Finds the GCD of a list of longs
     * 
     * @param diffs List of longs
     * @return
     */
    public static long findingGCD(ArrayList<Long> diffs)
    {
        BigInteger gcd = null;

        for (Long l : diffs)
        {
            if (gcd == null) // First, set
                gcd = BigInteger.valueOf(l);
            else
                // Others, update
                gcd = gcd.gcd(BigInteger.valueOf(l));
        }
        return gcd.longValue();
    }

    private static final String HEX_DIGITS[] =
        {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c",
            "d", "e", "f"
        };

    /**
     * 
     * Convert a byte[] array to readable string format. This makes the "hex"
     * readable!
     * Originally stolen from: http://www.devx.com/tips/Tip/13540 but modified.
     * 
     * @param in byte[] buffer to convert to string format
     * @param group option to group hex digits in groups of 2
     * @return result String buffer in String format
     * 
     */
    public static String byteArrayToHexString(byte in[], boolean group)
    {
        byte ch = 0x00;
        int i = 0;

        if (in == null || in.length <= 0)
            return null;

        StringBuilder out = new StringBuilder(in.length * 2);

        while (i < in.length)
        {
            ch = (byte) (in[i] & 0xF0); // Strip off high nibble
            ch = (byte) (ch >>> 4);
            // shift the bits down
            ch = (byte) (ch & 0x0F);
            // must do this is high order bit is on!
            out.append(HEX_DIGITS[(int) ch]); // convert the nibble to a String
            // Character
            ch = (byte) (in[i] & 0x0F); // Strip off low nibble
            out.append(HEX_DIGITS[(int) ch]); // convert the nibble to a String
            // Character
            i++;

            if (group)
                out.append(" ");
        }

        String rslt = new String(out);

        return rslt;

    }
    
    static public String createCSVString(String[] s, boolean addSpace)
    {
        if (s == null || s.length == 0)
            return "";
        if (s.length == 1)
            return s[0];
        
        String sepChar = "," + (addSpace ? " " : "");
        
        StringBuilder b = new StringBuilder();
        b.append(s[0]);
        for(int i = 1; i < s.length; i++)
        {
            b.append(sepChar);
            b.append(s[i]);
        }
        return b.toString();
    }
    
    public static final int NOT_FOUND = -1;
    public static final String NOT_FOUND_STR = "-1";
}
