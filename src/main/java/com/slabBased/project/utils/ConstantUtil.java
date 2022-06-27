package com.slabBased.project.utils;


public final class ConstantUtil {
    public static final String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    public static final String passwordRegex = "^((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])){4,12}$";
    public static final String userNameRegex = "^[a-zA-Z0-9]{6,12}$";
    public static final String nameRegex = "^[a-zA-z]$";
    public static final String jwtKey = "D8756383CB57C0753AC0813E6FB6994CCE5CA88C44588E9FB3622FA0F0B98458";
    public static final long jwtKeyExpiryTime = 60 * 60 * 6;
}
