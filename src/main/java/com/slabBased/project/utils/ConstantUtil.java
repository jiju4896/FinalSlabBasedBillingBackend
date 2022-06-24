package com.slabBased.project.utils;



public final class ConstantUtil {
    public static final String emailRegex="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
     public static final String passwordRegex="^((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])){4,12}$";
     public static final String userNameRegex="^[a-zA-Z0-9]{6,12}$";
     public static final String nameRegex="^[a-zA-z]$";
    public static final String jwtKey = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
    public static final long jwtKeyExpiryTime = 60 * 60 * 2;
}
