package com.qianyue.common.utils;

public class CommonUtils {

    /**
     * 将字节数组转换为16进制字符串
     *
     * @param bytes
     * @return
     */
    public static String binaryToHexString(byte[] bytes) {
        if (bytes == null || bytes.length == 0) return "";
        String hexStr = "0123456789ABCDEF";
        StringBuilder result = new StringBuilder("");
        String hex = "";
        for (byte b : bytes) {
            hex = String.valueOf(hexStr.charAt((b & 0xF0) >> 4));
            hex += String.valueOf(hexStr.charAt(b & 0x0F));
            result.append(hex);
            result.append(" ");//可加空格分割
        }
        return result.toString();
    }


    /**
     * hex字符串转byte数组
     * @param inHex 待转换的Hex字符串
     * @return  转换后的byte数组结果
     */
    public static byte[] hexToByteArray(String inHex){
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1){
            //奇数
            hexlen++;
            result = new byte[(hexlen/2)];
            inHex="0"+inHex;
        }else {
            //偶数
            result = new byte[(hexlen/2)];
        }
        int j=0;
        for (int i = 0; i < hexlen; i+=2){
            result[j]=hexToByte(inHex.substring(i,i+2));
            j++;
        }
        return result;
    }


    /**
     * Hex字符串转byte
     * @param inHex 待转换的Hex字符串
     * @return  转换后的byte
     */
    public static byte hexToByte(String inHex){
        return (byte) Integer.parseInt(inHex,16);
    }
}
