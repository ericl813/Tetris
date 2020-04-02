import java.awt.Color;
import java.util.Hashtable;

public class Configuration extends Object 
{
    /**
     * The internal configuration property values. This lookup table
     * is used to avoid setting configuration parameters in the system 
     * properties, as some programs (applets) do not have the security
     * permissions to set system properties.
     */
    private static Hashtable<String, String>  config = new Hashtable<String, String>();

    /**
     * Returns a configuration parameter value.
     * 
     * @param key: the configuration parameter key
     * 
     * @return the configuration parameter value, or
     *         null if not set
     */
    public static String getValue(String key) 
    {
        if (config.containsKey(key)) 
        {
            return config.get(key).toString();
        } 
        else 
        {
            try 
            {
                return System.getProperty(key);
            }
            catch (SecurityException ignore) 
            {
                return null;
            }
        }
    }
    
    /**
     * Returns a configuration parameter value. If the configuration
     * parameter is not set, a default value will be returned instead.
     * 
     * @param key       the configuration parameter key
     * @param def       the default value to use
     * 
     * @return the configuration parameter value, or
     *         the default value if not set
     */
    public static String getValue(String key, String def) 
    {
        String  value = getValue(key);
        
        return (value == null) ? def : value;
    }

    /**
     * Sets a configuration parameter value.
     * 
     * @param key       the configuration parameter key
     * @param value     the configuration parameter value
     */
    public static void setValue(String key, String value) 
    {
        config.put(key, value);
    }

    /**
     * Returns the color configured for the specified key. The key 
     * will be prepended with "tetris.color." and the value will be
     * read from the system properties. The color value must be 
     * specified in hexadecimal web format, i.e. in the "#RRGGBB" 
     * format. If the default color isn't in a valid format, white 
     * will be returned.
     * 
     * @param key       the configuration parameter key
     * @param def       the default value
     * 
     * @return the color specified in the configuration, or 
     *         a default color value
     */
    public static Color getColor(String key, String def) 
    {
        String  value = getValue("tetris.color." + key, def);
        Color   color;
        
        color = parseColor(value);
        if (color != null) 
        {
            return color;
        }
        color = parseColor(def);
        if (color != null) 
        {
            return color;
        }
        else 
        {
            return Color.white;
        }
    }
    
    /**
     * Parses a web color string. If the color value couldn't be 
     * parsed correctly, null will be returned.
     * 
     * @param value     the color value to parse
     * 
     * @return the color represented by the string, or
     *         null if the string was malformed
     */
    private static Color parseColor(String value) 
    {
        if (!value.startsWith("#")) 
        {
            return null;
        }
        try 
        {
            return new Color(Integer.parseInt(value.substring(1), 16));
        } 
        catch (NumberFormatException ignore)
        {
            return null;
        }
    }
}
