package org.rekdev.dip.copy.bad;

public class Copy {
    enum OutputDevice {
        Printer, Disk
    };

    public static final int EOF = -1;

    public void copy( OutputDevice device ) {
        int c;
        while ( ( c = readKeyboard() ) != EOF ) {
            switch ( device ) {
                case Printer:
                    writerPrinter( c );
                    break;
                case Disk:
                    writeDisk( c );
                    break;
            }
        }
    }

    private void writeDisk( int c ) {
    }

    private int readKeyboard() {
        return 0;
    }

    private void writerPrinter( int c ) {
    }

}
