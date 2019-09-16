package com.example.massagestore.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.massagestore.dao.entity.ProjectDB;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PROJECT_DB".
*/
public class ProjectDBDao extends AbstractDao<ProjectDB, Long> {

    public static final String TABLENAME = "PROJECT_DB";

    /**
     * Properties of entity ProjectDB.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Price = new Property(2, String.class, "price", false, "PRICE");
        public final static Property Time = new Property(3, String.class, "time", false, "TIME");
        public final static Property Commission = new Property(4, String.class, "commission", false, "COMMISSION");
        public final static Property Remarks = new Property(5, String.class, "remarks", false, "REMARKS");
        public final static Property IsMember = new Property(6, String.class, "isMember", false, "IS_MEMBER");
        public final static Property V1 = new Property(7, String.class, "v1", false, "V1");
        public final static Property V2 = new Property(8, String.class, "v2", false, "V2");
        public final static Property V3 = new Property(9, String.class, "v3", false, "V3");
        public final static Property V4 = new Property(10, String.class, "v4", false, "V4");
    }


    public ProjectDBDao(DaoConfig config) {
        super(config);
    }
    
    public ProjectDBDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PROJECT_DB\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"PRICE\" TEXT," + // 2: price
                "\"TIME\" TEXT," + // 3: time
                "\"COMMISSION\" TEXT," + // 4: commission
                "\"REMARKS\" TEXT," + // 5: remarks
                "\"IS_MEMBER\" TEXT," + // 6: isMember
                "\"V1\" TEXT," + // 7: v1
                "\"V2\" TEXT," + // 8: v2
                "\"V3\" TEXT," + // 9: v3
                "\"V4\" TEXT);"); // 10: v4
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PROJECT_DB\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ProjectDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String price = entity.getPrice();
        if (price != null) {
            stmt.bindString(3, price);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(4, time);
        }
 
        String commission = entity.getCommission();
        if (commission != null) {
            stmt.bindString(5, commission);
        }
 
        String remarks = entity.getRemarks();
        if (remarks != null) {
            stmt.bindString(6, remarks);
        }
 
        String isMember = entity.getIsMember();
        if (isMember != null) {
            stmt.bindString(7, isMember);
        }
 
        String v1 = entity.getV1();
        if (v1 != null) {
            stmt.bindString(8, v1);
        }
 
        String v2 = entity.getV2();
        if (v2 != null) {
            stmt.bindString(9, v2);
        }
 
        String v3 = entity.getV3();
        if (v3 != null) {
            stmt.bindString(10, v3);
        }
 
        String v4 = entity.getV4();
        if (v4 != null) {
            stmt.bindString(11, v4);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ProjectDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String price = entity.getPrice();
        if (price != null) {
            stmt.bindString(3, price);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(4, time);
        }
 
        String commission = entity.getCommission();
        if (commission != null) {
            stmt.bindString(5, commission);
        }
 
        String remarks = entity.getRemarks();
        if (remarks != null) {
            stmt.bindString(6, remarks);
        }
 
        String isMember = entity.getIsMember();
        if (isMember != null) {
            stmt.bindString(7, isMember);
        }
 
        String v1 = entity.getV1();
        if (v1 != null) {
            stmt.bindString(8, v1);
        }
 
        String v2 = entity.getV2();
        if (v2 != null) {
            stmt.bindString(9, v2);
        }
 
        String v3 = entity.getV3();
        if (v3 != null) {
            stmt.bindString(10, v3);
        }
 
        String v4 = entity.getV4();
        if (v4 != null) {
            stmt.bindString(11, v4);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ProjectDB readEntity(Cursor cursor, int offset) {
        ProjectDB entity = new ProjectDB( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // price
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // time
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // commission
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // remarks
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // isMember
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // v1
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // v2
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // v3
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10) // v4
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ProjectDB entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPrice(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTime(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCommission(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setRemarks(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setIsMember(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setV1(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setV2(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setV3(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setV4(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ProjectDB entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ProjectDB entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ProjectDB entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
