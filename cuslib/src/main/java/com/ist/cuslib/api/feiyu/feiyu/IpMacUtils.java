package com.ist.cuslib.api.feiyu.feiyu;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 史章华 on 2018/10/8.
 */
public class IpMacUtils {
    public static String getGateway(Context context) {
        mContext = context;
        String gateway = getGatewayAddress(context);
        if (TextUtils.isEmpty(gateway) || gateway.contains("0.0.0")) {
            try {
                Enumeration<NetworkInterface> eni = NetworkInterface.getNetworkInterfaces();
                outer:
                while (eni.hasMoreElements()) {
                    NetworkInterface networkCard = eni.nextElement();
                    if (!networkCard.isUp()) { // 判断网卡是否在使用
                        continue;
                    }
                    List<InterfaceAddress> addressList = networkCard.getInterfaceAddresses();
                    Iterator<InterfaceAddress> addressIterator = addressList.iterator();
                    while (addressIterator.hasNext()) {
                        InterfaceAddress interfaceAddress = addressIterator.next();
                        InetAddress address = interfaceAddress.getAddress();
                        if (!address.isLoopbackAddress()) {
                            String hostAddress = address.getHostAddress();
                            if (hostAddress.indexOf(":") > 0) {
                            } else {
                                String maskAddress = calcMaskByPrefixLength(interfaceAddress.getNetworkPrefixLength());
                                gateway = calcSubnetAddress(hostAddress, maskAddress);
                                if (!TextUtils.isEmpty(gateway) && !gateway.contains("0.0.0")) {
                                    break outer;
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return gateway;
    }

    private static String calcMaskByPrefixLength(int length) {

        int mask = 0xffffffff << (32 - length);
        int partsNum = 4;
        int bitsOfPart = 8;
        int maskParts[] = new int[partsNum];
        int selector = 0x000000ff;

        for (int i = 0; i < maskParts.length; i++) {
            int pos = maskParts.length - 1 - i;
            maskParts[pos] = (mask >> (i * bitsOfPart)) & selector;
        }

        String result = "";
        result = result + maskParts[0];
        for (int i = 1; i < maskParts.length; i++) {
            result = result + "." + maskParts[i];
        }
        return result;
    }

    private static String calcSubnetAddress(String ip, String mask) {
        String result = "";
        try {
            // calc sub-net IP
            InetAddress ipAddress = InetAddress.getByName(ip);
            InetAddress maskAddress = InetAddress.getByName(mask);

            byte[] ipRaw = ipAddress.getAddress();
            byte[] maskRaw = maskAddress.getAddress();

            int unsignedByteFilter = 0x000000ff;
            int[] resultRaw = new int[ipRaw.length];
            for (int i = 0; i < resultRaw.length; i++) {
                resultRaw[i] = (ipRaw[i] & maskRaw[i] & unsignedByteFilter);
            }

            // make result string
            result = result + resultRaw[0];
            for (int i = 1; i < resultRaw.length; i++) {
                result = result + "." + resultRaw[i];
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String getGatewayAddress(Context context) {
        mContext = context;
        WifiManager wifiManager = getWifiManager(context);
        DhcpInfo mDhcpInfo = wifiManager.getDhcpInfo();
        //调用方法将int转换为地址字符串
        return Int2String(mDhcpInfo.gateway);
    }

    private final static String Int2String(int IP) {
        String ipStr = "";
        if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
            ipStr += String.valueOf(0xFF & IP);
            ipStr += ".";
            ipStr += String.valueOf(0xFF & IP >> 8);
            ipStr += ".";
            ipStr += String.valueOf(0xFF & IP >> 16);
            ipStr += ".";
            ipStr += String.valueOf(0xFF & IP >> 24);
        } else {
            ipStr += String.valueOf(0xFF & IP >> 24);
            ipStr += ".";
            ipStr += String.valueOf(0xFF & IP >> 16);
            ipStr += ".";
            ipStr += String.valueOf(0xFF & IP >> 8);
            ipStr += ".";
            ipStr += String.valueOf(0xFF & IP);
        }
        return ipStr;
    }

    private static WifiManager wifimanager;
    private static Context mContext;

    private synchronized static WifiManager getWifiManager(Context context) {
        mContext = context;
        if (wifimanager == null) {
            wifimanager = (WifiManager) mContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        }
        return wifimanager;
    }

    public static String getMacAddress(Context context) {
        return getMacAddress((String[]) null);
    }

    private static String getMacAddress(final String... excepts) {
        String macAddress = getMacAddressByWifiInfo();
        if (isAddressNotInExcepts(macAddress, excepts)) {
            return macAddress;
        }
        macAddress = getMacAddressByNetworkInterface();
        if (isAddressNotInExcepts(macAddress, excepts)) {
            return macAddress;
        }
        macAddress = getMacAddressByInetAddress();
        if (isAddressNotInExcepts(macAddress, excepts)) {
            return macAddress;
        }
        macAddress = getMacAddressByFile();
        if (isAddressNotInExcepts(macAddress, excepts)) {
            return macAddress;
        }
        return "";
    }

    private static boolean isAddressNotInExcepts(final String address, final String... excepts) {
        if (excepts == null || excepts.length == 0) {
            return !"02:00:00:00:00:00".equals(address);
        }
        for (String filter : excepts) {
            if (address.equals(filter)) {
                return false;
            }
        }
        return true;
    }

    private static String getMacAddressByWifiInfo() {
        try {
            Context context = mContext.getApplicationContext();
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            if (wifi != null) {
                WifiInfo info = wifi.getConnectionInfo();
                if (info != null) return info.getMacAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    private static String getMacAddressByNetworkInterface() {
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                if (ni == null || !ni.getName().equalsIgnoreCase("wlan0")) {
                    continue;
                }
                byte[] macBytes = ni.getHardwareAddress();
                if (macBytes != null && macBytes.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (byte b : macBytes) {
                        sb.append(String.format("%02x:", b));
                    }
                    return sb.substring(0, sb.length() - 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    private static String getMacAddressByInetAddress() {
        try {
            InetAddress inetAddress = getInetAddress();
            if (inetAddress != null) {
                NetworkInterface ni = NetworkInterface.getByInetAddress(inetAddress);
                if (ni != null) {
                    byte[] macBytes = ni.getHardwareAddress();
                    if (macBytes != null && macBytes.length > 0) {
                        StringBuilder sb = new StringBuilder();
                        for (byte b : macBytes) {
                            sb.append(String.format("%02x:", b));
                        }
                        return sb.substring(0, sb.length() - 1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    private static InetAddress getInetAddress() {
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                // To prevent phone of xiaomi return "10.0.2.15"
                if (!ni.isUp()){
                    continue;
                }
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress inetAddress = addresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String hostAddress = inetAddress.getHostAddress();
                        if (hostAddress.indexOf(':') < 0) {
                            return inetAddress;
                        }

                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getMacAddressByFile() {
        ShellUtils.CommandResult result = ShellUtils.execCmd("getprop wifi.interface", false);
        if (result.result == 0) {
            String name = result.successMsg;
            if (name != null) {
                result = ShellUtils.execCmd("cat /sys/class/net/" + name + "/address", false);
                if (result.result == 0) {
                    String address = result.successMsg;
                    if (address != null && address.length() > 0) {
                        return address;
                    }
                }
            }
        }
        return "02:00:00:00:00:00";
    }


}
