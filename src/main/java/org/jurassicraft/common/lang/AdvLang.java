package org.jurassicraft.common.lang;

import net.minecraft.util.StatCollector;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class AdvLang
{
    private String langPath;
    private Map<String, String> properties = new HashMap<String, String>();

    public AdvLang(String langPath)
    {
        this.langPath = langPath;
    }

    public AdvLang withProperty(String propertyName, String value)
    {
        properties.put(propertyName, StatCollector.translateToLocal(value));

        return this;
    }

    public String build()
    {
        String translation = StatCollector.translateToLocal(langPath);

        for (Entry<String, String> property : properties.entrySet())
        {
            translation = translation.replaceAll(Pattern.quote("{" + property.getKey() + "}"), property.getValue());
        }

        return translation;
    }
}
