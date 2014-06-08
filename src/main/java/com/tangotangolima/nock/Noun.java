package com.tangotangolima.nock;

import java.util.Arrays;

import static com.tangotangolima.nock.NounType.ATOM;
import static com.tangotangolima.nock.NounType.CELL;

/**
 * Created by a00781 on 07.06.2014.
 */
public class Noun implements Atom, Cell {
    private final NounType type;
    private int atom;
    private Noun _1, _2;

    public Noun(int i) {
        atom = i;
        type = ATOM;
    }

    public Noun(Cell c) {
        _1 = c._1();
        _2 = c._2();
        type = CELL;
    }

    public Noun(Noun n1, Noun n2) {
        _1 = n1;
        _2 = n2;
        type = CELL;
    }

    public Noun(int a, int b) {
        _1 = new Noun(a);
        _2 = new Noun(b);
        type = CELL;
    }

    @Override
    public int getValue() {
        if (type != ATOM)
            throw new RuntimeException("Attempted to get the atom value from a non-atom");

        return atom;
    }

    @Override
    public Noun _1() {
        if (type != CELL)
            throw new RuntimeException("Attempted to get head from non-cell " + toString());

        return _1;
    }

    @Override
    public Noun _2() {
        if (type != CELL)
            throw new RuntimeException("Attempted to get tail from non-cell; " + toString());

        return _2;
    }


    @Override
    public String toString() {
        if (type == ATOM) {
            return String.valueOf(atom);
        } else {
            return "[" + _1.toString() + " " + _2.toString() + "]";
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Noun noun = (Noun) o;

        if (type != noun.type) return false;

        if (type == ATOM) {
            if (atom != noun.atom) return false;
        }

        if (type == CELL) {
            if (_1 != null ? !_1.equals(noun._1) : noun._1 != null) return false;
            if (_2 != null ? !_2.equals(noun._2) : noun._2 != null) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + atom;
        result = 31 * result + (_1 != null ? _1.hashCode() : 0);
        result = 31 * result + (_2 != null ? _2.hashCode() : 0);
        return result;
    }

    public static Noun ſ(Noun... l) {
        if (l.length == 1) {
            return l[0];
        }

        if (l.length == 2) {
            return new Noun(l[0], l[1]);
        }

        final Noun[] rest = Arrays.copyOfRange(l, 1, l.length);
        return new Noun(l[0], ſ(rest));
    }

    public static Noun ſ(int... l) {
        final Noun[] n = new Noun[ l.length ];
        for (int i = 0; i < l.length; i++) {
            n[i] = new Noun(l[i]);
        }

        return ſ(n);
    }

    public static Noun ſ(int i) {
        return new Noun(i);
    }

    public boolean isCell() {
        return type == CELL;
    }

    public boolean isAtom() {
        return type == ATOM;
    }
}
