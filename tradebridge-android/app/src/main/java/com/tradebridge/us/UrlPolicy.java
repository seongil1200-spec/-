package com.tradebridge.us;

import java.net.URI;

/** Restrict entry navigation to the TradeBridge host. */
public final class UrlPolicy {
    private UrlPolicy() {}
    public static final String HOST = "tradebridge-us-preview.noba-llc-1293.chatgpt.site";
    public static final String HOME = "https://" + HOST + "/";
    public static String page(String relativePath) {
        String result = URI.create(HOME).resolve(relativePath).toString();
        if (!isTrusted(result)) throw new IllegalArgumentException("Untrusted URL");
        return result;
    }
    public static boolean isTrusted(String url) {
        if (url == null) return false;
        try {
            URI u = new URI(url);
            return "https".equalsIgnoreCase(u.getScheme())
                && HOST.equalsIgnoreCase(u.getHost()) && u.getUserInfo() == null
                && (u.getPort() == -1 || u.getPort() == 443);
        } catch (Exception e) { return false; }
    }
}
