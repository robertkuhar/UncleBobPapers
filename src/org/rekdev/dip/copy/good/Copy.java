package org.rekdev.dip.copy.good;

public class Copy {
    public static final int EOF = -1;

    private Reader reader = null;
    private Writer writer = null;

    public Copy( Reader reader, Writer writer ) {
        this.reader = reader;
        this.writer = writer;
    }

    public void copy() {
        int c;
        while ( ( c = reader.read() ) != EOF ) {
            writer.write( c );
        }
    }

}
