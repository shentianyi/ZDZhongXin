package com.zd.core.util;

/**
 * 数据工具类
 * @author ShenLiang
 * @create 2014年7月3日
 */
public class ArrayUtils {
	// ----------------------------------------------------------------------
	// 摘自apache commons-lang
    /**
     * <p>Checks if an array of Objects is empty or {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>Checks if an array of primitive longs is empty or {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final long[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>Checks if an array of primitive ints is empty or {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final int[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>Checks if an array of primitive shorts is empty or {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final short[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>Checks if an array of primitive chars is empty or {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final char[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>Checks if an array of primitive bytes is empty or {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final byte[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>Checks if an array of primitive doubles is empty or {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final double[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>Checks if an array of primitive floats is empty or {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final float[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>Checks if an array of primitive booleans is empty or {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final boolean[] array) {
        return array == null || array.length == 0;
    }

    // ----------------------------------------------------------------------
    /**
     * <p>Checks if an array of Objects is not empty or not {@code null}.</p>
     *
     * @param <T> the component type of the array
     * @param array  the array to test
     * @return {@code true} if the array is not empty or not {@code null}
     * @since 2.5
     */
     public static <T> boolean isNotEmpty(final T[] array) {
         return (array != null && array.length != 0);
     }

    /**
     * <p>Checks if an array of primitive longs is not empty or not {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is not empty or not {@code null}
     * @since 2.5
     */
    public static boolean isNotEmpty(final long[] array) {
        return (array != null && array.length != 0);
    }

    /**
     * <p>Checks if an array of primitive ints is not empty or not {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is not empty or not {@code null}
     * @since 2.5
     */
    public static boolean isNotEmpty(final int[] array) {
        return (array != null && array.length != 0);
    }

    /**
     * <p>Checks if an array of primitive shorts is not empty or not {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is not empty or not {@code null}
     * @since 2.5
     */
    public static boolean isNotEmpty(final short[] array) {
        return (array != null && array.length != 0);
    }

    /**
     * <p>Checks if an array of primitive chars is not empty or not {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is not empty or not {@code null}
     * @since 2.5
     */
    public static boolean isNotEmpty(final char[] array) {
        return (array != null && array.length != 0);
    }

    /**
     * <p>Checks if an array of primitive bytes is not empty or not {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is not empty or not {@code null}
     * @since 2.5
     */
    public static boolean isNotEmpty(final byte[] array) {
        return (array != null && array.length != 0);
    }

    /**
     * <p>Checks if an array of primitive doubles is not empty or not {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is not empty or not {@code null}
     * @since 2.5
     */
    public static boolean isNotEmpty(final double[] array) {
        return (array != null && array.length != 0);
    }

    /**
     * <p>Checks if an array of primitive floats is not empty or not {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is not empty or not {@code null}
     * @since 2.5
     */
    public static boolean isNotEmpty(final float[] array) {
        return (array != null && array.length != 0);
    }

    /**
     * <p>Checks if an array of primitive booleans is not empty or not {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is not empty or not {@code null}
     * @since 2.5
     */
    public static boolean isNotEmpty(final boolean[] array) {
        return (array != null && array.length != 0);
    }

    public static String[] concat(String[] a, String[] b) {  
    	   String[] c= new String[a.length+b.length];  
    	   System.arraycopy(a, 0, c, 0, a.length);  
    	   System.arraycopy(b, 0, c, a.length, b.length);  
    	   return c;  
    	}
    
    public static boolean contains(int[] array,int value){
    	if(array!=null&&array.length>0){
    		for (int i : array) {
				if(i==value)
					return true;
			}
    		return false;
    	}else{
    		return false;
    	}
    	
    }

}