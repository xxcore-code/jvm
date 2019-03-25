package com.xxcore.code.jvm.java.util;

import java.util.Arrays;

public class UUID
{
    private String salt;
    private byte[] bytes;

    private UUID(String salt)
    {
        this.salt = salt;
        this.bytes = null;
    }

    private UUID(byte[] bytes)
    {
        this.salt = null;
        this.bytes = bytes;
    }

    private UUID()
    {
        this.salt = null;
        this.bytes = null;
    }

    /**
     * 返回特定长度的UUID字符串
     *
     * @param length 字符串长度，若小于0则取16
     * @return UUID字符串
     */
    public String toString(int length)
    {
        //返回特定长度的UUID字符串
        if (!(length > 0)) length = 16;

        String tempProtoUUIDString = this.getProtoUUID().toString();
        int lengthPerProtoUUID = tempProtoUUIDString.replaceAll("-", "").length();
        int numberOfPartsPerProtoUUID = tempProtoUUIDString.split("-").length;

        int numberOfProtoUUID = length / lengthPerProtoUUID;
        if (numberOfProtoUUID == 0) numberOfProtoUUID++;
        else if (length % lengthPerProtoUUID > 0) numberOfProtoUUID++;

        //看来还缺少一个规范化的操作
        java.util.List<String> tempStringArray =
                new java.util.ArrayList<>(numberOfProtoUUID * numberOfPartsPerProtoUUID);
        for (int i = 0; i < numberOfProtoUUID; i++)
        {
            tempStringArray.addAll(Arrays.asList(this.getProtoUUID().toString().split("-")));
        }
        return confuse(tempStringArray.toArray(new String[0])).substring(0, length);
    }

    private static String confuse(String[] strings)
    {
        java.util.List<Integer> confusedIndex = new java.util.ArrayList<>(strings.length);
        while (confusedIndex.size() < strings.length)
        {
            int tempInt = new java.util.Random().nextInt(strings.length);
            if (!confusedIndex.contains(tempInt))
            {
                confusedIndex.add(tempInt);
            }
        }
        java.util.Iterator<Integer> confusedIndexIterator = confusedIndex.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (confusedIndexIterator.hasNext())
        {
            stringBuilder.append(strings[confusedIndexIterator.next()]);
        }
        return stringBuilder.toString();
    }

    private java.util.UUID getProtoUUID()
    {
        if (this.salt != null)
        {
            return java.util.UUID.fromString(this.salt);
        }
        if (this.bytes != null)
        {
            return java.util.UUID.nameUUIDFromBytes(this.bytes);
        }
        return java.util.UUID.randomUUID();
    }

    //下面的部分写好之后不会变
    public static UUID randomUUID()
    {
        return new UUID();
    }

    public static UUID fromString(String name)
    {
        return new UUID(name);
    }

    public static UUID nameUUIDFromBytes(byte[] name)
    {
        return new UUID(name);
    }
}
