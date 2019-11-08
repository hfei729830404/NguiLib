package com.ng.ui;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {
    /**
     * 随机数Int的生成
     */
    // 随机数生成无边界的Int
    public static int getRandomForIntegerUnbounded() {
        return new Random().nextInt();
    }

    // 生成有边界的Int
    public static int getRandomForIntegerBounded(int min, int max) {

        return min + ((int) (new Random().nextFloat() * (max - min)));
    }

    // 包含1而不包含10
    // 使用Apache Common Math3来生成有边界的Int
    public static int getRandomForIntegerBounded2(int min, int max) {

        return new RandomDataGenerator().nextInt(min, max);
    }

    // 包含1且包含10
    // 使用Apache Common Lang3的工具类来生成有边界的Int
    public static int getRandomForIntegerBounded3(int min, int max) {

        return RandomUtils.nextInt(min, max);
    }

    // 使用TreadLocalRandom来生成有边界的Int,包含min而不包含max
    public static int getRandomForIntegerBounded4(int min, int max) {

        return ThreadLocalRandom.current().nextInt(min, max);
    }

    /**
     * 随机数Long的生成
     */
    // 随机数生成无边界的Long
    public static long getRandomForLongUnbounded() {

        return new Random().nextLong();
    }

    // 因为Random类使用的种子是48bits，所以nextLong不能返回所有可能的long值，long是64bits。
    // 使用Random生成有边界的Long
    public static long getRandomForLongBounded(long min, long max) {

        return min + (((long) (new Random().nextDouble() * (max - min))));
    }

    // 使用Apache Commons Math3来生成有边界的Long(RandomDataGenerator类提供的生成随机数的方法)
    public static long getRandomForLongBounded2(long min, long max) {
        return new RandomDataGenerator().nextLong(min, max);
    }

    // 使用Apache Commons Lang3的工具类来生成有边界的Long(RandomUtils提供了对java.util.Random的补充)
    public static long getRandomForLongBounded3(long min, long max) {
        return RandomUtils.nextLong(min, max);
    }

    // 使用ThreadLocalRandom生成有边界的Long
    public static long getRandomForLongBounded4(long min, long max) {
        return ThreadLocalRandom.current().nextLong(min, max);
    }

    /**
     * 随机数Float的生成
     */
    // 随机数Float的生成生成0.0-1.0之间的Float随机数
    public static float getRandomForFloat0To1() {
        return new Random().nextFloat();
    }

    // 以上只会生成包含0.0而不包括1.0的float类型随机数生成有边界的Float随机数
    public static float getRandomForFloatBounded(float min, float max) {
        return min + new Random().nextFloat() * (max - min);
    }

    // 使用Apache Common Math来生成有边界的Float随机数
    public static float getRandomForFloatBounded2(float min, float max) {
        float randomFloat = new RandomDataGenerator().getRandomGenerator().nextFloat();
        return min + randomFloat * (max - min);
    }

    // 使用Apache Common Lang来生成有边界的Float随机数
    public static float getRandomForFloatBounded3(float min, float max) {
        return RandomUtils.nextFloat(min, max);
    }

    // 使用ThreadLocalRandom生成有边界的Float随机数
    // ThreadLocalRandom类没有提供

    /**
     * 随机数Double的生成
     */
    // 生成0.0d-1.0d之间的Double随机数
    public static double getRandomForDouble0To1() {
        return new Random().nextDouble();
    }

    // 与Float相同，以上方法只会生成包含0.0d而不包含1.0d的随机数生成带有边界的Double随机数
    public static double getRandomForDoubleBounded(double min, double max) {
        return min + new Random().nextDouble() * (max - min);
    }

    // 使用Apache Common Math来生成有边界的Double随机数
    public static double getRandomForDoubleBounded2(double min, double max) {
        double boundedDouble = new RandomDataGenerator().getRandomGenerator().nextDouble();
        return min + boundedDouble * (max - min);
    }

    // 使用Apache Common Lang生成有边界的Double随机数
    public static double getRandomForDoubleBounded3(double min, double max) {
        return RandomUtils.nextDouble(min, max);
    }

    // 使用ThreadLocalRandom生成有边界的Double随机数
    public static double getRandomForDoubleBounded4(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
}
