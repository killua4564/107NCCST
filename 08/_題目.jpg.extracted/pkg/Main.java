// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package pkg;

import java.io.*;

// Referenced classes of package pkg:
//            a

public class Main
{

    public Main()
    {
    }

    public static void main(String args[])
    {
        args = "No Access";
        int i = 0;
        char ac[];
        int l = (ac = args.toCharArray()).length;
        for(int k = 0; k < l; k++)
        {
            char c = ac[k];
            i += c;
        }

        if((i % 37 | i % 23) != 0)
        {
            System.out.println(args);
            return;
        }
        args = "e.bin";
        InputStream inputstream = pkg/Main.getResourceAsStream(args);
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        byte abyte0[] = new byte[4096];
        while((i = inputstream.read(abyte0)) > 0) 
            bytearrayoutputstream.write(abyte0, 0, i);
        abyte0 = a.a(bytearrayoutputstream.toByteArray(), args.getBytes());
        File file;
        (file = File.createTempFile("decrypt", ".jpg")).deleteOnExit();
        (args = new FileOutputStream(file)).write(abyte0);
        args.close();
        for(int j = 5; j > 0; j--)
        {
            System.out.println(j);
            Thread.sleep(1000L);
        }

        System.out.println("Bye");
    }
}
