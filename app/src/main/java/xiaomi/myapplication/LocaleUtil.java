package xiaomi.myapplication;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;


public class LocaleUtil {

    public enum SupportLocaleType {
        EN(Locale.ENGLISH.getLanguage()),
        JA(Locale.JAPAN.getLanguage()),
        ZH(Locale.CHINESE.getLanguage()),
        ;

        private final String mTAG;
        SupportLocaleType(final String tag) {
            mTAG = tag;
        }

        private static SupportLocaleType getTypeByOrdinal(final int ordinal) {
            int correctordinal;
            SupportLocaleType[] types = SupportLocaleType.values();
            if (ordinal >= 0 && ordinal < types.length) {
                correctordinal = ordinal;
            } else {
                correctordinal = getDefVal();
            }
            return types[correctordinal];
        }

        private static int getDefVal() {
            return EN.ordinal();
        }
    }

    public static String[] getSupportLocaleList() {
        SupportLocaleType[] list = SupportLocaleType.values();
        String[] supportList = new String[list.length];
        for (int i = 0; i < list.length; i++) {
            supportList[i] = list[i].mTAG;
        }
        return supportList;
    }

    public static Context getResourcesLegacy(Context context) {
        Locale locale = getLocale();
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        return context;
    }

    private static Locale getLocale() {
        Locale locale = Locale.getDefault();
        locale.toLanguageTag();
        locale.getDisplayLanguage();
        locale.getLanguage();
        return new Locale(getType().mTAG);
    }

    //可以修改為sharepreference的實作方法
    private static String mName = SupportLocaleType.EN.name();

    static void setType(final SupportLocaleType type) {
        mName = type.name();
    }

    static SupportLocaleType getTypeByOrdinal(final int ordinal) {
        return SupportLocaleType.getTypeByOrdinal(ordinal);
    }

    static SupportLocaleType getType() {
        return SupportLocaleType.valueOf(mName);
    }

}

