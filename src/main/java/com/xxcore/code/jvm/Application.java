package com.xxcore.code.jvm;

import com.xxcore.code.jvm.java.util.UUID;

import java.io.PrintWriter;

public class Application
{
    public static void main(String[] args)
    {
        UUID uuid = UUID.randomUUID();

        PrintWriter printWriter = new PrintWriter(System.out);
        for (int i = 0; i < 10; i++)
        {
            printWriter.println(uuid.toString(10));
        }
        printWriter.flush();
    }


}
