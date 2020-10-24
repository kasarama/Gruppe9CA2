
package errorhandling;

/**
 *
 * @author zarpy
 */
public class PersonNotFoundException extends Exception{
        public PersonNotFoundException(String message) {
        super(message);
    }
}
