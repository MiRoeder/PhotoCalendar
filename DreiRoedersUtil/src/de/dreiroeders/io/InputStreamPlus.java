package de.dreiroeders.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownServiceException;
import java.security.MessageDigest;

public class InputStreamPlus extends BufferedInputStream {

    private long mFilePos;
    private MessageDigest mMD5;
    private MessageDigest mSHA1;
    private MessageDigest mSHA256;
    private MessageDigest mSHA512;
    private long mMarkedFilePos;
    private MessageDigest mMarkedMD5;
    private MessageDigest mMarkedSHA1;
    private MessageDigest mMarkedSHA256;
    private MessageDigest mMarkedSHA512;

    public InputStreamPlus(final InputStream in, final int size) {
        super(in, size);
        init();
    }

    public InputStreamPlus(final InputStream in) {
        super(in);
        init();
    }

    private void init() {
        try {
            mMD5 = MessageDigest.getInstance("MD5"); //$NON-NLS-1$
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            mSHA1 = MessageDigest.getInstance("SHA-1"); //$NON-NLS-1$
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            mSHA256 = MessageDigest.getInstance("SHA-256"); //$NON-NLS-1$
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            mSHA512 = MessageDigest.getInstance("SHA-512"); //$NON-NLS-1$
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        mFilePos = 0;
        mMarkedFilePos = 0;
        mMarkedMD5 = null;
        mMarkedSHA1 = null;
        mMarkedSHA256 = null;
        mMarkedSHA512 = null;
    }

    @Override
    public synchronized int read() throws IOException {
        long oldFilePos = mFilePos;
        int iRetVal = super.read();
        if (0 <= iRetVal && iRetVal <= 0xFF && oldFilePos == mFilePos) {
            /* if (oldFilePos+1 == mFilePos) then read byte was already counted for MD5 and SHA-1 */
            ++mFilePos;
            if (mMD5 != null) {
                mMD5.update((byte) iRetVal);
            }
            if (mSHA1 != null) {
                mSHA1.update((byte) iRetVal);
            }
            if (mSHA256 != null) {
                mSHA256.update((byte) iRetVal);
            }
            if (mSHA512 != null) {
                mSHA512.update((byte) iRetVal);
            }
        }
        return iRetVal;
    }

    @Override
    public synchronized int read(final byte[] b, final int off, final int len) throws IOException {
        long oldFilePos = mFilePos;
        int iRetVal = super.read(b, off, len);
        int nAlreadyCounted = (int) (mFilePos - oldFilePos);
        int nToBeCount = iRetVal - nAlreadyCounted;
        if (0 < nToBeCount) {
            if (mMD5 != null) {
                mMD5.update(b, off + nAlreadyCounted, nToBeCount);
            }
            if (mSHA1 != null) {
                mSHA1.update(b, off + nAlreadyCounted, nToBeCount);
            }
            if (mSHA256 != null) {
                mSHA256.update(b, off + nAlreadyCounted, nToBeCount);
            }
            if (mSHA512 != null) {
                mSHA512.update(b, off + nAlreadyCounted, nToBeCount);
            }
            mFilePos += nToBeCount;
        }
        return iRetVal;
    }

    @Override
    public synchronized long skip(final long n) throws IOException {
        long lBytesSkipped = 0;
        int nActRead = 1;
        byte[] buff = new byte[512];
        while (lBytesSkipped < n && nActRead > 0) {
            int len = (int) Math.min(buff.length, n - lBytesSkipped);
            nActRead = read(buff, 0, len);
            if (nActRead > 0) {
                lBytesSkipped += nActRead;
            }
        }
        return lBytesSkipped;
    }

    @Override
    public synchronized void mark(final int readlimit) {
        super.mark(readlimit);
        try {
            if (mMD5 != null) {
                mMarkedMD5 = (MessageDigest) mMD5.clone();
            }
            if (mSHA1 != null) {
                mMarkedSHA1 = (MessageDigest) mSHA1.clone();
            }
            if (mSHA256 != null) {
                mMarkedSHA256 = (MessageDigest) mSHA256.clone();
            }
            if (mSHA512 != null) {
                mMarkedSHA512 = (MessageDigest) mSHA512.clone();
            }
            mMarkedFilePos = mFilePos;
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public synchronized void reset() throws IOException {
        if (mMarkedMD5 != null || mMarkedSHA1 != null || mMarkedSHA256 != null || mMarkedSHA512 != null) {
            mMD5 = mMarkedMD5;
            mSHA1 = mMarkedSHA1;
            mSHA256 = mMarkedSHA256;
            mSHA512 = mMarkedSHA512;
            mFilePos = mMarkedFilePos;
        } else {
            throw new UnknownServiceException("MD5 or SHA-1 could not be resetted.");
        }
        super.reset();
    }

    @Override
    public int read(final byte[] b) throws IOException {
        long oldFilePos = mFilePos;
        int iRetVal = super.read(b);
        int nAlreadyCounted = (int) (mFilePos - oldFilePos);
        int nToBeCount = iRetVal - nAlreadyCounted;
        if (0 < nToBeCount) {
            if (mMD5 != null) {
                mMD5.update(b, nAlreadyCounted, nToBeCount);
            }
            if (mSHA1 != null) {
                mSHA1.update(b, nAlreadyCounted, nToBeCount);
            }
            if (mSHA256 != null) {
                mSHA256.update(b, nAlreadyCounted, nToBeCount);
            }
            if (mSHA512 != null) {
                mSHA512.update(b, nAlreadyCounted, nToBeCount);
            }
            mFilePos += nToBeCount;
        }
        return iRetVal;
    }

    public long getNumberOfReadBytes() {
        return mFilePos;
    }

    public byte[] calcAndResetMd5() {
        return mMD5.digest();
    }

    public byte[] calcAndResetSha1() {
        return mSHA1.digest();
    }

    public byte[] calcAndResetSha256() {
        return mSHA256.digest();
    }

    public byte[] calcAndResetSha512() {
        return mSHA512.digest();
    }

    public static String convert2hex(final byte[] in) {
        if (in != null && in.length > 0) {
            StringBuilder retVal = new StringBuilder(in.length*2);
            for (byte by : in) {
                int hd = (by >> 4) & 0x0F;
                retVal.append(Integer.toHexString(hd));
                hd = by & 0x0F;
                retVal.append(Integer.toHexString(hd));
            }
            return retVal.toString();
        } else {
            return "";
        }
    }

    public static InputStreamPlus createInst(final File file) throws IOException {
        FileInputStream inStream = new FileInputStream(file);
        return new InputStreamPlus(inStream);
    }

    public static void printHelp() {
        System.out.println("java InputStreamPlus filename");
    }

    public static void main(final String[] args) {
        try {
            if (args == null || args.length != 1) {
                printHelp();
                System.exit(1);
            } else {
                File inFile = new File(args[0]);
                InputStreamPlus in = createInst(inFile);
                System.out.println("Reading file "+inFile.getAbsolutePath() +" :");
                long lActRead;
                do {
                    lActRead = in.skip(2048);
                } while (lActRead > 0);
                System.out.println("Length: "+ in.getNumberOfReadBytes()
                + " bytes. MD5 = "+ convert2hex(in.calcAndResetMd5())
                + " sha1 = " + convert2hex(in.calcAndResetSha1())
                + " SHA-256 = " + convert2hex(in.calcAndResetSha256())
                + " SHA-512 = " + convert2hex(in.calcAndResetSha512()));
                if (in != null) {
                    in.close();
                }
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

}
