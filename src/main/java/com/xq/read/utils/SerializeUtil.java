package com.xq.read.utils;

import java.io.*;
import java.util.ArrayList;

/**
 * @author:xq
 * @date:2021/10/28 12:37
 * ClassName:SerializeUtil
 * Package:com.xq.read.utils
 * Description:
 */
public class SerializeUtil {

    public  static<T>  byte[]  serialize(T t){
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(t);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  static<T>  T  unSerialize(byte[] bytes) {

        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes); ObjectInputStream ois = new ObjectInputStream(bais)) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
