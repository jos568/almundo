package com.example.demo.utils.response;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContext;

public  interface MessageSourceService {



    public String getMessage(String key);

    public String getMessage(String key, Object[] params);
    
    public String getMessage(String key, String param);

    public void setLocaleContext(LocaleContext localeContext);

    public void setLocaleContext(Locale locale);

    public void setLocaleContext(String langTag);

}