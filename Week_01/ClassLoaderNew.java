package com.how2java.jvm;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ClassLoaderNew extends ClassLoader{

    public static void main(String[] args) {
        try {
            new ClassLoaderNew().findClass("com.how2java.jvm.hello.Hello.xlass").newInstance();//加载并初始化Hello类
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] bytes = new byte[0];
        byte[] bytesNew = new byte[0];
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(name);
            outputStream = new ByteArrayOutputStream();
            bytes = outputStream.toByteArray();
            for(int i=0;i<bytes.length;i++){
                bytesNew[i] = (byte)(255-bytes[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return defineClass(name,bytesNew, 0, bytes.length);
    }

}
