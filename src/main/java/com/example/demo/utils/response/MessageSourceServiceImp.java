package com.example.demo.utils.response;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceServiceImp implements MessageSourceService{
    
    
      @Autowired
    private MessageSource messageSource;

    public  String getMessage(String key) {

        Locale currentLocale = LocaleContextHolder.getLocale();

        return messageSource.getMessage(key, new Object[]{}, currentLocale);
    }

    public  String getMessage(String key, Object[] params) {

        Locale currentLocale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, params, currentLocale);
    }

    public  String getMessage(String key, String param) {

        Locale currentLocale = LocaleContextHolder.getLocale();

        return messageSource.getMessage(key, new Object[]{param}, currentLocale);
    }

    public  void setLocaleContext(LocaleContext localeContext) {
        LocaleContextHolder.setLocale(localeContext.getLocale());
    }

    public  void setLocaleContext(Locale locale) {
        LocaleContextHolder.setLocale(locale);
    }

    public  void setLocaleContext(String langTag) {

        Locale locale = Locale.forLanguageTag(langTag);

        LocaleContextHolder.setLocale(locale);

        SimpleDateFormat.getAvailableLocales();

    }

	
    
}