import java.util.Objects;

public class Substring {
    private int pos;
    private int length;

    public Substring(int pos, int length) {
        this.pos = pos;
        this.length = length;
    }

    public int getPos() {
        return pos;
    }

    public int getLength() {
        return length;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Substring substring = (Substring) o;
        return pos == substring.pos && length == substring.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, length);
    }

    @Override
    public String toString() {
        return "Substring{" +
                "pos=" + pos +
                ", length=" + length +
                '}';
    }
}
