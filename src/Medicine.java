public class Medicine {
    private String name;
    private String dose;
    private String sideEffects;
    private String symptoms;
    private String usageGuidelines;

    public Medicine(String name, String dose, String sideEffects, String symptoms, String usageGuidelines) {
        this.name = name;
        this.dose = dose;
        this.sideEffects = sideEffects;
        this.symptoms = symptoms;
        this.usageGuidelines = usageGuidelines;
    }

    public String getName() {
        return name;
    }

    public String getDose() {
        return dose;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public String getUsageGuidelines() {
        return usageGuidelines;
    }
    
}
