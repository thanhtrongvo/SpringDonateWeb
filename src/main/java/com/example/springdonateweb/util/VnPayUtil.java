package com.example.springdonateweb.util;

import jakarta.servlet.http.HttpServletRequest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Map;
import java.util.StringJoiner;


public class VnPayUtil {
//    // Tạo URL thanh toán
//    public static String createQueryUrl(Map<String, String> params, String hashSecret) throws Exception {
//        StringJoiner query = new StringJoiner("&");
//
//        for (Map.Entry<String, String> entry : params.entrySet()) {
//            query.add(entry.getKey() + "=" + entry.getValue());
//        }
//
//        // Ký dữ liệu
//        String secureHash = hmacSHA512(hashSecret, query.toString());
//        query.add("vnp_SecureHash=" + secureHash);
//        return query.toString();
//    }
//
//    // Hàm ký SHA512
//    private static String hmacSHA512(String key, String data) throws Exception {
//        MessageDigest md = MessageDigest.getInstance("SHA-512");
//        byte[] hashBytes = md.digest((key + data).getBytes(StandardCharsets.UTF_8));
//        StringBuilder hash = new StringBuilder();
//        for (byte b : hashBytes) {
//            hash.append(String.format("%02x", b));
//        }
//        return hash.toString();
//    }
//
//    // Lấy địa chỉ IP người dùng
//    public static String getIpAddress(HttpServletRequest request) {
//        String ipAddress = request.getHeader("X-Forwarded-For");
//        if (ipAddress == null) {
//            ipAddress = request.getRemoteAddr();
//        }
//        return ipAddress;
//    }
//
//    // Lấy thời gian hiện tại theo định dạng của VNPay
//    public static String getCurrentDateTime() {
//        return new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
//    }
}
