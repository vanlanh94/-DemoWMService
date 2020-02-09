package dn.mrv.wm.utils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Helper {

    /**
     * Constructor method.
     */
    private Helper() {
    }

    /**
     * <b>Method check a string is empty.</b>
     * @param obj
     *            - An Input String.
     * @return Return true if object is empty or null and otherwise false.
     */
    public static boolean isEmpty(final String obj) {
        return obj == null || Constants.EMPTY_STRING.equals(obj.trim());
    }

    /**
     * <b>Method check string is empty.</b>
     * @param obj
     *            - An Input Object.
     * @return Returns true if Object is null, otherwise false.
     */
    public static boolean isEmpty(final Object obj) {
        return obj == null;
    }

    /**
     * <b>Method check string is empty.</b>
     * @param map
     *            - An Input Object.
     * @return Returns true if Object is null, otherwise false.
     */
    public static boolean isEmpty(final Map map) {
        return map == null || map.isEmpty();
    }

    /**
     * Checks if is empty.
     * @param set
     *            the set
     * @return true, if is empty
     */
    public static boolean isEmpty(final Set set) {
        return set == null || set.isEmpty();
    }

    /**
     * Check is empty BigDecimal.
     * @param value
     *            BigDecimal value
     * @return boolean value
     */
    public static boolean isEmpty(final BigDecimal value) {
        return value == null || new BigDecimal(00).equals(value);
    }

    /**
     * Check is empty BigDecimal.
     * @param value
     *            BigDecimal value
     * @return boolean value
     */
    public static boolean isNull(final BigDecimal value) {
        return value == null;
    }

    /**
     * Check is empty Collection<?>.
     * @param obj
     *            Collection value
     * @return boolean value
     */
    public static boolean isEmpty(final Collection<Object> obj) {
        return obj == null || obj.isEmpty();
    }

    /**
     * Check is empty List<?>.
     * @param obj
     *            Collection value
     * @return boolean value
     */
    public static boolean isEmpty(final List<?> obj) {
        return obj == null || obj.isEmpty();
    }

    /**
     * Check is empty List<?>.
     * @param obj1
     *            List<?>...
     * @return boolean value
     */
    public static boolean isListEmptys(final List<?>... obj1) {
        boolean status = true;
        for (List<?> ob : obj1) {
            if (ob != null && !ob.isEmpty()) {
                status = false;
                break;
            }
        }
        return status;
    }

    /**
     * Check is empty Object[].
     * @param arr
     *            Object array
     * @return true or false
     */
    public static boolean isEmpty(final Object[] arr) {
        return arr == null || arr.length == 0;
    }


}
