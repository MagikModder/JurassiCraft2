package net.timeless.animationapi;

import com.google.gson.Gson;
import net.ilexiconn.llibrary.common.json.container.JsonTabulaModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class TabulaModelHelper
{
    private static Gson gson = new Gson();

    public static JsonTabulaModel parseModel(String path) throws Exception
    {
        if (!path.endsWith("\\.tbl"))
        {
            path += ".tbl";
        }

        try
        {
            ZipInputStream inputStream = new ZipInputStream(TabulaModelHelper.class.getResourceAsStream(path));
            ZipEntry entry;
            JsonTabulaModel parseTabulaModel = null;

            while ((entry = inputStream.getNextEntry()) != null)
            {
                if (entry.getName().equals("model.json"))
                {
                    parseTabulaModel = parseModel(inputStream);
                    break;
                }
            }

            return parseTabulaModel;
        } catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static JsonTabulaModel parseModel(InputStream stream)
    {
        return gson.fromJson(new InputStreamReader(stream), JsonTabulaModel.class);
    }
}