package com.tangotangolima.nock;

import static com.tangotangolima.nock.Noun.ſ;

/**
 * Created by a00781 on 07.06.2014.
 */
public class Op {
    public static Noun fas(Noun n) {
        // 16 ::    /[1 a]           a
        if (n._1().equals(new Noun(1)))
            return n._2();

        // 17 ::    /[2 a b]         a
        if (n._1().equals(new Noun(2)))
            return n._2()._1();

        // 18 ::    /[3 a b]         b
        if (n._1().equals(new Noun(3)))
            return n._2()._2();

        // 19 ::    /[(a + a) b]     /[2 /[a b]]
        if (n._1().getValue() % 2 == 0) {
            final Noun a = new Noun(n._1().getValue() / 2);
            final Noun b = n._2();

            return fas(new Noun(new Noun(2), fas(new Noun(a, b))));
        }

        // 20 ::    /[(a + a + 1) b] /[3 /[a b]]
        if (n._1().getValue() % 2 == 1) {
            final Noun a = new Noun((n._1().getValue() - 1) / 2);
            final Noun b = n._2();

            return fas(new Noun(new Noun(3), fas(new Noun(a, b))));
        }

        throw new RuntimeException();
    }

    public static Noun tis(Noun n) {
        if (n.isCell()) {
            if (n._1().equals(n._2())) {
                return new Noun(0);
            } else {
                return new Noun(1);
            }
        }

        throw new RuntimeException();
    }

    public static Noun wut(Noun n) {
        if (n.isCell()) {
            return new Noun(0);
        } else if (n.isAtom()) {
            return new Noun(1);
        }

        throw new RuntimeException();
    }

    public static Noun /* atom */ lus(Noun n) {
        if (n.isAtom()) {
            return new Noun(n.getValue() + 1);
        }

        throw new RuntimeException();
    }

    public static Noun tar(Noun n) {
        final Noun a = n._1();

        // 23 ::    *[a [b c] d]     [*[a b c] *[a d]]
        if (n._2().isCell() && n._2()._1().isCell()) {
            final Noun b = n._2()._1()._1();
            final Noun c = n._2()._1()._2();
            final Noun d = n._2()._2();

            return Noun.ſ(
                    tar(Noun.ſ(a, b, c)),
                    tar(Noun.ſ(a, d))
            );
        }

        final Noun arg = n._2()._1();

        // 25 ::    *[a 0 b]         /[b a]
        if (arg.getValue() == 0) {
            final Noun b = n._2()._2();
            return fas(Noun.ſ(b, a));
        }

        // 26 ::    *[a 1 b]         b
        if (arg.getValue() == 1) {
            final Noun b = n._2()._2();
            return b;
        }

        // 27 ::    *[a 2 b c]       *[*[a b] *[a c]]
        if (arg.getValue() == 2) {
            final Noun b = n._2()._2()._1();
            final Noun c = n._2()._2()._2();

            return tar(
                    Noun.ſ(
                            tar(Noun.ſ(a, b)),
                            tar(Noun.ſ(a, c))
                    )
            );
        }

        // 28 ::    *[a 3 b]         ?*[a b]
        if (arg.getValue() == 3) {
            final Noun b = n._2()._2();

            return wut(tar(Noun.ſ(a, b)));
        }

        // 29 ::    *[a 4 b]         +*[a b]
        if (arg.getValue() == 4) {
            final Noun b = n._2()._2();

            return lus(tar(Noun.ſ(a, b)));
        }

        // 30 ::    *[a 5 b]         =*[a b]
        if (arg.getValue() == 5) {
            final Noun b = n._2()._2();

            return tis(tar(Noun.ſ(a, b)));
        }

        // 32 ::    *[a 6 b c d]     *[a 2 [0 1] 2 [1 c d] [1 0] 2 [1 2 3] [1 0] 4 4 b]
        //
        // [ a [6 [b [c d]]]]
        // 1   2
        //     1  2
        //        1  2
        //
        if (arg.getValue() == 6) {
            final Noun
                    b = n._2()._2()._1(),
                    c = n._2()._2()._2()._1(),
                    d = n._2()._2()._2()._2();

            // *[a 2 [0 1] 2 [1 c d] [1 0] 2 [1 2 3] [1 0] 4 4 b]
            return tar(
                    ſ(
                            a,
                            new Noun(2),
                            new Noun(0, 1),
                            new Noun(2),
                            Noun.ſ(new Noun(1), c, d),
                            new Noun(1, 0),
                            new Noun(2),
                            Noun.ſ(new Noun(1), new Noun(2), new Noun(3)),
                            new Noun(1, 0),
                            new Noun(4),
                            new Noun(4),
                            b
                    )
            );
        }

        // 33 ::    *[a 7 b c]       *[a 2 b 1 c]
        if (arg.getValue() == 7) {
            final Noun
                    b = n._2()._2()._1(),
                    c = n._2()._2()._2();

            return tar(
                    Noun.ſ(
                            a,
                            new Noun(2),
                            b,
                            new Noun(1),
                            c
                    )
            );
        }

        // 34 ::    *[a 8 b c]       *[a 7 [[7 [0 1] b] 0 1] c]
        if (arg.getValue() == 8) {
            final Noun
                    b = n._2()._2()._1(),
                    c = n._2()._2()._2();

            return tar(
                    ſ(
                            a,
                            new Noun(7),
                            ſ(
                                    Noun.ſ(
                                            new Noun(7),
                                            new Noun(0, 1),
                                            b
                                    ),
                                    new Noun(0),
                                    new Noun(1)
                            ),
                            c
                    )
            );
        }

        // 35 ::    *[a 9 b c]       *[a 7 c 2 [0 1] 0 b]
        if (arg.getValue() == 9) {
            final Noun
                    b = n._2()._2()._1(),
                    c = n._2()._2()._2();

            return tar(
                    Noun.ſ(
                            a,
                            new Noun(7),
                            c,
                            new Noun(2),
                            new Noun(0, 1),
                            new Noun(0),
                            b
                    )
            );
        }

        // 36 ::    *[a 10 [b c] d]  *[a 8 c 7 [0 3] d]
        if (arg.getValue() == 10 && n._2()._2()._2().isCell()) {
            final Noun
                    b = n._2()._2()._1()._1(),
                    c = n._2()._2()._1()._2(),
                    d = n._2()._2()._2();

            return tar(
                    Noun.ſ(
                            a,
                            new Noun(8),
                            c,
                            new Noun(7),
                            new Noun(0, 3),
                            d
                    )
            );
        }

        // 37 ::    *[a 10 b c]      *[a c]
        if (arg.getValue() == 10) {
            final Noun
                    b = n._2()._2()._1(),
                    c = n._2()._2()._2();

            return tar(new Noun(a, c));
        }

        throw new RuntimeException();
    }
}
