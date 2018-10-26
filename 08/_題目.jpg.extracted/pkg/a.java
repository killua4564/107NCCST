// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package pkg;

import java.util.Arrays;

public final class a
{

    private static int a(int i, int j, int k, int l, int i1, int ai[])
    {
        return (k >>> 5 ^ j << 2) + (j >>> 3 ^ k << 4) ^ (i ^ j) + (ai[l & 3 ^ i1] ^ k);
    }

    public static final byte[] a(byte abyte0[], byte abyte1[])
    {
        if(abyte0.length == 0)
            return abyte0;
        abyte1 = a(Arrays.copyOf(abyte1, 16), false);
        abyte0 = a(abyte0, false);
        byte abyte2[] = new byte[256];
        for(int k = 0; k < 256; k++)
            abyte2[k] = (byte)k;

        int l;
        if((l = abyte0.length - 1) > 0) goto _L2; else goto _L1
_L1:
        abyte0;
          goto _L3
_L2:
        int i = 6 + 52 / (l + 1);
        int k1 = abyte0[0];
        for(int l1 = i * 0x9e3779b9; l1 != 0; l1 -= 0x9e3779b9)
        {
            int i2 = l1 >>> 2 & 3;
            int i1;
            for(i1 = l; i1 > 0; i1--)
            {
                byte byte0 = abyte0[i1 - 1];
                k1 = abyte0[i1] -= a(l1, k1, byte0, i1, i2, ((int []) (abyte1)));
            }

            byte byte1 = abyte0[l];
            k1 = abyte0[0] -= a(l1, k1, byte1, i1, i2, ((int []) (abyte1)));
        }

        abyte0;
_L3:
        abyte1 = 1;
        JVM INSTR dup ;
        abyte0;
        JVM INSTR arraylength .length;
        2;
        JVM INSTR ishl ;
        int j;
        j;
        byte byte2 = abyte0[abyte0.length - 1];
        j -= 4;
        if(byte2 < j - 3 || byte2 > j)
            return null;
        byte abyte3[] = new byte[j = byte2];
        for(int j1 = 0; j1 < j; j1++)
            abyte3[j1] = (byte)(abyte0[j1 >>> 2] >>> ((j1 & 3) << 3));

        return abyte3;
    }

    private static int[] a(byte abyte0[], boolean flag)
    {
        int ai[] = new int[flag = (abyte0.length & 3) != 0 ? ((boolean) ((abyte0.length >>> 2) + 1)) : ((boolean) (abyte0.length >>> 2))];
        flag = abyte0.length;
        for(int i = 0; i < flag; i++)
            ai[i >>> 2] |= (0xff & abyte0[i]) << ((i & 3) << 3);

        return ai;
    }
}
