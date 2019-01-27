

import java.util.Stack;

public class HangmanStack<T> extends Stack<T> {

    public T at(int index) {
        T out;
        HangmanStack<T> tmp = new HangmanStack<>();

        for (int i = 0; i <= index; i++) {
            tmp.push(pop());
        }

        out = tmp.peek();

        while (!tmp.empty()) {
            push(tmp.pop());
        }

        return out;
    }

    public void removeAt(int index) {
        HangmanStack<T> tmp = new HangmanStack<>();

        for (int i = 0; i < index - 1; i++) {
            tmp.push(pop());
        }

        pop();

        while (!tmp.empty()) {
            push(tmp.pop());
        }
    }

    @Override
    public synchronized String toString() {
        StringBuilder sb = new StringBuilder();

        HangmanStack<T> tmp = new HangmanStack<>();
        while (!empty()) {
            T c = pop();

            sb.append(c.toString());
            sb.append(" ");
            tmp.push(c);
        }

        while (!tmp.empty()) {
            push(tmp.pop());
        }

        return sb.toString();
    }

}
