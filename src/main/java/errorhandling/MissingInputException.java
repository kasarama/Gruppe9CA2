
package errorhandling;

/**
 *
 * @author zarpy
 */
public class MissingInputException extends Exception {
        public MissingInputException (String message) {
        super(message);
    }
}