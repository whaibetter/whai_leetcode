package cn.whaifree.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/23 20:36
 * @注释
 */
public class CRC16_ARC {



    private static final int CRC16_ARC = 0x8005; // CRC16-ARC多项式

    /**
     * 计算CRC16ARC校验值
     * @param data 要计算校验值的数据数组
     * @return 返回计算得到的CRC16ARC校验值
     */
    public static int calculateCRC16ARC(byte[] data) {
        // 使用CRC32算法计算校验值，然后将其转换为16位
        int crcValue = 0xFFFF;  // 初始值为0xFFFF，表示CRC16-ARC的初始寄存器状态

        // 遍历数据数组，逐字节计算CRC校验值
        for (byte b : data) {
            crcValue ^= b & 0xFF;  // 将数据字节与CRC值异或

            // 对CRC值进行8次位运算，模拟移位和异或过程
            for (int i = 0; i < 8; i++) {
                if ((crcValue & 0x0001) != 0) {
                    crcValue = (crcValue >> 1) ^ CRC16_ARC;  // 如果最低位为1，进行异或运算
                } else {
                    crcValue >>= 1;  // 如果最低位为0，仅右移一位
                }
            }
        }

        return crcValue;
    }



    /**
     * 为指定的源文件附加CRC16ARC校验值，并将结果保存为新文件。
     *
     * @param sourceFilePath 源文件路径，将对此文件进行读取。
     * @param destinationFilePath 保存带有CRC校验值的新文件的路径。
     * @throws IOException 如果在读取源文件或写入新文件过程中发生I/O错误。
     */
    public static void attachCRC16ARC(String sourceFilePath, String destinationFilePath) throws IOException {
        // 打开源文件并读取所有内容
        FileInputStream fis = new FileInputStream(sourceFilePath);
        byte[] data = fis.readAllBytes();
        fis.close();

        // 计算源文件数据的CRC16ARC校验值
        int crcValue = calculateCRC16ARC(data);

        // 打开目标文件，写入源文件数据和CRC校验值
        FileOutputStream fos = new FileOutputStream(destinationFilePath);
        fos.write(data);
        // 写入CRC的高字节
        fos.write((crcValue & 0xFF00) >> 8);
        // 写入CRC的低字节
        fos.write(crcValue & 0xFF);
        fos.close();
    }

    public static byte[] generateCRC16ARCForSend(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        byte[] data = fis.readAllBytes();
        fis.close();

        // 计算CRC16ARC校验值
        int crcValue = calculateCRC16ARC(data);

        byte[] crcBytes = new byte[2];
        crcBytes[0] = (byte) ((crcValue >> 8) & 0xFF);  // 高位字节
        crcBytes[1] = (byte) (crcValue & 0xFF);         // 低位字节

        byte[] dataWithCRC = new byte[data.length + 2];
        System.arraycopy(data, 0, dataWithCRC, 0, data.length);
        System.arraycopy(crcBytes, 0, dataWithCRC, data.length, 2);

        return dataWithCRC;
    }

    public static boolean verifyCRC16ARCByBytes(byte[] bytes) {

        int crcValue = ((bytes[bytes.length - 2] & 0xFF) << 8) | (bytes[bytes.length - 1] & 0xFF);
        byte[] crcData = new byte[]{(byte) (crcValue >> 8), (byte) crcValue};
        byte[] data = Arrays.copyOf(bytes, bytes.length - crcData.length);
        int calculatedCRC = calculateCRC16ARC(data);
        return crcValue == calculatedCRC;
    }


    /**
     * 读取文件时，提取末尾的CRC值并进行验证：
     * @param filePath
     * @return
     * @throws IOException
     */
    public static boolean verifyCRC16ARC(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        byte[] data = fis.readAllBytes();
        fis.close();

        // 文件附带的CRC
        int fileCRC = ((data[data.length - 2] & 0xFF) << 8) | (data[data.length - 1] & 0xFF);
        byte[] fileData = Arrays.copyOf(data, data.length - 2);
        // 重新计算的CRC
        int calculatedCRC = calculateCRC16ARC(fileData);

        return fileCRC == calculatedCRC;
    }



    public static void main(String[] args) throws IOException, ClassNotFoundException {



        String filePath = "D:\\Downloads\\BFD-D42401883.pdf";
        String destinationFilePath = "D:\\Downloads\\BFD-D42401883-1.pdf"; // 输出的
        attachCRC16ARC(filePath, destinationFilePath);
        if (verifyCRC16ARC(destinationFilePath)) {
            System.out.println("验证成功");
        }else {
            System.out.println("验证失败");
        }


        byte[] bytes = generateCRC16ARCForSend(filePath); // 生成附带CRC的完整文件
        if (verifyCRC16ARCByBytes(bytes)) { // 验证
            System.out.println("验证成功");
        }else {
            System.out.println("验证失败");
        }


        System.out.println(calculateCRC16ARC(new byte[]{1}));

    }
}
