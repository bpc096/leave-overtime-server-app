package com.mii.server.utils;

import javax.servlet.http.HttpServletRequest;

public class SiteUrl {
    public static String getSiteURL(HttpServletRequest request){
        String siteURL = request.getRequestURL().toString();
        System.out.println(siteURL);
        return siteURL.replace(request.getServletPath(), "");
    }
}
