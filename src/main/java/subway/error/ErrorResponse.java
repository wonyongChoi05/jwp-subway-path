package subway.error;

public class ErrorResponse {

    private final int status;
    private final String massage;

    public ErrorResponse(final int status, final String massage) {
        this.status = status;
        this.massage = massage;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\"status\": " + status +
                ",\t\"description\": \"" + massage + '\"' +
                "\n}";
    }

}
