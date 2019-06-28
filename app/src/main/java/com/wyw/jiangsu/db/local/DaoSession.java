package com.wyw.jiangsu.db.local;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.wyw.jiangsu.db.CodeXD;
import com.wyw.jiangsu.db.Unit;
import com.wyw.jiangsu.db.User;

import com.wyw.jiangsu.db.local.CodeXDDao;
import com.wyw.jiangsu.db.local.UnitDao;
import com.wyw.jiangsu.db.local.UserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig codeXDDaoConfig;
    private final DaoConfig unitDaoConfig;
    private final DaoConfig userDaoConfig;

    private final CodeXDDao codeXDDao;
    private final UnitDao unitDao;
    private final UserDao userDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        codeXDDaoConfig = daoConfigMap.get(CodeXDDao.class).clone();
        codeXDDaoConfig.initIdentityScope(type);

        unitDaoConfig = daoConfigMap.get(UnitDao.class).clone();
        unitDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        codeXDDao = new CodeXDDao(codeXDDaoConfig, this);
        unitDao = new UnitDao(unitDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);

        registerDao(CodeXD.class, codeXDDao);
        registerDao(Unit.class, unitDao);
        registerDao(User.class, userDao);
    }
    
    public void clear() {
        codeXDDaoConfig.clearIdentityScope();
        unitDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
    }

    public CodeXDDao getCodeXDDao() {
        return codeXDDao;
    }

    public UnitDao getUnitDao() {
        return unitDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

}
