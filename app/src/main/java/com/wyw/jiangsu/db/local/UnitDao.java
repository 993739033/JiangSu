package com.wyw.jiangsu.db.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.wyw.jiangsu.db.Unit;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "UNIT".
*/
public class UnitDao extends AbstractDao<Unit, Long> {

    public static final String TABLENAME = "UNIT";

    /**
     * Properties of entity Unit.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property UId = new Property(0, long.class, "uId", true, "_id");
        public final static Property UName = new Property(1, String.class, "uName", false, "U_NAME");
        public final static Property UCode = new Property(2, String.class, "uCode", false, "U_CODE");
        public final static Property UParent = new Property(3, String.class, "uParent", false, "U_PARENT");
        public final static Property TId = new Property(4, String.class, "tId", false, "T_ID");
        public final static Property UStrid = new Property(5, String.class, "uStrid", false, "U_STRID");
        public final static Property UOrder = new Property(6, String.class, "uOrder", false, "U_ORDER");
    }


    public UnitDao(DaoConfig config) {
        super(config);
    }
    
    public UnitDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"UNIT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: uId
                "\"U_NAME\" TEXT," + // 1: uName
                "\"U_CODE\" TEXT," + // 2: uCode
                "\"U_PARENT\" TEXT," + // 3: uParent
                "\"T_ID\" TEXT," + // 4: tId
                "\"U_STRID\" TEXT," + // 5: uStrid
                "\"U_ORDER\" TEXT);"); // 6: uOrder
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"UNIT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Unit entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getUId());
 
        String uName = entity.getUName();
        if (uName != null) {
            stmt.bindString(2, uName);
        }
 
        String uCode = entity.getUCode();
        if (uCode != null) {
            stmt.bindString(3, uCode);
        }
 
        String uParent = entity.getUParent();
        if (uParent != null) {
            stmt.bindString(4, uParent);
        }
 
        String tId = entity.getTId();
        if (tId != null) {
            stmt.bindString(5, tId);
        }
 
        String uStrid = entity.getUStrid();
        if (uStrid != null) {
            stmt.bindString(6, uStrid);
        }
 
        String uOrder = entity.getUOrder();
        if (uOrder != null) {
            stmt.bindString(7, uOrder);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Unit entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getUId());
 
        String uName = entity.getUName();
        if (uName != null) {
            stmt.bindString(2, uName);
        }
 
        String uCode = entity.getUCode();
        if (uCode != null) {
            stmt.bindString(3, uCode);
        }
 
        String uParent = entity.getUParent();
        if (uParent != null) {
            stmt.bindString(4, uParent);
        }
 
        String tId = entity.getTId();
        if (tId != null) {
            stmt.bindString(5, tId);
        }
 
        String uStrid = entity.getUStrid();
        if (uStrid != null) {
            stmt.bindString(6, uStrid);
        }
 
        String uOrder = entity.getUOrder();
        if (uOrder != null) {
            stmt.bindString(7, uOrder);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public Unit readEntity(Cursor cursor, int offset) {
        Unit entity = new Unit( //
            cursor.getLong(offset + 0), // uId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // uName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // uCode
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // uParent
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // tId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // uStrid
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // uOrder
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Unit entity, int offset) {
        entity.setUId(cursor.getLong(offset + 0));
        entity.setUName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUCode(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUParent(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTId(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUStrid(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setUOrder(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Unit entity, long rowId) {
        entity.setUId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Unit entity) {
        if(entity != null) {
            return entity.getUId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Unit entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
