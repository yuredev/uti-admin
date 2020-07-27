package springboot.backend.utils;

public class Message {
    public static final String NULL = "The field can't be null";
    public static final String NEGATIVE = "The field can't be negative";
    public static final String BLANK = "The field can't finish with spaces";
    public static final String OUT_OF_SIZE_NAME = "The name should contain between 2 and 70 characters";
    public static final String PATIENT_IN_ANOTHER_BED = "The given patient is in another bed";
    public static final String PATIENT_IN_ANOTHER_BED_DESCRIPTION = "Impossible to save the patient in the hospital bed " +
            "because this patient is in another bed. Try to save a database patient that it is not hospitalized" +
            "or even a new patient";
}
