package lu.uni.jakartaee.moviefy.exeptions;

public class APICallNotSuccessful extends Exception {
    public APICallNotSuccessful(String message) {
        super(message);
    }
}
