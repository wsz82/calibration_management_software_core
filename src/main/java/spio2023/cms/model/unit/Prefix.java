package spio2023.cms.model.unit;

public enum Prefix {
    QUETTA  ("Q",    30     ),
    RONNA   ("R",    27     ),
    YOTTA   ("Y",    24     ),
    ZETTA   ("Z",    21     ),
    EXA     ("E",    18     ),
    PETA    ("P",    15     ),
    TERA    ("T",    12     ),
    GIGA    ("G",    9      ),
    MEGA    ("M",    6      ),
    KILO    ("k",    3      ),
    HECTO   ("h",    2      ),
    DECA    ("da",   1      ),
    NULL    ("",     0      ),
    DECI    ("d",    -1     ),
    CENTI   ("c",    -2     ),
    MILLI   ("m",    -3     ),
    MICRO   ("μ",    -6     ),
    NANO    ("n",    -9     ),
    PICO    ("p",    -12    ),
    FEMTO   ("f",    -15    ),
    ATTO    ("a",    -18    ),
    ZEPTO   ("z",    -21    ),
    YOCTO   ("y",    -24    ),
    RONTO   ("r",    -27    ),
    QUECTO  ("q",    -30    );

    private final String symbol;
    private final int exponent;

    Prefix(String symbol, int exponent) {
        this.symbol = symbol;
        this.exponent = exponent;
    }

    public double multiplicand() {
        return Math.pow(10, exponent);
    }

    public String getSymbol() {
        return symbol;
    }

    public int getExponent() {
        return exponent;
    }

}
