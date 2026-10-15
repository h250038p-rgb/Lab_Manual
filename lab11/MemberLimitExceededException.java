/** Checked exception: a member already holds the maximum number of loans (3). */
public class MemberLimitExceededException extends Exception {
    public MemberLimitExceededException(String message) { super(message); }
}
