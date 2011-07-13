package cgh.util;

import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.builder.ToStringStyle;

public class DumpObject
{
    public static final class MultiLineToStringStyle
        extends ToStringStyle
    {
        private Object readResolve()
        {
            return ToStringStyle.MULTI_LINE_STYLE;
        }
    
        private static final long serialVersionUID = 1L;
    
        MultiLineToStringStyle()
        {
            setContentStart("[");
            setFieldSeparator(SystemUtils.LINE_SEPARATOR + "  ");
            setFieldSeparatorAtStart(true);
            setContentEnd(SystemUtils.LINE_SEPARATOR + "]");
        }
    }
}
