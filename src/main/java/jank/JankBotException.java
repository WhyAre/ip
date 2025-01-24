package jank;

/**
 * Custom exception class for the chatbot
 */
public class JankBotException extends Exception {
    JankBotException(String msg) {
        super(msg);
    }
}
