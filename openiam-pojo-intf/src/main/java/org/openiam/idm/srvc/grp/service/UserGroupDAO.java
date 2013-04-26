/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 *
 */
package org.openiam.idm.srvc.grp.service;

import org.openiam.idm.srvc.grp.domain.UserGroupEntity;
import org.openiam.idm.srvc.user.domain.UserEntity;

import java.util.List;

/**
 * Interface for the User-Group DAO which manages the relationship between users and groups.
 *
 * @author suneet
 */
public interface UserGroupDAO {

    public abstract void add(UserGroupEntity transientInstance);

    public abstract void remove(UserGroupEntity persistentInstance);

    public abstract UserGroupEntity update(UserGroupEntity detachedInstance);

    public abstract UserGroupEntity findById(java.lang.String id);

    void removeUserFromGroup(String grpId, String userId);

    List<UserGroupEntity> findUserInGroup(String groupId, String userId);

    /**
     * Returns a list of users that are associated with a group.
     *
     * @param groupId
     * @return
     */
    List<UserEntity> findUserByGroup(String groupId);

    List<String> findUserIdsByGroup(String groupId);
}