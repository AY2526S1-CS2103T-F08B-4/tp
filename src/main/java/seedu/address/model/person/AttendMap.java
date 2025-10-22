package seedu.address.model.person;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A map from TutorialClass to attendance status for each student.
 */
public class AttendMap {

    public static final String MESSAGE_CONSTRAINTS = TutorialClass.MESSAGE_CONSTRAINTS;

    private final Map<TutorialClass, Integer> attendanceMap;

    /**
     * AttendMap constructor which populates TutorialClass with default value.
     */
    public AttendMap() {
        attendanceMap = new EnumMap<>(TutorialClass.class);
        // Initialize attendanceMap with default value 0
        for (TutorialClass tut : TutorialClass.values()) {
            attendanceMap.put(tut, 0);
        }
    }

    /**
     * Constructs a AttendMap with the given map.
     * Used for copying.
     */
    public AttendMap(AttendMap otherAttendMap) {
        assert otherAttendMap != null;
        attendanceMap = new EnumMap<>(otherAttendMap.attendanceMap);
    }

    /**
     * Returns true if a given map is a valid attend map.
     * A valid attend map has valid TutorialClass keys and valid integer values.
     */
    public static boolean isValidAttendMap(Map<String, String> attendMap) {
        for (Map.Entry<String, String> entry : attendMap.entrySet()) {
            String tutClassStr = entry.getKey();
            String attendanceStr = entry.getValue();

            // Ensure it is part of the enum class.
            if (!TutorialClass.isValidTutorialClass(tutClassStr)) {
                return false;
            }

            // Ensure value has to be "0" or "1".
            if (!attendanceStr.equals("0") && !attendanceStr.equals("1")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the overall attendance as a string in the form "x/11",
     * where x is the number of attended classes.
     */
    public String getOverallAttendance() {
        int attendedClasses = 0;
        int totalClasses = TutorialClass.getAllTutorialClass().length;

        for (TutorialClass tutClass : TutorialClass.getAllTutorialClass()) {
            attendedClasses += attendanceMap.get(tutClass);
        }

        return attendedClasses + "/" + totalClasses;
    }

    /**
     * Marks the student present for the given tutorial
     */
    public void markPresent(TutorialClass tut) {
        assert attendanceMap.containsKey(tut);
        attendanceMap.put(tut, 1);
    }

    /**
     * Marks the student absent for the given tutorial.
     */
    public void markAbsent(TutorialClass tut) {
        assert attendanceMap.containsKey(tut);
        attendanceMap.put(tut, 0);
    }

    /**
     * Checks if student is present for the given tutorial.
     */
    public boolean isPresent(TutorialClass tut) {
        assert attendanceMap.containsKey(tut);
        return attendanceMap.get(tut) == 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AttendMap)) {
            return false;
        }

        AttendMap other = (AttendMap) o;
        return attendanceMap.equals(other.attendanceMap);
    }

    /**
     * Converts the AttendMap to a Map with String keys and values.
     * Used for serialization
     */
    public Map<String, String> toStringMap() {
        Map<String, String> stringMap = new LinkedHashMap<>();
        for (Map.Entry<TutorialClass, Integer> entry : attendanceMap.entrySet()) {
            stringMap.put(entry.getKey().name(), entry.getValue().toString());
        }
        return stringMap;
    }
}
