package com.tangotangolima.nock;

import org.junit.Test;

import static com.tangotangolima.nock.Noun.ſ;
import static com.tangotangolima.nock.Op.fas;
import static com.tangotangolima.nock.Op.tar;
import static org.junit.Assert.assertEquals;

public class OpTest {

    @Test
    public void testFas() {
        final Noun fas1 = ſ(
                ſ(97, 2),
                ſ(1, 42, 0)
        );

        assertEquals(fas(ſ(ſ(1), fas1)), fas1);
        assertEquals(fas(ſ(ſ(2), fas1)), ſ(97, 2));
        assertEquals(fas(ſ(ſ(3), fas1)), ſ(1, 42, 0));
        assertEquals(fas(ſ(ſ(6), fas1)), ſ(1));
        assertEquals(fas(ſ(ſ(7), fas1)), ſ(42, 0));
    }

    @Test
    public void testTar1() {
        assertEquals(tar(ſ(5, 1, 6)), ſ(6));
    }

    @Test
    public void testTar2() {
        assertEquals(tar(ſ(ſ(19, 42), ſ(0, 3), ſ(0), ſ(2))), ſ(42, 19));
    }

    @Test
    public void testTarInc() {
        assertEquals(tar(ſ(ſ(42), ſ(4, 0, 1))), ſ(43));
    }

    @Test
    public void testTarInc2() {
        final Noun val = ſ(42);
        final Noun form = ſ(ſ(6), ſ(1, 0), ſ(4, 0, 1), ſ(1, 233));
        final Noun actual = tar(ſ(val, form));
        final Noun expected = ſ(43);
    }

    // http://urbit.org/doc/nock/tut/3/
    @Test
    public void testTarDec1() {
        final Noun dec = ſ(ſ(8),
                ſ(
                        ſ(ſ(1),
                                ſ(ſ(1),
                                        ſ(ſ(8),
                                                ſ(ſ(1), ſ(0)),
                                                ſ(ſ(8),
                                                        ſ(ſ(1),
                                                                ſ(ſ(6),
                                                                        ſ(ſ(5), ſ(ſ(4), ſ(0), ſ(6)), ſ(ſ(0), ſ(30))),
                                                                        ſ(ſ(0), ſ(6)),
                                                                        ſ(ſ(9), ſ(2), ſ(ſ(0), ſ(2)), ſ(ſ(4), ſ(0), ſ(6)), ſ(ſ(0), ſ(7)))
                                                                )
                                                        ),
                                                        ſ(ſ(9), ſ(2), ſ(0), ſ(1))
                                                )
                                        )
                                ),
                                ſ(ſ(1), ſ(0)),
                                ſ(ſ(0), ſ(1))
                        ),
                        ſ(ſ(1), ſ(0))
                ),
                ſ(ſ(8),
                        ſ(ſ(9), ſ(2), ſ(0), ſ(2)),
                        ſ(ſ(9), ſ(2), ſ(ſ(0), ſ(4)), ſ(ſ(0), ſ(7)), ſ(ſ(0), ſ(11)))
                )
        );

        assertEquals(tar(ſ(ſ(42), dec)), ſ(41));
    }

    // http://urbit.org/doc/nock/tut/3/
    @Test
    public void testTarDec2() {
        final Noun dec = ſ(ſ(8),
                ſ(
                        ſ(
                                ſ(ſ(1),
                                        ſ(ſ(1),
                                                ſ(ſ(8),
                                                        ſ(ſ(9), ſ(5), ſ(0), ſ(7)),
                                                        ſ(ſ(6),
                                                                ſ(ſ(5), ſ(ſ(1), ſ(0)), ſ(ſ(0), ſ(29))),
                                                                ſ(ſ(0), ſ(28)),
                                                                ſ(ſ(9),
                                                                        ſ(2),
                                                                        ſ(ſ(0), ſ(6)),
                                                                        ſ(ſ(ſ(9), ſ(2), ſ(ſ(0), ſ(4)), ſ(ſ(0), ſ(28)), ſ(ſ(0), ſ(15))),
                                                                                ſ(ſ(9), ſ(2), ſ(ſ(0), ſ(4)), ſ(ſ(0), ſ(29)), ſ(ſ(0), ſ(15)))
                                                                        ),
                                                                        ſ(ſ(0), ſ(15))
                                                                )
                                                        )
                                                )
                                        ),
                                        ſ(ſ(1), ſ(0)),
                                        ſ(ſ(0), ſ(1))
                                ),
                                ſ(ſ(1),
                                        ſ(ſ(1),
                                                ſ(ſ(8),
                                                        ſ(ſ(1), ſ(0)),
                                                        ſ(ſ(8),
                                                                ſ(ſ(1),
                                                                        ſ(ſ(6),
                                                                                ſ(ſ(5), ſ(ſ(4), ſ(0), ſ(6)), ſ(ſ(0), ſ(30))),
                                                                                ſ(ſ(0), ſ(6)),
                                                                                ſ(ſ(9), ſ(2), ſ(ſ(0), ſ(2)), ſ(ſ(4), ſ(0), ſ(6)), ſ(ſ(0), ſ(7)))
                                                                        )
                                                                ),
                                                                ſ(ſ(9), ſ(2), ſ(0), ſ(1))
                                                        )
                                                )
                                        ),
                                        ſ(ſ(1), ſ(0)),
                                        ſ(ſ(0), ſ(1))
                                )
                        ),
                        ſ(ſ(1), ſ(0))
                ),
                ſ(ſ(8),
                        ſ(ſ(9), ſ(4), ſ(0), ſ(2)),
                        ſ(ſ(9), ſ(2), ſ(ſ(0), ſ(4)), ſ(ſ(0), ſ(7)), ſ(ſ(0), ſ(11)))
                )
        );

        assertEquals(tar(ſ(ſ(42, 12), dec)), ſ(30));
    }
}
