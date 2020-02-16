package com.bmw.boss.infos.exportExcle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * Created by qxr4383 on 2018/3/12.
 */
public class ImportByMappedByteBuffer {
    public static void main(String[] args) {
        String filePath = "/Users/qxr4383/Documents/work/logger/prod/id5/id5-weather/2018/weather.txt";
        String filePath1 = "/Users/qxr4383/Documents/work/logger/prod/access/2018/01/1.txt";
        System.out.println(filePath1);
        readTxt(filePath1);
    }
    static int length = 0x8000000; // 128 Mb
    public static void readTxt(String filePath){
//        FileInputStream fis = null;
//        FileChannel inChannel = null;
//        int bufSize = 1024 * 14 * 1024;
        File file = new File(filePath);
        ImportByMappedByteBuffer mf = new ImportByMappedByteBuffer();
        try {
            mf.structXML(file);
//            fis = new FileInputStream(filePath);
//            inChannel = fis.getChannel();
//            ByteBuffer buffer = ByteBuffer.allocate(bufSize);
//            MappedByteBuffer mbb = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
//            new Thread(new CompareThread(buffer)).start();
//            for (int i = 0; i < length; i++) {
//                mbb.put((byte) 'x');
//            }
//            System.out.println("Finished writing");
//            //读取文件中间6个字节内容
//            for (int i = length / 2; i < length / 2 + 6; i++) {
//                System.out.print((char) mbb.get(i));
//            }
        } catch (Exception e) {
            System.out.println("文件读取错误!");
            e.printStackTrace();
        } finally {
//            if(fis != null){
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(inChannel != null){
//                try{
//                    inChannel.close();
//                }catch (IOException e){
//                    e.printStackTrace();
//                }
//            }

        }


    }

    /**
     * 对一个文件画块后开启不同的线程，线程中划分的块中要记录下一个块的位置。
     * @throws IOException
     * */
    private List structXML(File file) throws IOException{
        long start = System.currentTimeMillis();
        for(int i=1;i<=8;i++){
            try {
                MappedByteBuffer buffer = new RandomAccessFile(file, "r")
                        .getChannel().map(FileChannel.MapMode.READ_ONLY,
                                file.length()*7/8,file.length()/8);
                //0, file.length());
                new Thread(new CompareThread(buffer)).start();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("====花费时间===="+(System.currentTimeMillis()-start));
        return null;
    }

    //内部类实现读取文件
    class CompareThread implements Runnable{

        private MappedByteBuffer buffer = null;
        // 缓冲区大小为3M
        final int BUFFER_SIZE = 0x1000;
        // 每次读出3M的内容
        byte[] dst = new byte[BUFFER_SIZE];

        public CompareThread(MappedByteBuffer buffer) {
            this.buffer = buffer;
//				this.length = length;
        }

        public void run(){
            long start = System.currentTimeMillis();
            String enterStr = "\n";
            StringBuffer strBuf = new StringBuffer("");
            long total=0;
            for (int offset = 0; offset < this.buffer.capacity(); offset += BUFFER_SIZE) {
                if (this.buffer.capacity() - offset >= BUFFER_SIZE) {
                    for (int i = 0; i < BUFFER_SIZE; i++)
                        dst[i] = this.buffer.get(offset + i);
                } else {
                    for (int i = 0; i < this.buffer.capacity() - offset; i++)
                        dst[i] = this.buffer.get(offset + i);
                }
                int length = (this.buffer.capacity() % BUFFER_SIZE == 0) ? BUFFER_SIZE
                        : this.buffer.capacity() % BUFFER_SIZE;
                String tempString = new String(dst, 0, length);
                int fromIndex = 0;
                int endIndex = 0;
                while ((endIndex = tempString.indexOf(enterStr, fromIndex)) != -1) {
                    total++;
                    String line = tempString.substring(fromIndex, endIndex);
                    line = new String(strBuf.toString() + line);
                    fromIndex = endIndex + 1;
                    strBuf.delete(0, strBuf.length());
                }
//					if (rSize > tempString.length()) {
//						strBuf.append(tempString.substring(fromIndex,tempString.length()));
//					} else {
//						strBuf.append(tempString.substring(fromIndex, rSize));
//					}System.out.println("total----->"+total+"====花费时间===="+(System.currentTimeMillis()-start));
            }
            System.out.println("total----->"+total+"====花费时间===="+(System.currentTimeMillis()-start));

        }

    }
}
