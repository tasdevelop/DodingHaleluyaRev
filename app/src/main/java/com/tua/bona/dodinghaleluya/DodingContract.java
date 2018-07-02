package com.tua.bona.dodinghaleluya;

import android.provider.BaseColumns;

public final class DodingContract {
    private DodingContract(){}
    public static class DodingTable implements BaseColumns{
        public static final String TBL_NAME = "doding";
        public static final String COLUMN_NO = "no";
        public static final String COLUMN_KATEGORI = "kategori";
        public static final String COLUMN_JUDUL = "judul";
        public static final String COLUMN_LIRIK = "lirik";
    }
}
